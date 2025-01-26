package com.tech.teste.luiza.oracleebs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_journal_lines")
public class JournalLines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineId;
    private String accountCode;
    private Double debit;
    private Double credit;
    private String costCenter;
    private String description;



    public JournalLines() {
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        JournalLines that = (JournalLines) o;
        return accountCode.equals(that.accountCode) && debit.equals(that.debit) && credit.equals(that.credit) && costCenter.equals(that.costCenter) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = accountCode.hashCode();
        result = 31 * result + debit.hashCode();
        result = 31 * result + credit.hashCode();
        result = 31 * result + costCenter.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
