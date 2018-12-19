package com.tripod.qa.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.SignUp;
import com.tripod.qa.pages.LoginPage;
import com.tripod.qa.pages.Logout;

public class SignUpTest extends InitializeDriver{
	public SignUp signUp;
	public LoginPage loginPage;
	public Logout logout;
	public SignUpTest() {
		super();
	}
	@BeforeTest
	public void initialize() {
		signUp = new SignUp();
		loginPage = new LoginPage();
		logout = new Logout();
	}
	@Test(priority = 1)
	public void signUpProcess() {
		signUp.createGuerrillaMail();
		signUp.createAccount();
		signUp.validateMail();
		signUp.loginWithGuerrillaMail();
		logout.logoutUser();
		loginPage.doLogin();
		logout.logoutUser();
	}
}
