package com.cts.springboot.com.cts.mockupProject.glue;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportClass {

	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest extenttest;
	public static void startReport()
{
		String path=new File("src/main/resources/Reports").getAbsolutePath();
		htmlreporter=new ExtentHtmlReporter(path+"/ReportOn"+currentDateAndTime()+".html");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setDocumentTitle("CTS - Employee API Testing");
		htmlreporter.config().setReportName("CTS - Employee API Testing");
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
		
	

}
	
	 
		
	
	public static void endTest()
	{
		extent.flush();
	}

	
	public static String currentDateAndTime()
	{
		  DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MMM-dd  HH-mm-ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   return dateformat.format(now);  
	}
	
}
