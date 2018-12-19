package com.tripod.qa.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.GoogleLogin;

public class GoogleLoginTest extends InitializeDriver{
	
	private GoogleLogin googleLogin;
	
	public GoogleLoginTest() {
		super();
	}
	
	@BeforeTest
	public void initialize() {
		googleLogin = new GoogleLogin();
	}
	
	@Test
	public void loginwithGoogleAccount() {
		googleLogin.loginWithGoogle();
	}
	

}
