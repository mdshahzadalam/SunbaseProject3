
<%@page import="com.dao.CustomerDao"%>
<%@page import="com.entity.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.db.DBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search List</title>
<%@include file="components/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}

body {
	background-color: #E3F4F4;
}
</style>
</head>
<body>
	<%@include file="components/navbar.jsp"%>

	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Search List</p>
						<c:if test="${not empty errorMsg}">
							<p class="fs-3 text-center text-danger">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<c:if test="${not empty succMsg}">
							<div class="fs-3 text-center text-success" role="alert">${succMsg}</div>
							<c:remove var="succMsg" scope="session" />
						</c:if>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">First Name</th>
									<th scope="col">Last Name</th>
									<th scope="col">Address</th>
									<th scope="col">City</th>
									<th scope="col">State</th>
									<th scope="col">Email</th>
									<th scope="col">Phone Number</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>
								<%
								CustomerDao dao = new CustomerDao(DBConnect.getConn());
								List<Customer> list = dao.getAllCustomer();
								for (Customer c : list) {
								%>
								<tr>
									<td><%=c.getFirstName()%></td>
									<td><%=c.getLastName()%></td>
									<td><%=c.getAddress()%></td>
									<td><%=c.getCity()%></td>
									<td><%=c.getState()%></td>
									<td><%=c.getEmail()%></td>
									<td><%=c.getPhone()%></td>

									<td><a href="deleteCustomer?id=<%=c.getId()%>"
										class="btn btn-sm btn-danger"><i class="fa-solid fa-trash"></i></a>
										<a href="edit_customer.jsp?id=<%=c.getId()%>"
										class="btn btn-sm btn-primary"><i
											class="fa-solid fa-pen-to-square"></i></a></td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>