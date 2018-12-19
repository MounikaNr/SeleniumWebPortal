package com.tripod.qa.pages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tripod.qa.base.BaseTest;

public class DeleteEnvironmentandApp extends BaseTest{
	public CreateProject createproject;
	
	@FindBy(xpath = "//li[contains(@class,'ng-scope active')]")
	WebElement settings;
	
	@FindBy(xpath = "//button[contains(text(),'Delete Environment')]")
	WebElement deleteEnvironment;
	
	@FindBy(xpath = "//div[contains(@class,'modal-footer ng-scope')]//button[contains(@class,'btn btn-default')][contains(text(),'Delete')]")
	WebElement deleteButton;
	
	@FindBy(xpath = "//h4[contains(text(),'No Environments found')]")
	WebElement noEnvironmentsFound;
	
	@FindBy(xpath = "//body/div[@id='app']/div[contains(@class,'app-aside hidden-xs bg-black')]/div[contains(@class,'aside-wrap ng-scope')]/div[contains(@class,'navi-wrap')]/nav[contains(@class,'navi ng-scope')]/ul[contains(@class,'nav ng-scope')]/li[contains(@class,'pull-right ng-scope')]/a[1]")
	WebElement settingsIcon;
	
	@FindBy(xpath = "//a[contains(text(),'Delete App')]")
	WebElement deleteApp;
		
	public DeleteEnvironmentandApp() {
		PageFactory.initElements(driver, this);
		createproject = new CreateProject();
	}
	public void deleteEnvironment() {
		createproject.settings.click();
		waitVisibility(deleteEnvironment);
		clickElementUsingJs(deleteEnvironment);
		waitVisibility(deleteButton);
		clickElementUsingJs(deleteButton);
	}
	public void verifyEnvironmentDeletedorNot() {
		sleep(10);
		Assert.assertEquals("No Environments found", noEnvironmentsFound.getText());
	}
	public void deleteApp() {
		sleep(5);
		clickElementUsingJs(settingsIcon);
		sleep(5);
		clickElementUsingJs(deleteApp);
		sleep(3);
		clickElementUsingJs(deleteButton);
	}
}
