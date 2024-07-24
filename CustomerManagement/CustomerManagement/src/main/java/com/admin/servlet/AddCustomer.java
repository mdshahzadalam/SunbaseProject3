package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CustomerDao;
import com.db.DBConnect;
import com.entity.Customer;


@WebServlet("/addCustomer")
public class AddCustomer extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String address = req.getParameter("address");

			String city = req.getParameter("city");

			String state = req.getParameter("state");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String password = req.getParameter("password");

			Customer c = new Customer(firstName, lastName, address, city, state, email, phone, password);

			CustomerDao dao = new CustomerDao(DBConnect.getConn());
			HttpSession session = req.getSession();

			if (dao.registerCustomer(c)) {
				session.setAttribute("succMsg", "Customer Added Sucessfully..");
				resp.sendRedirect("customer_list.jsp");
			} else {
				session.setAttribute("errorMsg", "something wrong on server");
				resp.sendRedirect("customer_list.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
