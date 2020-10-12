package com.cts.springboot.com.cts.mockupProject;

public class EmployeeDetailsObject {
	private Resources resourceObj;

	public EmployeeDetailsObject(Resources resourceObj) {
		this.resourceObj = resourceObj;
	}

	public EmployeeDetailsObject() {
		
	}
	
	public Resources getResourceObj() {
		return resourceObj;
	}

	public void setResourceObj(Resources resourceObj) {
		this.resourceObj = resourceObj;
	}

}
