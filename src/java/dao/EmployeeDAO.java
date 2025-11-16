package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class EmployeeDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/erp_finance?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_EMPLOYEE_SQL
            = "INSERT INTO employees (first_name, last_name, email, phone, role, salary, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_EMPLOYEES
            = "SELECT * FROM employees";

    private static final String SELECT_EMPLOYEE_BY_ID
            = "SELECT * FROM employees WHERE employee_id = ?";

    private static final String UPDATE_EMPLOYEE_SQL
            = "UPDATE employees SET first_name=?, last_name=?, email=?, phone=?, role=?, salary=?, hire_date=? WHERE employee_id=?";

    private static final String DELETE_EMPLOYEE_SQL
            = "DELETE FROM employees WHERE employee_id=?";

    public EmployeeDAO() {
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
    public void insertEmployee(Employee employee) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhone());
            ps.setString(5, employee.getRole());
            ps.setDouble(6, employee.getSalary());
            ps.setDate(7, new java.sql.Date(employee.getHireDate().getTime()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<Employee> selectAllEmployees() {
        List<Employee> list = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_EMPLOYEES); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setRole(rs.getString("role"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setHireDate(rs.getDate("hire_date"));

                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // READ BY ID
    public Employee selectEmployeeById(int id) {
        Employee emp = null;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setRole(rs.getString("role"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setHireDate(rs.getDate("hire_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    // UPDATE
    public boolean updateEmployee(Employee employee) {
        boolean updated = false;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhone());
            ps.setString(5, employee.getRole());
            ps.setDouble(6, employee.getSalary());
            ps.setDate(7, new java.sql.Date(employee.getHireDate().getTime()));
            ps.setInt(8, employee.getEmployeeId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // DELETE
    public boolean deleteEmployee(int id) {
        boolean deleted = false;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_SQL)) {

            ps.setInt(1, id);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }
}
