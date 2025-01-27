package com.tech.teste.luiza.oracleebs.dto.response;

public record AuthenticationResponseRecord(String token, String type, Long expirationTime) {
}
