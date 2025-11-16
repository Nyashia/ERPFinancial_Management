<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>

<h1>Employee Management</h1>

<!-- ---------------------------------------------- -->
<!-- FORM: Shows only if ?action=form -->
<!-- ---------------------------------------------- -->
<c:if test="${param.action == 'form'}">

    <h2>${employee == null ? "Add Employee" : "Edit Employee"}</h2>

    <form action="${employee == null ? 'insert-employee' : 'update-employee'}" method="post">

        <c:if test="${employee != null}">
            <input type="hidden" name="employeeId" value="${employee.employeeId}" />
        </c:if>

        First Name: 
        <input type="text" name="firstName" value="${employee.firstName}" required><br/>

        Last Name: 
        <input type="text" name="lastName" value="${employee.lastName}" required><br/>

        Email: 
        <input type="email" name="email" value="${employee.email}"><br/>

        Phone: 
        <input type="text" name="phone" value="${employee.phone}"><br/>

        Role: 
        <input type="text" name="role" value="${employee.role}"><br/>

        Salary: 
        <input type="number" step="0.01" name="salary" value="${employee.salary}"><br/>

        <button type="submit">${employee == null ? "Add Employee" : "Update Employee"}</button>
    </form>

    <br/>
    <a href="employee">← Back to List</a>

</c:if>


<!-- ---------------------------------------------- -->
<!-- LIST: Shows only when NOT in form mode -->
<!-- ---------------------------------------------- -->
<c:if test="${param.action != 'form'}">

    <h2>Employee List</h2>

    <a href="employee?action=form">Add New Employee</a>

    <table border="1" width="100%" cellspacing="0" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>First</th>
            <th>Last</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Salary</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="emp" items="${employeeList}">
            <tr>
                <td>${emp.employeeId}</td>
                <td>${emp.firstName}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.phone}</td>
                <td>${emp.role}</td>
                <td>${emp.salary}</td>

                <td>
                    <a href="edit-employee?id=${emp.employeeId}">Edit</a> |
                    <a href="delete-employee?id=${emp.employeeId}">Delete</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</c:if>

</body>
</html>
