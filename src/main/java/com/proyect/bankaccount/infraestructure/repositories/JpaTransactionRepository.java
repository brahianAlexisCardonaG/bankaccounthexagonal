package com.proyect.bankaccount.infraestructure.repositories;

import com.proyect.bankaccount.infraestructure.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTransactionRepository extends JpaRepository<TransactionEntity,Long> {
}
