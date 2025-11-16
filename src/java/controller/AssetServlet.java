package controller;

import dao.AssetDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.Asset;

public class AssetServlet extends HttpServlet {
    private AssetDAO assetsDAO;

    public void init() {
        assetsDAO = new AssetDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new-asset":
                    showNewForm(request, response);
                    break;
                case "/insert-asset":
                    insertAsset(request, response);
                    break;
                case "/delete-asset":
                    deleteAsset(request, response);
                    break;
                case "/edit-asset":
                    showEditForm(request, response);
                    break;
                case "/update-asset":
                    updateAsset(request, response);
                    break;
                default:
                    listAssets(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // List all assets
    private void listAssets(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Asset> listAssets = assetsDAO.selectAllAssets();
        request.setAttribute("listAssets", listAssets);
        RequestDispatcher dispatcher = request.getRequestDispatcher("assets.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-asset.jsp");
        dispatcher.forward(request, response);
    }

    private void insertAsset(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        java.sql.Date purchaseDate = java.sql.Date.valueOf(request.getParameter("purchase_date"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        double depreciation = Double.parseDouble(request.getParameter("depreciation"));

        Asset newAsset = new Asset();
        newAsset.setName(name);
        newAsset.setPurchaseDate(purchaseDate);
        newAsset.setCost(cost);
        newAsset.setDepreciation(depreciation);

        assetsDAO.insertAsset(newAsset);
        response.sendRedirect("assets");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Asset existingAsset = assetsDAO.selectAsset(id);
        request.setAttribute("asset", existingAsset);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-asset.jsp");
        dispatcher.forward(request, response);
    }

    private void updateAsset(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        java.sql.Date purchaseDate = java.sql.Date.valueOf(request.getParameter("purchase_date"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        double depreciation = Double.parseDouble(request.getParameter("depreciation"));

        Asset asset = new Asset();
        asset.setAssetId(id);
        asset.setName(name);
        asset.setPurchaseDate(purchaseDate);
        asset.setCost(cost);
        asset.setDepreciation(depreciation);

        assetsDAO.updateAsset(asset);
        response.sendRedirect("assets");
    }

    private void deleteAsset(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        assetsDAO.deleteAsset(id);
        response.sendRedirect("assets");
    }
}
