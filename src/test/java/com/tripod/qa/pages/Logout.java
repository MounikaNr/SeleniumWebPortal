package com.tripod.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tripod.qa.base.BaseTest;

public class Logout extends BaseTest{
	@FindBy(xpath = "//li[contains(@class,'dropdown')]")
	WebElement profileDropDown;
	
	@FindBy(xpath = "//a[contains(@ng-click,'signOut()')]")
	WebElement logoutOption;
	
	public Logout() {
		PageFactory.initElements(driver, this);
	}
	public void logoutUser() {
		sleep(5);
		profileDropDown.click();
		sleep(5);
		waitVisibility(logoutOption);
		clickElementUsingJs(logoutOption);
	}
}
