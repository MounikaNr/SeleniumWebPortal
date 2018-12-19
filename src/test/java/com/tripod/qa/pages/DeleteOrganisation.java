package com.tripod.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tripod.qa.base.BaseTest;

public class DeleteOrganisation extends BaseTest{
	
	int size;
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;
	
	@FindBy(name = "search-input")
	WebElement searchOrganisation;
	
	@FindBy(xpath = "//div[@class='list-group']/a")
	WebElement organisationName;
	
	@FindBy(xpath = "//*[@id=\"mat-input-0\"]")
	WebElement companyNameField;
	
	@FindBy(xpath = "//button[@class='fab-del mat-fab mat-accent']")
	WebElement deleteButton;
	
	@FindBy(xpath = "/html/body/app-dashboard/div/main/div/app-organisations/div/div/div[2]/app-dialog/div[1]/form/div/div[3]/button")
	WebElement deleteOrganisationButton;
	
	public DeleteOrganisation() {
		PageFactory.initElements(driver, this);
	}
	
	public void deleteOrganisationData() {
		driver.get(prop.getProperty("webadminurl"));
		username.sendKeys(prop.getProperty("adminUserName"));
		password.sendKeys(prop.getProperty("adminPassword"));
		loginButton.click();
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='list-group']/a"));
		size = list.size();
		for(int i =1;i<size;i++) {
			sleep(1);
			WebElement organisationName = driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-organisations/div/div/div[1]/div[2]/div/a["+i+"]"));
			String organisationNameText = organisationName.getText();
			if(organisationNameText.contains("Gd Test")) {
				organisationName.click();
				clickElementUsingJs(deleteButton);
				companyNameField.sendKeys(organisationNameText);
				sleep(5);
				clickElementUsingJs(deleteOrganisationButton);
				sleep(4);
			}	
		}
	}

}
