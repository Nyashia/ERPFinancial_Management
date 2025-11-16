package controller;

import dao.EmployeeDAO;
import model.Employee;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet({
    "/employee",
    "/insert-employee",
    "/update-employee",
    "/delete-employee",
    "/edit-employee"
})
public class EmployeeServlet extends HttpServlet {

    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/edit-employee":
                    showEditForm(request, response);
                    break;
                case "/delete-employee":
                    deleteEmployee(request, response);
                    break;
                default:
                    listEmployees(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/insert-employee":
                    insertEmployee(request, response);
                    break;
                case "/update-employee":
                    updateEmployee(request, response);
                    break;
                default:
                    listEmployees(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------
    // LIST
    // -----------------------------
    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Employee> list = employeeDAO.selectAllEmployees();
        request.setAttribute("employeeList", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/employee.jsp");
        dispatcher.forward(request, response);
    }

    // -----------------------------
    // SHOW EDIT FORM
    // -----------------------------
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeDAO.selectEmployeeById(id);

        request.setAttribute("employee", employee);
        request.setAttribute("action", "form");

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/employee.jsp");
        dispatcher.forward(request, response);
    }

    // -----------------------------
    // INSERT
    // -----------------------------
    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Employee e = new Employee();
        e.setFirstName(request.getParameter("firstName"));
        e.setLastName(request.getParameter("lastName"));
        e.setEmail(request.getParameter("email"));
        e.setPhone(request.getParameter("phone"));
        e.setRole(request.getParameter("role"));
        e.setSalary(Double.parseDouble(request.getParameter("salary")));
        e.setHireDate(Date.valueOf(java.time.LocalDate.now()));

        employeeDAO.insertEmployee(e);

        response.sendRedirect("employee");
    }

    // -----------------------------
    // UPDATE
    // -----------------------------
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Employee e = new Employee();
        e.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
        e.setFirstName(request.getParameter("firstName"));
        e.setLastName(request.getParameter("lastName"));
        e.setEmail(request.getParameter("email"));
        e.setPhone(request.getParameter("phone"));
        e.setRole(request.getParameter("role"));
        e.setSalary(Double.parseDouble(request.getParameter("salary")));
        e.setHireDate(Date.valueOf(java.time.LocalDate.now()));

        employeeDAO.updateEmployee(e);

        response.sendRedirect("employee");
    }

    // -----------------------------
    // DELETE
    // -----------------------------
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        employeeDAO.deleteEmployee(id);

        response.sendRedirect("employee");
    }
}
