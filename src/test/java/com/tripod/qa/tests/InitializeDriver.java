package com.tripod.qa.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.tripod.qa.base.BaseTest;

public class InitializeDriver extends BaseTest{
	
	
	public InitializeDriver() {
		super();
	}
	@BeforeSuite
	public void setUp(ITestContext context) {
		deletePrevoiusExtentReport();
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		intialization();
	}
	@AfterSuite
	public void tearDown(){
		driver.quit();
	}
}
