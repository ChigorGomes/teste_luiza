package com.tech.teste.luiza.oracleebs.repository;

import com.tech.teste.luiza.oracleebs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório para gerenciar a persistência da entidade {@link User}.
 *
 * <p>Fornece métodos padrão para operações CRUD através da extensão de {@link JpaRepository}
 * e um método personalizado para buscar um usuário pelo nome de usuário.</p>
 *
 * @author Cicero Higor Gomes
 */
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
}
