package com.tech.teste.luiza.oracleebs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * Representa a entidade de registro de logs no sistema.
 *
 * <p>Esta classe é mapeada para a tabela {@code tb_log} no banco de dados,
 * armazenando informações relacionadas a requisições e respostas, como status,
 * payload, caminho, método HTTP e o timestamp.</p>
 *
 * <ul>
 *   <li>{@code id}: Identificador único do log.</li>
 *   <li>{@code status}: Código de status HTTP associado à requisição/resposta.</li>
 *   <li>{@code timestamp}: Data e hora da criação do log, gerada automaticamente.</li>
 *   <li>{@code payload}: Corpo da requisição/resposta armazenado como texto.</li>
 *   <li>{@code path}: Caminho HTTP acessado.</li>
 *   <li>{@code method}: Método HTTP utilizado (e.g., GET, POST).</li>
 * </ul>
 *
 * @author Cicero Higor Gomes
 */

@Entity
@Table(name = "tb_log")
public class Logging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer status;
    private LocalDateTime timestamp;
    @Column(columnDefinition = "TEXT")
    private String payload;
    private String path;
    private String method;

    public Logging() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
    }
}
