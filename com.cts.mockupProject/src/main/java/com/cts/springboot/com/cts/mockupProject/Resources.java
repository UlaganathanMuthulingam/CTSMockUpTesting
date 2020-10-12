package com.cts.springboot.com.cts.mockupProject;

public class Resources {

	
	private int id;
	private String employeename;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private int employeeStatus;
	
	public Resources(int id, String employeeName, String firstName, String lastName, String email, String password,
			String phone, int status) {
		this.id = id;
		this.employeename = employeeName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.employeeStatus = status;
	}
	
	
	public Resources(String employeeName,String password)
	{
		this.employeename=employeeName;
		this.password=password;
	}
	
	public Resources(String employeeName)
	{
		this.employeename=employeeName;
		
	}
	
	
	public Resources() {
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmployeename() {
		return employeename;
	}


	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}


	public int getEmployeeStatus() {
		return employeeStatus;
	}


	public void setEmployeeStatus(int employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	


}
