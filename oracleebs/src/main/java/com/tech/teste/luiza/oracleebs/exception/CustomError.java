package com.tech.teste.luiza.oracleebs.exception;

import java.time.Instant;

public record CustomError(Instant timestamp, Integer status, String error, String path, String method) {
}
