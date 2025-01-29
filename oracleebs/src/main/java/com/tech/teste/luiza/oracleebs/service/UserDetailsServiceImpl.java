package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.config.authenticated.UserAuthenticated;
import com.tech.teste.luiza.oracleebs.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço de autenticação de usuários.
 *
 * <p>Carrega os detalhes do usuário com base no nome de usuário fornecido.</p>
 *
 * @author Cicero Higor Gomes
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserAuthenticated::new)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
    }
}
