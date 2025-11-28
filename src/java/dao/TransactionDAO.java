package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class TransactionDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/erp_finance?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_TRANSACTION_SQL =
        "INSERT INTO transactions(type, amount, source, date, description) VALUES (?,?,?,?,?)";
    private static final String SELECT_ALL_TRANSACTIONS = "SELECT * FROM transactions";
    private static final String SELECT_TRANSACTION_BY_ID = "SELECT * FROM transactions WHERE transaction_id = ?";
    private static final String UPDATE_TRANSACTION_SQL =
        "UPDATE transactions SET type = ?, amount = ?, source = ?, date = ?, description = ? WHERE transaction_id = ?";
    private static final String DELETE_TRANSACTION_SQL =
        "DELETE FROM transactions WHERE transaction_id = ?";

    public TransactionDAO() {
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
    public void insertTransaction(Transaction transaction) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_TRANSACTION_SQL)) {
            ps.setString(1, transaction.getType());
            ps.setDouble(2, transaction.getAmount());
            ps.setString(3, transaction.getSource());
            ps.setDate(4, transaction.getDate());
            ps.setString(5, transaction.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<Transaction> selectAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_TRANSACTIONS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setType(rs.getString("type"));
                t.setAmount(rs.getDouble("amount"));
                t.setSource(rs.getString("source"));
                t.setDate(rs.getDate("date"));
                t.setDescription(rs.getString("description"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ BY ID
    public Transaction selectTransaction(int id) {
        Transaction t = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_TRANSACTION_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setType(rs.getString("type"));
                t.setAmount(rs.getDouble("amount"));
                t.setSource(rs.getString("source"));
                t.setDate(rs.getDate("date"));
                t.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    // UPDATE
    public boolean updateTransaction(Transaction transaction) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_TRANSACTION_SQL)) {
            ps.setString(1, transaction.getType());
            ps.setDouble(2, transaction.getAmount());
            ps.setString(3, transaction.getSource());
            ps.setDate(4, transaction.getDate());
            ps.setString(5, transaction.getDescription());
            ps.setInt(6, transaction.getTransactionId());
            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // DELETE
    public boolean deleteTransaction(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_TRANSACTION_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
