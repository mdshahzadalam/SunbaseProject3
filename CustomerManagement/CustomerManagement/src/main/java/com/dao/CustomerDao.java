package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Customer;

public class CustomerDao {

	private Connection conn;

	public CustomerDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public CustomerDao() {
		// TODO Auto-generated constructor stub
	}

	public boolean registerCustomer(Customer c) {
		boolean f = false;

		try {
			String sql = "insert into customer_details(firstName,lastName,address,city,state,email,phone,password) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.setString(3, c.getAddress());
			ps.setString(4, c.getCity());
			ps.setString(5, c.getState());
			ps.setString(6, c.getEmail());
			ps.setString(7, c.getPhone());
			ps.setString(8, c.getPassword());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public List<Customer> getAllCustomer() {
		List<Customer> list = new ArrayList<Customer>();
		Customer c = null;
		try {

			String sql = "select * from customer_details order by id desc";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Customer();
				c.setId(rs.getInt(1));
				c.setFirstName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setAddress(rs.getString(4));
				c.setCity(rs.getString(5));
				c.setState(rs.getString(6));
				c.setEmail(rs.getString(7));
				c.setPhone(rs.getString(8));
				c.setPassword(rs.getString(9));
				list.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateCustomer(Customer c) {
		boolean f = false;

		try {
			String sql = "update customer_details set firstName=?,lastName=?,address=?,city=?,state=?,email=?, phone=?,password=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.setString(3, c.getAddress());
			ps.setString(4, c.getCity());
			ps.setString(5, c.getState());
			ps.setString(6, c.getEmail());
			ps.setString(7, c.getPhone());
			ps.setString(8, c.getPassword());
			ps.setInt(9, c.getId());
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public boolean deleteCustomer(int id) {
		boolean f = false;
		try {
			String sql = "delete from customer_details where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public Customer getCustomerById(int id) {

		Customer c = null;
		try {

			String sql = "select * from customer_details where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				c = new Customer();
				c.setId(rs.getInt(1));
				c.setFirstName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setAddress(rs.getString(4));
				c.setCity(rs.getString(5));
				c.setState(rs.getString(6));
				c.setEmail(rs.getString(7));
				c.setPhone(rs.getString(8));
				c.setPassword(rs.getString(9));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public boolean exists(String email) throws SQLException {
		String sql = "SELECT COUNT(*) FROM customer_details WHERE email = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}
		return false;
	}

	public void saveCustomer(Customer c) throws SQLException {
		String sql = "INSERT INTO customer_details (firstName, lastName, address, city, state, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, c.getFirstName());
			stmt.setString(2, c.getLastName());
			stmt.setString(3, c.getAddress());
			stmt.setString(4, c.getCity());
			stmt.setString(5, c.getState());
			stmt.setString(6, c.getEmail());
			stmt.setString(7, c.getPhone());
			stmt.executeUpdate();
		}
	}

}
