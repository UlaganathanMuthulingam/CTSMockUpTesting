package com.cts.springboot.com.cts.mockupProject.glue;

import org.junit.Assert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.aventstack.extentreports.Status;
import com.cts.springboot.com.cts.mockupProject.Resources;

import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.Json;
import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Stepdef {
	
	@LocalServerPort
	private int port;
	
	private String postURL="http://localhost";
	private RestAssured restAssobj;
	private RequestSpecification request;	
	private Response response;

	@Before
	public void beforeScenario(Scenario scenario)
	{
		ExtentReportClass.extenttest=ExtentReportClass.extent.createTest(scenario.getName());
		
	}

	
	
	@Given("Pass employee parameter {string} password paramter {string}")
	public void setloginParameter(String employeename,String password)
	{
		RestAssured.baseURI=postURL+":"+port;
		ExtentReportClass.extenttest.log(Status.INFO, "Rest base url -"+restAssobj.baseURI);
		ExtentReportClass.extenttest.log(Status.INFO, "Passing employee paramter as "+employeename+" Passing Password parameter as - "+password);
		request=RestAssured.given().param("employeename", employeename).param("password", password);
		
	}
	@When("hit GET employee service endPoint resource as {string}")
	public void setResource(String res)
	{
		response=request.get(res);
		ExtentReportClass.extenttest.log(Status.PASS, "Hitting the end point success");


	}


	@When("hit Post employee service endPoint resource as {string}")
	public void postMethod(String resource)
	{
		response=request.post(resource);
		ExtentReportClass.extenttest.log(Status.PASS, "Hitting the end point success");
	}
	
	
	@When("hit put employee service endPoint resource as {string}")
	public void createRecordMethod(String resource)
	{
		response=request.put(resource);
		ExtentReportClass.extenttest.log(Status.PASS, "Hitting the end point success");
	}
	
	
	
	@When("hit delete employee service endPoint resource as {string}")
	public void deleteRecordMethod(String resource)
	{
		response=request.delete(resource);
		ExtentReportClass.extenttest.log(Status.PASS, "Hitting the end point success");
	}

	
	
	
	@Then("should return response code as {int}")
	public void successResponseCode(int statusCode)
	{

		RestAssuredValidationClass.verifyStatus(response.getStatusCode(), statusCode);
	}
	
	@And("successMessage should be displayed as {string}")
	public void successMessage(String successMessage)
	{
		RestAssuredValidationClass.verifySuccessMessage(successMessage, response.body().path("responseMessage").toString());

	}

	
	@Given("Pass employee parameter as {string}")
	public void getResponse(String empName)
	{
		request=RestAssured.given().param("employeename", empName);
	}
	
	@And("print the response")
	public void responseMessage()
	{
		ExtentReportClass.extenttest.log(Status.PASS,response.getBody().asString());
		System.out.println(response.getBody().asString());

	}
	
	@And("verify {string} with reponse field {string}")
	public void verifyEmployeeName(String expected,String actual)
	{
		
		RestAssuredValidationClass.verifySuccessMessage(expected, response.body().path(actual).toString());
	}
	
	
	@Given("Pass new record json body")
	public void passJsonbodyReq()
	{
		Resources resobj=new Resources();
		resobj.setId(12);
		resobj.setEmployeename("NewaddedRecord");
		resobj.setFirstName("NewlyFirstName");
		resobj.setLastName("NewLastName");
		resobj.setEmail("newcheck@test.com");
		resobj.setPassword("NewPassword");
		resobj.setPhone("9999999999");
		resobj.setEmployeeStatus(1);
		request=RestAssured.given().header("Content-Type","application/json").body(resobj);
		
		
	}
	
	@Given("Pass new record json body with parameter employee name as {string}")
	public void jsonbodyWithParameter(String employeeName)
	{
		Resources putmethod=new Resources();
		putmethod.setId(4);
		putmethod.setEmployeename("emp4");
		putmethod.setFirstName("After update");
		putmethod.setLastName("NewLastName");
		putmethod.setEmail("newcheck@test.com");
		putmethod.setPassword("NewPassword");
		putmethod.setPhone("9999999999");
		putmethod.setEmployeeStatus(1);
		request=RestAssured.given().header("Content-Type","application/json").param("employeename", employeeName).body(putmethod);
		
	}
	
}
