/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Payroll {
    private int payrollId;
    private int employeeId;
    private Date paymentDate;
    private double amount;
    private String status; // Paid/Unpaid
    private String description;

    public Payroll() {}

    public Payroll(int payrollId, int employeeId, Date paymentDate, double amount, String status, String description) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.status = status;
        this.description = description;
    }

    public int getPayrollId() { return payrollId; }
    public void setPayrollId(int payrollId) { this.payrollId = payrollId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
