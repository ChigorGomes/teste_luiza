package com.tech.teste.luiza.oracleebs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.UUID;

/**
 * Representa a entidade de razão contábil (Ledger) no sistema.
 *
 * <p>Esta classe é mapeada para a tabela {@code tb_ledger} no banco de dados
 * e mantém informações relacionadas a uma transação contábil, como descrição
 * e associação com entradas de diário contábil ({@link JournalEntries}).</p>
 *
 * <ul>
 *   <li>{@code transcationId}: Identificador único da transação, gerado como um UUID.</li>
 *   <li>{@code description}: Descrição da transação contábil.</li>
 *   <li>{@code journalEntries}: Referência à entidade {@link JournalEntries}, associada via relacionamento {@code OneToOne}.</li>
 * </ul>
 *
 * @author Cicero Higor Gomes
 */

@Entity
@Table(name = "tb_ledger")
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transcationId;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_id", referencedColumnName = "journalId")
    private JournalEntries journalEntries;


    public Ledger() {
    }

    public UUID getTranscationId() {
        return transcationId;
    }

    public void setTranscationId(UUID transcationId) {
        this.transcationId = transcationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JournalEntries getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(JournalEntries journalEntries) {
        this.journalEntries = journalEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Ledger ledger = (Ledger) o;
        return transcationId.equals(ledger.transcationId) && description.equals(ledger.description) && journalEntries.equals(ledger.journalEntries);
    }

    @Override
    public int hashCode() {
        int result = transcationId.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + journalEntries.hashCode();
        return result;
    }
}
