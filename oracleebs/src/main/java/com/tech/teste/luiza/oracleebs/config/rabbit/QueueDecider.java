package com.tech.teste.luiza.oracleebs.config.rabbit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface QueueDecider {
    String decideQueue(HttpServletRequest request, HttpServletResponse httpServletResponse);
}