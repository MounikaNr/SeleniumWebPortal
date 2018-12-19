package com.tripod.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.tripod.qa.base.BaseTest;

public class GoogleLogin extends BaseTest{
	
	private MicrosoftLogin microsoftLogin;
	
	@FindBy(xpath = "//a[@ng-href='https://accounts.google.com/o/oauth2/v2/auth?access_type=offline&include_granted_scopes=true&redirect_uri=https://demo01.gendevs.com/tripod-backend/rest/users/google-redirect&response_type=code&client_id=66929743986-q3phqu6bcs1qr4ofrggn8fvc855um0t6.apps.googleusercontent.com&scope=openid profile https://www.googleapis.com/auth/userinfo.email&state=NEW_LOGIN']//img[@class='btn-icon']")
	WebElement loginWithGoogle;
	
	@FindBy(id = "identifierId")
	WebElement emailField;
	
	@FindBy(id = "identifierNext")
	WebElement identifierNext;
	
	@FindBy(name = "password")
	WebElement passwordField;
	
	@FindBy(id = "passwordNext")
	WebElement passwordNext;
	
	public GoogleLogin() {
		PageFactory.initElements(driver, this);
		microsoftLogin = new MicrosoftLogin();
	}
	public void loginWithGoogle() {
		loginWithGoogle.click();
		sleep(2);
		emailField.sendKeys(prop.getProperty("googleEmail"));
		identifierNext.click();
		passwordField.sendKeys(prop.getProperty("googlePassword"));
		passwordNext.click();
		microsoftLogin.createOrganisation();
	}
}
