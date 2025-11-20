package com.example.banque_service.DTO;

import com.example.banque_service.entities.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteRequest {
    private double solde;
    private String dateCreation; // format "yyyy-MM-dd"
    private TypeCompte type;
}

