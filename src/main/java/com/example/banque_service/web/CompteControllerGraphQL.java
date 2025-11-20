package com.example.banque_service.web;

import com.example.banque_service.DTO.CompteRequest;
import com.example.banque_service.DTO.SoldeStats;
import com.example.banque_service.entities.Compte;
import com.example.banque_service.repositories.CompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class CompteControllerGraphQL {

    private CompteRepository compteRepository;

    // === 1) Récupérer tous les comptes ===
    @QueryMapping
    public List<Compte> allComptes() {
        return compteRepository.findAll();
    }

    // === 2) Récupérer un compte par ID ===
    @QueryMapping
    public Compte compteById(@Argument Long id) {
        Compte compte = compteRepository.findById(id).orElse(null);
        if (compte == null)
            throw new RuntimeException(String.format("Compte %s not found", id));
        return compte;
    }

    // === 3) Créer / mettre à jour un compte ===
    @MutationMapping
    public Compte saveCompte(@Argument CompteRequest compte) {
        Compte c = new Compte();
        c.setSolde(compte.getSolde());
        c.setType(compte.getType()); // ✅ ici : TypeCompte

        // dateCreation au format "yyyy-MM-dd"
        LocalDate localDate = LocalDate.parse(compte.getDateCreation());
        c.setDateCreation(Date.valueOf(localDate));

        return compteRepository.save(c);
    }

    // === 4) Statistiques globales sur les soldes ===
    @QueryMapping
    public SoldeStats totalSolde() {
        List<Compte> comptes = compteRepository.findAll();
        int count = comptes.size();
        double sum = comptes.stream().mapToDouble(Compte::getSolde).sum();
        double average = count == 0 ? 0.0 : sum / count;

        return new SoldeStats(count, sum, average);
    }
}
