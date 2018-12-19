package com.tripod.qa.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.LoginPage;
import com.tripod.qa.pages.Logout;

public class LogoutTest extends InitializeDriver{
	public LoginPage loginpage;
	public Logout logout;
	public LogoutTest(){
		super();
	}
	@BeforeTest
	public void initialize() {
		loginpage = new LoginPage();
		logout = new Logout();
	}
	@Test(priority = 1)
	public void Logout() {
		//this is to run invidually
		loginpage.doLogin();
		logout.logoutUser();
	}
}
