package com.tripod.qa.pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.tripod.qa.base.BaseTest;

public class MicrosoftLogin extends BaseTest{
	
	private LoginPage loginpage;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[3]/form/div[2]/center/a[1]")
	WebElement microsoftLogo;
	
	@FindBy(id = "i0116")
	WebElement emailTextField;
	
	@FindBy(id = "idSIButton9")
	WebElement nextButton;
	
	@FindBy(id = "i0118")
	WebElement passwordTextField;
	
	@FindBy(id = "idSIButton9")
	WebElement signInButton;
	
	@FindBy(xpath = "//select[@class='form-control no-border ng-pristine ng-untouched ng-invalid ng-invalid-required']")
	WebElement selectCountry;
	
	@FindBy(name = "companyName")
	WebElement companyNameField;
	
	@FindBy(xpath = "//div[@class='m-b-lg col-md-6']//div[5]//div[1]")
	WebElement SignUpButton;
	
	@FindBy(id = "idBtn_Accept")
	WebElement AcceptPermissions;
	
	@FindBy(id = "idBtn_Deny")
	WebElement DenyPermissions;
	
	@FindBy(xpath = "//span[@class='hidden-sm hidden-md ng-binding']")
	WebElement verifyUserName;
	
	public MicrosoftLogin() {
		PageFactory.initElements(driver, this);
		loginpage = new LoginPage();
	}
	
	public void microsoftLogin() {
		microsoftLogo.click();
		sleep(2);
		emailTextField.sendKeys(prop.getProperty("microsoftEmail"));
		nextButton.click();
		sleep(2);
		passwordTextField.sendKeys(prop.getProperty("microsoftPassword"));
		signInButton.click();
		driver.get(prop.getProperty("url"));
		driver.navigate().refresh();
	}
	public void createOrganisation() {
		if(waitVisibility(companyNameField)) {
			Select chooseCountry = new Select(selectCountry);
			chooseCountry.selectByVisibleText("India");
			companyNameField.sendKeys("Gd"+new Date().getTime());
			SignUpButton.click();	
		}
	}
}
