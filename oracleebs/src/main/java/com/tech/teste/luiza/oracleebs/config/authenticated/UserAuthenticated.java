package com.tech.teste.luiza.oracleebs.config.authenticated;

import com.tech.teste.luiza.oracleebs.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Classe que representa um usuário autenticado no contexto do Spring Security.
 * Implementa a interface {@link UserDetails} para fornecer informações
 * do usuário ao sistema de autenticação.
 *
 * <p>Esta classe encapsula um objeto {@link User} e expõe suas informações,
 * como username e password. Além disso, os métodos relacionados ao estado
 * da conta do usuário retornam valores padrão (verdadeiros).</p>
 *
 * <p>Os métodos de autorização retornam uma coleção vazia de {@link GrantedAuthority},
 * já que esta classe não adiciona permissões específicas.</p>
 *
 * @author Cicero Higor Gomes
 */

public class UserAuthenticated implements UserDetails {
    private final User user;

    public UserAuthenticated(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
