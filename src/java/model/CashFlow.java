/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class CashFlow {
    private int cashflowId;
    private String type; // inflow/outflow
    private double amount;
    private String source;
    private Date date;
    private String description;

    public CashFlow() {}

    public CashFlow(int cashflowId, String type, double amount, String source, Date date, String description) {
        this.cashflowId = cashflowId;
        this.type = type;
        this.amount = amount;
        this.source = source;
        this.date = date;
        this.description = description;
    }

    public int getCashflowId() { return cashflowId; }
    public void setCashflowId(int cashflowId) { this.cashflowId = cashflowId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
