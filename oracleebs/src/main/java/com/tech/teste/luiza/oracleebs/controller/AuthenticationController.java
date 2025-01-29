package com.tech.teste.luiza.oracleebs.controller;

import com.tech.teste.luiza.oracleebs.dto.response.AuthenticationResponseRecord;
import com.tech.teste.luiza.oracleebs.service.AuthenticationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ledger/authenticate")
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationServiceImpl;

    public AuthenticationController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @PostMapping()
    public ResponseEntity<AuthenticationResponseRecord> authenticate(Authentication authentication) {
        AuthenticationResponseRecord responseRecord = authenticationServiceImpl.authenticate(authentication);
        if (responseRecord == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().body(responseRecord);
    }


}
