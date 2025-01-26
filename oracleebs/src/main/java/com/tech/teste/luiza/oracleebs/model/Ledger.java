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
