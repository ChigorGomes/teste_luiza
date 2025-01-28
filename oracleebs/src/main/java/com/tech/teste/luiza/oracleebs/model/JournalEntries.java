package com.tech.teste.luiza.oracleebs.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_journal_entries")
public class JournalEntries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;
    private String journalName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate accountingDate;
    private String currencyCode;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "journal_id")
    private Set<JournalLines> lines;

    public JournalEntries() {
    }

    public JournalEntries(String journalName, LocalDate accountingDate, String currencyCode, Set<JournalLines> lines) {
        this.journalName = journalName;
        this.accountingDate = accountingDate;
        this.currencyCode = currencyCode;
        this.lines = lines;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }


    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Set<JournalLines> getLines() {
        return lines;
    }

    public void setLines(Set<JournalLines> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        JournalEntries that = (JournalEntries) o;
        return journalName.equals(that.journalName) && accountingDate.equals(that.accountingDate) && currencyCode.equals(that.currencyCode) && lines.equals(that.lines);
    }

    @Override
    public int hashCode() {
        int result = journalName.hashCode();
        result = 31 * result + accountingDate.hashCode();
        result = 31 * result + currencyCode.hashCode();
        result = 31 * result + lines.hashCode();
        return result;
    }

    @PrePersist
    public void prePersist() {
        accountingDate = LocalDateTime.now().toLocalDate();
    }
}
