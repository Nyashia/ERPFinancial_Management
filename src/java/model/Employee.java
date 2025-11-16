/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    private double salary;
    private Date hireDate;

    public Employee() {}

    public Employee(int employeeId, String firstName, String lastName, String email, String phone, String role, double salary, Date hireDate) {
        this.employeeId = employeeId;
       this.firstName = firstName;
       this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
      public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
}
