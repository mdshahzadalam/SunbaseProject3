
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
<title>Edit Customer Details</title>
<%@include file="components/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
body{
    background-color:#E3F4F4;
}
</style>
</head>
<body>
<%@include file="components/navbar.jsp"%>

<div class="container-fluid p-3">
		<div class="row">

			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Edit Doctor Details</p>
						<c:if test="${not empty errorMsg}">
							<p class="fs-3 text-center text-danger">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<c:if test="${not empty succMsg}">
							<div class="fs-3 text-center text-success" role="alert">${succMsg}</div>
							<c:remove var="succMsg" scope="session" />
						</c:if>

						<%
						int id = Integer.parseInt(request.getParameter("id"));
						CustomerDao dao = new CustomerDao(DBConnect.getConn());
						Customer d= dao.getCustomerById(id);
						
						%>


						<form action="updateCustomer" method="post">
							<div class="mb-3">
								<label class="form-label">First Name</label> <input type="text"
									required name="firstName" class="form-control"
									value="<%=d.getFirstName()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Last Name</label> <input type="text"
									required name="lastName" class="form-control"
									value="<%=d.getLastName()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Address</label> <input required
									value="<%=d.getAddress()%>" name="address"
									type="text" class="form-control">
							</div>
							
							<div class="mb-3">
								<label class="form-label">City</label> <input required
									value="<%=d.getCity()%>" name="city"
									type="text" class="form-control">
							</div>
							
							<div class="mb-3">
								<label class="form-label">State</label> <input required
									value="<%=d.getState()%>" name="state"
									type="text" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input type="text"
									value="<%=d.getEmail()%>" required name="email"
									class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Phone No</label> <input type="text"
									value="<%=d.getPhone()%>" required name="phone"
									class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input required
									value="<%=d.getPassword()%>" name="password" type="text"
									class="form-control">
							</div>
							<input type="hidden" name="id" value="<%=d.getId()%>">

							<button type="submit" class="btn btn-primary col-md-12">Update</button>
						</form>
					</div>
				</div>
			</div>




		</div>
	</div>

</body>
</html>