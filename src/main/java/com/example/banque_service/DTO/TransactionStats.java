package com.example.banque_service.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStats {
    private long count;
    private double sumDepots;
    private double sumRetraits;
}

