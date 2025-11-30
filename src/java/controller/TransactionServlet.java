/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.TransactionDAO;
import model.Transaction;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransactionDAO transactionDAO;

    public void init() {
        transactionDAO = new TransactionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteTransaction(request, response);
                break;
            default:
                listTransactions(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "insert":
                insertTransaction(request, response);
                break;
            case "update":
                updateTransaction(request, response);
                break;
            default:
                response.sendRedirect("transaction");
                break;
        }
    }

    private void listTransactions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Transaction> list = transactionDAO.selectAllTransactions();
        request.setAttribute("listTransaction", list);
        request.getRequestDispatcher("transaction.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("transaction-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Transaction transaction = transactionDAO.selectTransaction(id);
        request.setAttribute("transaction", transaction);
        request.getRequestDispatcher("transaction-form.jsp").forward(request, response);
    }

    private void insertTransaction(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String type = request.getParameter("type");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String source = request.getParameter("source");
        Date date = Date.valueOf(request.getParameter("date"));
        String description = request.getParameter("description");

        Transaction transaction = new Transaction(0, type, amount, source, date, description);
        transactionDAO.insertTransaction(transaction);
        response.sendRedirect("transaction");
    }

    private void updateTransaction(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String type = request.getParameter("type");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String source = request.getParameter("source");
        Date date = Date.valueOf(request.getParameter("date"));
        String description = request.getParameter("description");

        Transaction transaction = new Transaction(id, type, amount, source, date, description);
        transactionDAO.updateTransaction(transaction);
        response.sendRedirect("transaction");
    }

    private void deleteTransaction(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        transactionDAO.deleteTransaction(id);
        response.sendRedirect("transaction");
    }
}
