package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admin.servlet.Search;

public class SearchDao {
	
	private Connection conn;

	public SearchDao(Connection conn) {
		this.conn = conn;
	}

	public List<Search> getSearch(String searchBy, String searchTerm) throws SQLException {
		List<Search> searches = new ArrayList<>();

		// Use PreparedStatement to prevent SQL injection
		String sql = "SELECT * FROM customer_details WHERE " + searchBy + " LIKE ?";
		
		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			preparedStatement.setString(1, "%" + searchTerm + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
				String city = resultSet.getString("city");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				searches.add(new Search(id, firstName, city, email, phone));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e; // Rethrow the exception after logging it
		}
		
		return searches;
	}
}
