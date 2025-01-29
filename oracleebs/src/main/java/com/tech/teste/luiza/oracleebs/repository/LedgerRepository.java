package com.tech.teste.luiza.oracleebs.repository;

import com.tech.teste.luiza.oracleebs.model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
/**
 * Repositório para gerenciar a persistência da entidade {@link Ledger}.
 *
 * <p>Fornece métodos padrão para operações CRUD através da extensão de {@link JpaRepository}.</p>
 *
 * @author Cicero Higor Gomes
 */
public interface LedgerRepository extends JpaRepository<Ledger, UUID> {
}
