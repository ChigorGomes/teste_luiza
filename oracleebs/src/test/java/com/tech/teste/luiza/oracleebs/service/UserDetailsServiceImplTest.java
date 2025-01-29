package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.config.authenticated.UserAuthenticated;
import com.tech.teste.luiza.oracleebs.model.User;
import com.tech.teste.luiza.oracleebs.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Classe de testes para o {@link UserDetailsServiceImpl}.
 *
 * <p>Valida o carregamento de detalhes do usuário com base no username, garantindo o
 * retorno correto de um {@link org.springframework.security.core.userdetails.UserDetails}
 * ou o lançamento de exceções em casos de usuário não encontrado.</p>
 *
 * @author Cicero Higor Gomes
 */

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para UserDetailsServiceImpl")
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;


    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("luiza");
        user.setPassword("<PASSWORD>");

    }

    @Test
    @DisplayName("Deve retornar um UserDetails válido quando o usuário é encontrado pelo username")
    void testLoadUserByUsername() {

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        assertNotNull(userDetails);
        assertInstanceOf(UserAuthenticated.class, userDetails);
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());


    }

    @Test
    @DisplayName("Deve lançar UsernameNotFoundException quando o usuário não é encontrado pelo username")
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(user.getUsername());
        });
    }


}