package com.cts.springboot.com.cts.mockupProject.glue;

import org.assertj.core.api.SoftAssertions;

import com.aventstack.extentreports.Status;

public class RestAssuredValidationClass {
	
	SoftAssertions softAssertions = new SoftAssertions();
	
	
	public static boolean verifyStatus(int actualStatus,int expectedStatus)
	{
		
		if(actualStatus==expectedStatus)
		{
			ExtentReportClass.extenttest.log(Status.PASS, "got the expected status code - "+actualStatus);
			return true;
		}
		else
		{
			ExtentReportClass.extenttest.log(Status.FAIL, "wroung reponse code, Expected "+expectedStatus+"  but acutal "+actualStatus);
			return false;
		}

	}

	
	public static boolean verifySuccessMessage(String expected,String actual)
	{
	
		
		if(expected.equalsIgnoreCase(actual))
		{
			ExtentReportClass.extenttest.log(Status.PASS, "got the expected success Message - "+actual);
			return true;
		}
		else
		{
			ExtentReportClass.extenttest.log(Status.FAIL, "success message wrong, Expected success message is - "+expected+"  but acutal success message is "+actual);
			return false;
		}
		
	}

		public static boolean verifyText(String expected,String actual)
		{
		
			
			if(expected.equalsIgnoreCase(actual))
			{
				ExtentReportClass.extenttest.log(Status.PASS, "got the expected text - "+actual);
				return true;
			}
			else
			{
				ExtentReportClass.extenttest.log(Status.FAIL, "details mismatched, Expected  - "+expected+"  but acutal - "+actual);
				return false;
			}
			
			
		
	}
	
}
