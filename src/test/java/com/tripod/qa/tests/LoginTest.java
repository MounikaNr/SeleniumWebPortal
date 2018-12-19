package com.tripod.qa.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.tripod.qa.pages.LoginPage;
import com.tripod.qa.pages.Logout;

public class LoginTest extends InitializeDriver{
	public LoginPage loginpage;
	public Logout logout;
	public ExtentTest extentTest;
	public ExtentReports extent;

	public LoginTest(){
		super();
	}
	@BeforeTest
	public void initialize() {
		loginpage = new LoginPage();
		logout = new Logout();
	}
	@Test(priority=1)
	public void doLoginWithInvalidEmail() {
		loginpage.loginWithInvalidEmail();
	}
	@Test(priority=2)
	public void doLoginWithInvalidPassword() {
		loginpage.loginWithInvalidPassword();
	}
	@Test(priority=3)
	public void doLoginWithInvalidCredintials() {
		loginpage.loginWithInvalidCredintials();
	}
	@Test(priority=4)
	public void doLogin() {
		loginpage.doLogin();
	}
	@AfterTest
	public void logout() {
		//this is to run invidually
		logout.logoutUser();
	}
}
