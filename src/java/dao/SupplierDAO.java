package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Supplier;

public class SupplierDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/erp_finance?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_SUPPLIER_SQL
            = "INSERT INTO suppliers (supplier_id, name, email, phone, address) VALUES (?,?,?,?,?0";
    private static final String SELECT_ALL_SUPPLIERS = "SELECT * FROM suppliers";
    private static final String SELECT_SUPPLIER_BY_ID = "SELECT * FROM suppliers WHERE supplier_id = ?";
    private static final String UPDATE_SUPPLIER_SQL
            = "UPDATE suppliers SET name = ?, email = ?, phone = ?, address = ? WHERE supplier_id = ?";
    private static final String DELETE_SUPPLIER_SQL
            = "DELETE FROM suppliers WHERE supplier_id = ?";

    public SupplierDAO() {
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
    public void insertSupplier(Supplier supplier) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(INSERT_SUPPLIER_SQL)) {
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getPhone());
            ps.setString(4, supplier.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //READ ALL
    public List<Supplier> selectAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SUPPLIERS); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("name"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    // READ BY ID
    public Supplier selectSupplier(int id) {
        Supplier supplier = null;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_SUPPLIER_BY_ID); ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, id);
            if (rs.next()) {
                supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("name"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    //UPDATE
    public boolean updateSupplier(Supplier supplier) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_SUPPLIER_SQL)) {
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getPhone());
            ps.setString(4, supplier.getAddress());
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
             PreparedStatement ps = connection.prepareStatement(DELETE_SUPPLIER_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    }
