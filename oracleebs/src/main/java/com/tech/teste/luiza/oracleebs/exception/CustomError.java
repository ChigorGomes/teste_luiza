package com.tech.teste.luiza.oracleebs.exception;

import java.time.LocalDateTime;

public record CustomError(LocalDateTime timestamp, Integer status, String error, String path, String method) {
}
