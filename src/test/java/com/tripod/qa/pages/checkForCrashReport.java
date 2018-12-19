package com.tripod.qa.pages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tripod.qa.base.BaseTest;

public class checkForCrashReport extends BaseTest{
	
	
	private RemoteWebDriver appiumDriver;
	private CreateProject createproject;
	
	Date  date;
	public String currentTime;

	
	@FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div/nav/div[3]")
	WebElement environmentDropDown;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div/nav/div[3]/div/div[1]/a/div/div/span[2]")
	WebElement qualityEnvironment;
	
	public checkForCrashReport() {
		createproject = new CreateProject();
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnProject(){
		sleep(6);
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"app\"]/div[3]/div[2]/div/div[2]/div/div/div[1]/div"));
		System.out.println(list.size());
		int size = list.size();
		String projectName = "Best Practice Tripod Tests";
		for(int i=1;i<=size;i++) {
			if(projectName.equalsIgnoreCase(driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div[2]/div/div[2]/div/div/div[1]/div["+i+"]/div/div[1]/footer/div/div[1]/a[2]/small")).getText())) {
				WebElement element = driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div[2]/div/div[2]/div/div/div[1]/div["+i+"]"));
				element.isDisplayed();
				element.click();
				break;
			}
		}
		
	}
	public void viewForCrasghes() {
		waitVisibility(createproject.crashes);
		createproject.crashes.click();
		sleep(5);
		environmentDropDown.click();
		qualityEnvironment.click();
		waitVisibility(createproject.crashes);
		createproject.crashes.click();
		sleep(3);
		this.checkReport();
	}
	public void checkReport() {
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"app\"]/div[3]/div[2]/div[2]/div[1]/div/div[1]/div[2]/div/div/div[1]/div/a"));
		list.size();
		for(int i=1;i<=list.size();i++) {
			WebElement crashReport = driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div[2]/div[2]/div[1]/div/div[1]/div[2]/div/div/div[1]/div/a["+i+"]/div/div[2]/div[2]/span[2]"));
			crashReport.click();
		}
	}
}
