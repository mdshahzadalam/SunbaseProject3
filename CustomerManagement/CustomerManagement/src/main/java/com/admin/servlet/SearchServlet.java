package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dao.SearchDao;
import com.db.DBConnect;
import com.entity.Customer;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
	  try {

			String searchBy = req.getParameter("searchBy");
			String searchTerm = req.getParameter("searchTerm");
			
			

			Search c = new Search(searchBy, searchTerm);

			SearchDao dao = new SearchDao(DBConnect.getConn());
			HttpSession session = req.getSession();

			if (dao.getSearch(searchBy, searchTerm) != null) {
				session.setAttribute("succMsg", "Customer Searched Sucessfully..");
				resp.sendRedirect("search.jsp");
			} else {
				session.setAttribute("errorMsg", "something wrong on server");
				resp.sendRedirect("customer_list.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	  
  }



