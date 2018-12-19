package com.tripod.qa.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.LoginPage;
import com.tripod.qa.pages.Logout;
import com.tripod.qa.pages.MicrosoftLogin;

public class MicrosoftLoginTest extends InitializeDriver{
	
	private Logout logout;
	private MicrosoftLogin microsoftLogin;
	private LoginPage loginpage;
	
	public MicrosoftLoginTest() {
		super();
	}
	@BeforeTest
	public void initialize() {
		logout = new Logout();
		loginpage = new LoginPage();
		microsoftLogin = new MicrosoftLogin();
	}
	@Test
	public void microsoftLogin() {
		microsoftLogin.microsoftLogin();
		loginpage.doLogin();
		logout.logoutUser();
	}
}
