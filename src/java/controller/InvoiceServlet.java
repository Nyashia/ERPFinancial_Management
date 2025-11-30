/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.InvoiceDAO;
import model.Invoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/invoices/*")
public class InvoiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InvoiceDAO invoiceDAO;

    public void init() {
        invoiceDAO = new InvoiceDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) action = "";

        switch (action) {
            case "/new":
                request.getRequestDispatcher("/jsp/invoice.jsp").forward(request, response);
                break;
            case "/edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Invoice invoiceEdit = invoiceDAO.selectInvoice(idEdit);
                request.setAttribute("invoice", invoiceEdit);
                request.getRequestDispatcher("/jsp/invoice.jsp").forward(request, response);
                break;
            case "/delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                invoiceDAO.deleteInvoice(idDelete);
                response.sendRedirect(request.getContextPath() + "/invoices");
                break;
            default: // list all
                List<Invoice> listInvoice = invoiceDAO.selectAllInvoices();
                request.setAttribute("listInvoice", listInvoice);
                request.getRequestDispatcher("/jsp/invoice.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int invoiceId = request.getParameter("invoiceId") != null ? Integer.parseInt(request.getParameter("invoiceId")) : 0;
        int supplierId = Integer.parseInt(request.getParameter("relatedSupplierId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String status = request.getParameter("status");
        java.sql.Date issueDate = java.sql.Date.valueOf(request.getParameter("issueDate"));
        java.sql.Date dueDate = java.sql.Date.valueOf(request.getParameter("dueDate"));

        Invoice invoice = new Invoice(invoiceId, supplierId, amount, status, issueDate, dueDate);

        if (invoiceId == 0) {
            invoiceDAO.insertInvoice(invoice);
        } else {
            invoiceDAO.updateInvoice(invoice);
        }

        response.sendRedirect(request.getContextPath() + "/invoices");
    }
}

