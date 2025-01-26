package com.tech.teste.luiza.oracleebs.repository;

import com.tech.teste.luiza.oracleebs.model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LedgerRepository extends JpaRepository<Ledger, UUID> {
}
