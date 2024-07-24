package com.admin.servlet;

public class Search {
	
	private int id;
    private String firstName;
    private String city;
    private String email;
    private String phone;

    public Search(int id, String firstName, String city, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.city = city;
        this.email = email;
        this.phone = phone;
    }

	public Search(String searchBy, String searchTerm) {
		// TODO Auto-generated constructor stub
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
    
    


}
