package com.cts.springboot.com.cts.mockupProject;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.springboot.com.cts.mockupProject.glue.ExtentReportClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes= {Application.class,BDDRunnerClass.class},
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(plugin= {"pretty"},features="src/test/java/EmployeeAPITesting.feature",monochrome = true)

public class BDDRunnerClass {

	
	
	  @BeforeClass public static void beforeClass() {
	  ExtentReportClass.startReport(); 
	  
	  }
	  
	  @AfterClass public static void afterClass() { 
		  ExtentReportClass.endTest();
	  }
	 

	
}
