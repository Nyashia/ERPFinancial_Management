package model;

import java.sql.Date;

public class Transaction {
    private int transactionId;
    private String type; // "expense" or "revenue"
    private double amount;
    private String source;
    private Date date;
    private String description;

    public Transaction() {}

    public Transaction(int transactionId, String type, double amount, String source, Date date, String description) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.source = source;
        this.date = date;
        this.description = description;
    }

    // Getters and Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

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
