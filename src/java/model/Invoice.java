/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Invoice {
    private int invoiceId;
    private String type; // 'AP' for supplier, 'ROYALTY' for author
    private Integer relatedSupplierId; // nullable if type = ROYALTY
    private String relatedAuthor;      // nullable if type = AP
    private double amount;
    private String status; // Paid / Unpaid
    private Date issueDate;
    private Date dueDate;

    public Invoice() {}

    public Invoice(int invoiceId, String type, Integer relatedSupplierId, String relatedAuthor, double amount, String status, Date issueDate, Date dueDate) {
        this.invoiceId = invoiceId;
        this.type = type;
        this.relatedSupplierId = relatedSupplierId;
        this.relatedAuthor = relatedAuthor;
        this.amount = amount;
        this.status = status;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Integer getRelatedSupplierId() { return relatedSupplierId; }
    public void setRelatedSupplierId(Integer relatedSupplierId) { this.relatedSupplierId = relatedSupplierId; }

    public String getRelatedAuthor() { return relatedAuthor; }
    public void setRelatedAuthor(String relatedAuthor) { this.relatedAuthor = relatedAuthor; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public void getRelatedSupplierId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void getRelatedAuthor(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void getStatus(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void getIssueDate(java.sql.Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void getDueDate(java.sql.Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
