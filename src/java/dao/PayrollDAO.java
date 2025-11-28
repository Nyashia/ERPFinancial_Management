/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Payroll;

public class PayrollDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/erp_finance?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_PAYROLL_SQL = 
        "INSERT INTO payroll(employee_id, payment_date, amount, royalties, status, description) VALUES (?,?,?,?,?,?)";
    private static final String SELECT_ALL_PAYROLL = "SELECT * FROM payroll";
    private static final String SELECT_PAYROLL_BY_ID = "SELECT * FROM payroll WHERE payroll_id = ?";
    private static final String UPDATE_PAYROLL_SQL = 
        "UPDATE payroll SET employee_id = ?, payment_date = ?, amount = ?, royalties = ?, status = ?, description = ? WHERE payroll_id = ?";
    private static final String DELETE_PAYROLL_SQL = "DELETE FROM payroll WHERE payroll_id = ?";

    public PayrollDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // CREATE
    public void insertPayroll(Payroll payroll) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PAYROLL_SQL)) {
            ps.setInt(1, payroll.getEmployeeId());
            ps.setDate(2, (Date) payroll.getPaymentDate());
            ps.setDouble(3, payroll.getAmount());
            ps.setDouble(4, payroll.getRoyalties());
            ps.setString(5, payroll.getStatus());
            ps.setString(6, payroll.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<Payroll> selectAllPayroll() {
        List<Payroll> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PAYROLL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Payroll payroll = new Payroll();
                payroll.setPayrollId(rs.getInt("payroll_id"));
                payroll.setEmployeeId(rs.getInt("employee_id"));
                payroll.setPaymentDate(rs.getDate("payment_date"));
                payroll.setAmount(rs.getDouble("amount"));
                payroll.setRoyalties(rs.getDouble("royalties"));
                payroll.setStatus(rs.getString("status"));
                payroll.setDescription(rs.getString("description"));
                list.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ BY ID
    public Payroll selectPayroll(int id) {
        Payroll payroll = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PAYROLL_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                payroll = new Payroll();
                payroll.setPayrollId(rs.getInt("payroll_id"));
                payroll.setEmployeeId(rs.getInt("employee_id"));
                payroll.setPaymentDate(rs.getDate("payment_date"));
                payroll.setAmount(rs.getDouble("amount"));
                payroll.setRoyalties(rs.getDouble("royalties"));
                payroll.setStatus(rs.getString("status"));
                payroll.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payroll;
    }

    // UPDATE
    public boolean updatePayroll(Payroll payroll) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PAYROLL_SQL)) {
            ps.setInt(1, payroll.getEmployeeId());
            ps.setDate(2, (Date) payroll.getPaymentDate());
            ps.setDouble(3, payroll.getAmount());
            ps.setDouble(4, payroll.getRoyalties());
            ps.setString(5, payroll.getStatus());
            ps.setString(6, payroll.getDescription());
            ps.setInt(7, payroll.getPayrollId());
            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // DELETE
    public boolean deletePayroll(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_PAYROLL_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
