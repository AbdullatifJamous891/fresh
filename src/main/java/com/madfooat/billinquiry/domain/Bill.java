package com.madfooat.billinquiry.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {

    private Date dueDate;
    private BigDecimal dueAmount;
    private BigDecimal fees;

    public Bill(Date dueDate, BigDecimal dueAmount){
        this.dueDate = dueDate;
        this.dueAmount = dueAmount;
    }


    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "dueDate=" + dueDate +
                ", dueAmount=" + dueAmount +
                ", fees=" + fees +
                '}';
    }
}
