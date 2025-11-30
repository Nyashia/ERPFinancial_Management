/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Invoice {
    private int invoiceId;
    private double amount;
    private String status; // Paid / Unpaid
    private int relatedSupplierId; // supplier this invoice is for
    private Date issueDate;
    private Date dueDate;

    public Invoice() {}

    public Invoice(int invoiceId, int relatedSupplierId, double amount, String status, Date issueDate, Date dueDate) {
        this.invoiceId = invoiceId;
        this.relatedSupplierId = relatedSupplierId;
        this.amount = amount;
        this.status = status;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public int getRelatedSupplierId() { return relatedSupplierId; }
    public void setRelatedSupplierId(int relatedSupplierId) { this.relatedSupplierId = relatedSupplierId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
}

