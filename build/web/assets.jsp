<%@page import="dao.AssetDAO"%>
<%@ page import="java.util.*, dao.AssetDAO, model.Asset" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assets Management</title>
</head>
<body>
<h2>Assets Management</h2>

<%
    AssetDAO dao = new AssetDAO();

    String action = request.getParameter("action");
    String idStr = request.getParameter("asset_id");
    int assetId = idStr != null ? Integer.parseInt(idStr) : 0;

    // ADD ASSET
    if ("add".equals(action)) {
        String name = request.getParameter("name");
        java.sql.Date purchaseDate = java.sql.Date.valueOf(request.getParameter("purchase_date"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        double depreciation = Double.parseDouble(request.getParameter("depreciation"));

        Asset asset = new Asset();
        asset.setName(name);
        asset.setPurchaseDate(purchaseDate);
        asset.setCost(cost);
        asset.setDepreciation(depreciation);
        dao.insertAsset(asset);
    }

    // UPDATE ASSET
    if ("update".equals(action)) {
        String name = request.getParameter("name");
        java.sql.Date purchaseDate = java.sql.Date.valueOf(request.getParameter("purchase_date"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        double depreciation = Double.parseDouble(request.getParameter("depreciation"));

        Asset asset = new Asset();
        asset.setAssetId(assetId);
        asset.setName(name);
        asset.setPurchaseDate(purchaseDate);
        asset.setCost(cost);
        asset.setDepreciation(depreciation);
        dao.updateAsset(asset);
    }

    // DELETE ASSET
    if ("delete".equals(action)) {
        dao.deleteAsset(assetId);
    }

    List<Asset> assets = dao.selectAllAssets();
%>

<h3>Add Asset</h3>
<form method="post">
    Name: <input type="text" name="name" required><br>
    Purchase Date (YYYY-MM-DD): <input type="text" name="purchase_date" required><br>
    Cost: <input type="number" step="0.01" name="cost" required><br>
    Depreciation: <input type="number" step="0.01" name="depreciation" required><br>
    <input type="hidden" name="action" value="add">
    <input type="submit" value="Add Asset">
</form>

<h3>All Assets</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Purchase Date</th>
        <th>Cost</th>
        <th>Depreciation</th>
        <th>Actions</th>
    </tr>
    <%
        for(Asset a : assets) {
    %>
    <tr>
        <td><%= a.getAssetId() %></td>
        <td><%= a.getName() %></td>
        <td><%= a.getPurchaseDate() %></td>
        <td><%= a.getCost() %></td>
        <td><%= a.getDepreciation() %></td>
        <td>
            <!-- Update Form -->
            <form method="post" style="display:inline;">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="asset_id" value="<%= a.getAssetId() %>">
                Name: <input type="text" name="name" value="<%= a.getName() %>" required>
                Purchase Date: <input type="text" name="purchase_date" value="<%= a.getPurchaseDate() %>" required>
                Cost: <input type="number" step="0.01" name="cost" value="<%= a.getCost() %>" required>
                Depreciation: <input type="number" step="0.01" name="depreciation" value="<%= a.getDepreciation() %>" required>
                <input type="submit" value="Update">
            </form>

            <!-- Delete Form -->
            <form method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="asset_id" value="<%= a.getAssetId() %>">
                <input type="submit" value="Delete" onclick="return confirm('Delete this asset?');">
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
