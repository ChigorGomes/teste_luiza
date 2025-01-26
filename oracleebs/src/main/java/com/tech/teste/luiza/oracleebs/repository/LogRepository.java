package com.tech.teste.luiza.oracleebs.repository;

import com.tech.teste.luiza.oracleebs.model.Logging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Logging, Long> {
}
