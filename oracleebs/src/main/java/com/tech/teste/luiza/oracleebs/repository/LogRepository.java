package com.tech.teste.luiza.oracleebs.repository;

import com.tech.teste.luiza.oracleebs.model.Logging;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para gerenciar a persistência da entidade {@link Logging}.
 *
 * <p>Fornece métodos padrão para operações CRUD através da extensão de {@link JpaRepository}.</p>
 *
 * @author Cicero Higor Gomes
 */

public interface LogRepository extends JpaRepository<Logging, Long> {

}
