package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Invoice;

public class InvoiceDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/erp_finance?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_INVOICE_SQL = 
        "INSERT INTO invoices (related_supplier_id, amount, status, issue_date, due_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_INVOICES = "SELECT * FROM invoices";
    private static final String SELECT_INVOICE_BY_ID = "SELECT * FROM invoices WHERE invoice_id = ?";
    private static final String UPDATE_INVOICE_SQL = 
        "UPDATE invoices SET related_supplier_id=?, amount=?, status=?, issue_date=?, due_date=? WHERE invoice_id=?";
    private static final String DELETE_INVOICE_SQL = "DELETE FROM invoices WHERE invoice_id=?";

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

    // CREATE
    public void insertInvoice(Invoice invoice) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_INVOICE_SQL)) {
            ps.setInt(1, invoice.getRelatedSupplierId());
            ps.setDouble(2, invoice.getAmount());
            ps.setString(3, invoice.getStatus());
            ps.setDate(4, new java.sql.Date(invoice.getIssueDate().getTime()));
            ps.setDate(5, new java.sql.Date(invoice.getDueDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<Invoice> selectAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_INVOICES);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setRelatedSupplierId(rs.getInt("related_supplier_id"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setStatus(rs.getString("status"));
                invoice.setIssueDate(rs.getDate("issue_date"));
                invoice.setDueDate(rs.getDate("due_date"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    // READ BY ID
    public Invoice selectInvoice(int id) {
        Invoice invoice = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_INVOICE_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    invoice = new Invoice();
                    invoice.setInvoiceId(rs.getInt("invoice_id"));
                    invoice.setRelatedSupplierId(rs.getInt("related_supplier_id"));
                    invoice.setAmount(rs.getDouble("amount"));
                    invoice.setStatus(rs.getString("status"));
                    invoice.setIssueDate(rs.getDate("issue_date"));
                    invoice.setDueDate(rs.getDate("due_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    // UPDATE
    public boolean updateInvoice(Invoice invoice) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_INVOICE_SQL)) {
            ps.setInt(1, invoice.getRelatedSupplierId());
            ps.setDouble(2, invoice.getAmount());
            ps.setString(3, invoice.getStatus());
            ps.setDate(4, new java.sql.Date(invoice.getIssueDate().getTime()));
            ps.setDate(5, new java.sql.Date(invoice.getDueDate().getTime()));
            ps.setInt(6, invoice.getInvoiceId());
            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // DELETE
    public boolean deleteInvoice(int id) {
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

