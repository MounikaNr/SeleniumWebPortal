package com.tripod.qa.pages;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tripod.qa.base.BaseTest;

public class LoginPage extends BaseTest{
	
	@FindBy(css= "input[name=email]")
	WebElement usernameFiled;
	
	@FindBy(css="input[name=password]")
	WebElement passwordFiled;
	
	@FindBy(css="button.text-white")
	WebElement loginButton;
	
	@FindBy(xpath = "//span[contains(@class,'hidden-folded m-l-xs ng-binding')]")
	WebElement tripodText;
	
	@FindBy(xpath = "//div[contains(@class,'text-danger wrapper text-center ng-binding')]")
	WebElement alertText;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	public void doLogin() {
		
		try {
			this.login(SignUp.getUserMailId("userMailId"), prop.getProperty("password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		waitVisibility(tripodText);
		Assert.assertEquals("Tripod", tripodText.getText());
	}
	public void loginWithInvalidEmail() {
		driver.get(prop.getProperty("url"));
		this.login("username@gmail.com", prop.getProperty("password"));
		Assert.assertTrue(loginButton.isEnabled());
	}
	public void loginWithInvalidPassword() {
		try {
			this.login(SignUp.getUserMailId("userMailId"),"sample");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sleep(5);
		Assert.assertEquals("The user name or password you entered is incorrect.", alertText.getText());
	}
	public void loginWithInvalidCredintials() {
		this.login("username@gma.com","sample");
		Assert.assertEquals("The user name or password you entered is incorrect.", alertText.getText());
	}
	public void login(String username,String password) {
		usernameFiled.clear();
		passwordFiled.clear();
		usernameFiled.sendKeys(username);
		passwordFiled.sendKeys(password);
		waitVisibility(loginButton);
		clickElementUsingJs(loginButton);
	}
	
}
