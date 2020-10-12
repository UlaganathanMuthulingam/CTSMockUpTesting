package com.cts.springboot.com.cts.mockupProject;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EmployeeController {

	
	@GetMapping("/Employees")
	public String welcomeMessage()
	{
		return "Hello,All API Tests are successful";
	}
	
	
	@PutMapping("/employee")
	
	public ResponseEntity<UpdateMessage> updateRecord(@RequestParam(value="employeename", required=true) String employeeName,
			@RequestBody (required=true) Resources res,
			HttpServletRequest status)
	{
		ReadExcelData readExcel=new ReadExcelData();		
		UpdateMessage update=new UpdateMessage();
		readExcel.setResources(res);
		return readExcel.updateDetails(update,employeeName);
	}
	
	@GetMapping("/employee/login")
	public ResponseEntity<UpdateMessage> emplogin(@RequestParam(value="employeename") String employeeName,
			@RequestParam(value="password") String password)
	{
		
		ReadExcelData readExcel=new ReadExcelData();
		Resources resource=new Resources(employeeName,password);
		UpdateMessage update=new UpdateMessage();
		resource.setEmployeename(employeeName);
		resource.setPassword(password);		
		readExcel.setResources(resource);
		return readExcel.loginAPICall(update,employeeName,password);
	}
	
	
	
	@GetMapping("/employee")
	public ResponseEntity<?> getempDetails(@RequestParam(value="employeename") String employeeName)
	{
		
		ReadExcelData readExcel=new ReadExcelData();
		Resources resource=new Resources();
		UpdateMessage update=new UpdateMessage();
		resource.setEmployeename(employeeName);	
		readExcel.setResources(resource);
		return readExcel.getEmployeeAPICall(update,employeeName);
	}
	
	
	
	@DeleteMapping("/employee")
	public ResponseEntity<UpdateMessage> empDelete(@RequestParam(value="employeename") String employeeName) throws Exception
	{
		ReadExcelData readExcel=new ReadExcelData();
		Resources resource=new Resources(employeeName);
		UpdateMessage update=new UpdateMessage();
		resource.setEmployeename(employeeName);
		return readExcel.deleteRecord(update,employeeName);
		
	}
	
	@PostMapping("/employee")
	public ResponseEntity<UpdateMessage> createEmployee(@RequestBody (required=true) Resources response) throws Exception
	{
		ReadExcelData readExcel=new ReadExcelData();		
		UpdateMessage update=new UpdateMessage();
		readExcel.setResources(response);
		return readExcel.createUser(update);
		
	}

	
}
