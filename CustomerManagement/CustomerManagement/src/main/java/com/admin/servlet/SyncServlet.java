package com.admin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.CustomerDao;
import com.entity.Customer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/sync")
public class SyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDao customerDao;

    public void init() {
        customerDao = new CustomerDao(null); // Assuming you handle connection in DAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        if (token != null && !token.isEmpty()) {
            try {
                URL url = new URL("https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=customer_list");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + token);

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // Assuming the response is in CSV format for simplicity
                String[] customerRecords = content.toString().split("\n");
                for (String record : customerRecords) {
                    String[] fields = record.split(",");
                    Customer customer = new Customer(
                            fields[0], // firstName
                            fields[1], // lastName
                            fields[2], // address
                            fields[3], // city
                            fields[4], // state
                            fields[5], // email
                            fields[6]  // phone
                    );

                    if (customerDao.exists(customer.getEmail())) {
                        customerDao.updateCustomer(customer);
                    } else {
                        customerDao.saveCustomer(customer);
                    }
                }
                response.sendRedirect("customer_list.jsp");
            } catch (Exception e) {
                throw new ServletException(e);
            }
        } else {
            response.sendRedirect("index.jsp?error=2");
        }
    }
}
