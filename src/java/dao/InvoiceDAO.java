package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Invoice;

public class InvoiceDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/erp_finance?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_INVOICE_SQL
            = "INSERT INTO assets (type, related_supplier, related_author, amount, status, issue_date, due_date) VALUES (?,? ,?,?, ?, ?, ?)";
    private static final String SELECT_ALL_INVOICES = "SELECT * FROM invoices";
    private static final String SELECT_INVOICE_BY_ID = "SELECT * FROM invoices WHERE invoice_id = ?";
    private static final String UPDATE_INVOICE_SQL
            = "UPDATE invoice SET type = ?, related_supplier = ?, related_author = ?, amount = ?, status = ?, issue_date = ?, due_date = ? WHERE invoice_id = ?";
    private static final String DELETE_INVOICE_SQL
            = "DELETE FROM invoice WHERE invoice_id = ?";

    public InvoiceDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    //CREATE
    public void insertInvoice(Invoice invoice) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(INSERT_INVOICE_SQL)) {
            ps.setString(1, invoice.getType());
            ps.setInt(2, invoice.getRelatedSupplierId());
            ps.setString(3, invoice.getRelatedAuthor());
            ps.setString(4, invoice.getStatus());
            ps.setDate(5, (Date) invoice.getIssueDate());
            ps.setDate(6, (Date) invoice.getDueDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //READ ALL
    public List<Invoice> selectAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_INVOICES); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setType(rs.getString("type"));
                invoice.getRelatedSupplierId(rs.getInt("related_supplier"));
                invoice.getRelatedAuthor(rs.getString("related_author"));
                invoice.getStatus(rs.getString("status"));
                invoice.getIssueDate(rs.getDate("issue_date"));
                invoice.getDueDate(rs.getDate("due_date"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    //READ BY ID
    public Invoice selectInvoice(int id) {
        Invoice invoice = null;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_INVOICE_BY_ID); ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, id);
            if (rs.next()) {
                invoice.setType(rs.getString("type"));
                invoice.getRelatedSupplierId(rs.getInt("related_supplier"));
                invoice.getRelatedAuthor(rs.getString("related_author"));
                invoice.getStatus(rs.getString("status"));
                invoice.getIssueDate(rs.getDate("issue_date"));
                invoice.getDueDate(rs.getDate("due_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    //UPDATE
    public boolean updateInvoice(Invoice invoice) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_INVOICE_SQL)) {
            ps.setString(1, invoice.getType());
            ps.setInt(2, invoice.getRelatedSupplierId());
            ps.setString(3, invoice.getRelatedAuthor());
            ps.setString(4, invoice.getStatus());
            ps.setDate(5, (Date) invoice.getIssueDate());
            ps.setDate(6, (Date) invoice.getDueDate());
                rowUpdated = ps.executeUpdate() > 0;
              } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
    
       
    //DELETE
       public boolean deleteSupplier(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_INVOICE_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
    

}
