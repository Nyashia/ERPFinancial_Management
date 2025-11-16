package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Asset;

public class AssetDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/erp_finance?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";   
    private String jdbcPassword = "";       

    private static final String INSERT_ASSET_SQL =
            "INSERT INTO assets (name, purchase_date, cost, depreciation) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_ASSETS = "SELECT * FROM assets";
    private static final String SELECT_ASSET_BY_ID = "SELECT * FROM assets WHERE asset_id = ?";
    private static final String UPDATE_ASSET_SQL =
            "UPDATE assets SET name = ?, purchase_date = ?, cost = ?, depreciation = ? WHERE asset_id = ?";
    private static final String DELETE_ASSET_SQL =
            "DELETE FROM assets WHERE asset_id = ?";

    public AssetDAO() {
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
    public void insertAsset(Asset asset) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_ASSET_SQL)) {
            ps.setString(1, asset.getName());
            ps.setDate(2, (Date) asset.getPurchaseDate());
            ps.setDouble(3, asset.getCost());
            ps.setDouble(4, asset.getDepreciation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<Asset> selectAllAssets() {
        List<Asset> assets = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ASSETS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Asset asset = new Asset();
                asset.setAssetId(rs.getInt("asset_id"));
                asset.setName(rs.getString("name"));
                asset.setPurchaseDate(rs.getDate("purchase_date"));
                asset.setCost(rs.getDouble("cost"));
                asset.setDepreciation(rs.getDouble("depreciation"));
                assets.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    // READ BY ID
    public Asset selectAsset(int id) {
        Asset asset = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ASSET_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                asset = new Asset();
                asset.setAssetId(rs.getInt("asset_id"));
                asset.setName(rs.getString("name"));
                asset.setPurchaseDate(rs.getDate("purchase_date"));
                asset.setCost(rs.getDouble("cost"));
                asset.setDepreciation(rs.getDouble("depreciation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asset;
    }

    // UPDATE
    public boolean updateAsset(Asset asset) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_ASSET_SQL)) {
            ps.setString(1, asset.getName());
            ps.setDate(2, (Date) asset.getPurchaseDate());
            ps.setDouble(3, asset.getCost());
            ps.setDouble(4, asset.getDepreciation());
            ps.setInt(5, asset.getAssetId());
            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // DELETE
    public boolean deleteAsset(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_ASSET_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
