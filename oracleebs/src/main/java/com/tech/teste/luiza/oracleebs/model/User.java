package com.tech.teste.luiza.oracleebs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa a entidade de usuário no sistema.
 *
 * <p>Esta classe é mapeada para a tabela {@code tb_user} no banco de dados,
 * armazenando credenciais de usuários, incluindo nome de usuário e senha.</p>
 *
 * <ul>
 *   <li>{@code username}: Identificador único do usuário.</li>
 *   <li>{@code password}: Senha do usuário.</li>
 * </ul>
 *
 * @author Cicero Higor Gomes
 */

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    private String username;
    private String password;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
