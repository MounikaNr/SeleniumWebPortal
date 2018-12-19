package com.tripod.qa.pages;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.tripod.qa.base.BaseTest;

public class CreateProject extends BaseTest{
	String projectName;	
	int size;
	public static String guerrillaMail;
	public String replaceUser;
	
	Date date = new Date();
	String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	String environmentName = "com.usernae.deleteme.develop";
	
	@FindBy(xpath = "//li[@class='ng-scope']//a[@ng-click='addApp()']")
	WebElement newProject;
	
	@FindBy(css  = "i.fa.fa-fw.fa-plus.visible-xs-inline-block")
	WebElement createIosProject;
	
	@FindBy(name = "appName")
	WebElement projectNameField;
	
	@FindBy(name = "appkey")
	WebElement appKeyField;
	
	@FindBy(xpath = "//input[@value='ANDROID']")
	WebElement selectAndroidPlatform;
	
	@FindBy(xpath = "//input[@value='IOS']")
	WebElement selectIosPlatform;
	
	@FindBy(id = "fileInput")
	WebElement chooseFile;
	
	@FindBy(name = "envName")
	WebElement envNameField;
	
	@FindBy(name = "appIdentifier")
	WebElement envIdentifierField;
	
	@FindBy(xpath = "//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath = "//body/div[@id='app']/div[contains(@class,'app-aside hidden-xs bg-black')]/div[contains(@class,'aside-wrap ng-scope')]/div[contains(@class,'navi-wrap')]/nav[contains(@class,'navi ng-scope')]/ul[contains(@class,'nav ng-scope')]/li[contains(@class,'pull-right ng-scope')]/a[1]")
	WebElement settingsIcon;
	
	@FindBy(xpath = "//a[contains(text(),'Permissions')]")
	WebElement permissionsTab;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Select user')]")
	WebElement selectUser;
	
	
	
	@FindBy(xpath = "//div[contains(@class,'panel-group')]//div[2]//div[1]//h4[1]//a[1]//div[1]//span[2]//i[1]")
	WebElement userPermissions;
	
	@FindBy(xpath = "//button[contains(text(),'Add Users')]")
	WebElement addUsersButton;
	
	@FindBy(xpath = "//body/div[@id='app']/div[contains(@class,'app-content ng-scope')]/div[contains(@class,'app-content-body fade-in-up ng-scope')")
	WebElement listOfprojects;
	
	@FindBy(xpath = "//span[contains(text(),'Insights')]")
	WebElement insights;
	
	@FindBy(xpath = "//span[contains(text(),'Crashes')]")
	WebElement crashes;
	
	@FindBy(xpath = "//span[contains(text(),'Pictures')]")
	WebElement pictures;
	
	@FindBy(xpath = "//span[contains(text(),'Videos')]")
	WebElement videos;
	
	@FindBy(xpath = "//span[contains(text(),'Builds')]")
	WebElement builds;
	
	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	WebElement settings;
		
	@FindBy(xpath = "//input[contains(@placeholder,'Environment Name')]")
	WebElement editEnvironmentName;
	
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitButton;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[4]/div[2]/div[2]/div[2]/div[1]/div[2]/div[2]/div/div/div[1]/div[2]/div/span[1]/strong")
	WebElement actualEnvironmentName;
	
	@FindBy(xpath ="//*[@id=\"app\"]/div[4]/div[2]/div/div/div[2]/div[2]/div[1]/div[1]/div[1]/h4")
	WebElement unauthorizedAccess;
	
	@FindBy(xpath = "//span[contains(text(),'Users')]")
	WebElement users;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div[2]/div[1]/div[2]/div[3]/a")
	WebElement inviteUser;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/div/form/div[2]/div[1]/div/div/input")
	WebElement usernameField;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/div/form/div[2]/div[2]/div/div/input")
	WebElement firstNameField;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/div/form/div[2]/div[3]/div/div/input")
	WebElement lastNameField;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/div/form/div[2]/div[4]/div/div/input")
	WebElement phoneNumberField;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/div/form/div[2]/div[5]/div/div/input")
	WebElement designationField;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/div/form/div[2]/div[6]/div/div/select")
	WebElement role;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div[2]/div[2]/div[2]/div/div[1]/div/a[2]")
	WebElement done;
	
	public CreateProject() {
		PageFactory.initElements(driver, this);
	}
	
	public void createNewProject(String type) {
		waitVisibility(newProject);
		clickElementUsingJs(newProject);
		projectName = "Testing"+new Date().getTime();
		projectNameField.sendKeys(projectName);
		appKeyField.sendKeys("Testing"+new Date().getTime());
		if(type == "ANDROID") 
			clickElementUsingJs(selectAndroidPlatform);
		else if(type == "IOS")
			clickElementUsingJs(selectIosPlatform);
		chooseFile.sendKeys(System.getProperty("user.dir")+"/src/main/resources/download.png");
		envNameField.sendKeys("com.usernae.deleteme"+new Date().getTime());
		envIdentifierField.sendKeys("com.usernae.deleteme.develop"+new Date().getTime());
		clickElementUsingJs(addButton);
		sleep(9);
	}
	public void addPermissionsToUser(String permissionType,boolean addPermission) {
		waitVisibility(settingsIcon);
		clickElementUsingJs(settingsIcon);
		waitVisibility(permissionsTab);
		permissionsTab.click();
		waitVisibility(selectUser);
		selectUser.click();
		deleteSystem(replaceUser);
		addUsersButton.click();
		if(addPermission) {
			userPermissions.click();
			for(int i =1;i<7;i++) {
				sleep(2);
				if(permissionType.equalsIgnoreCase("VIEW"))			
					driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div/div/div[3]/fieldset/div/form/div[2]/div/accordion/div/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td["+i+"]/div[1]/input")).click();
				else if(permissionType.equalsIgnoreCase("EDIT"))
					driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div/div/div[3]/fieldset/div/form/div[2]/div/accordion/div/div[2]/div[2]/div/div/div/table/tbody/tr/td["+i+"]/div[2]/input")).click();
			}
		}
	}
	public void verifyCreatedProject() {
		size = this.getListSize();
		System.out.println(size);
		sleep(2);
		for(int i = size;i<= size;i++) {
			WebElement project = driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div/div[3]/div/div/div[1]/div["+i+"]"));
			waitVisibility(project);
			String projectNameText = driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div/div[3]/div/div/div[1]/div["+i+"]/div/div[1]/footer/div/div[1]/a[2]/small")).getText();
			if(projectName.equalsIgnoreCase(projectNameText)) {
				Assert.assertEquals(projectName, driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div/div[3]/div/div/div[1]/div["+i+"]/div/div[1]/footer/div/div[1]/a[2]/small")).getText());
				project.click();
				sleep(4);
			}
			else {
				Assert.assertFalse(projectName.equalsIgnoreCase(projectNameText), "Project is deleted");
				sleep(3);
			}
				
		}
	}
	public void verifyCreatedProjectName(String projectName) {
		sleep(10);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div[2]/div/div[2]/div/div/div[1]/div[5]"));
		clickElementUsingJs(element);
	}
	public void verifyUserAbleToModifyDetails(String modifyType) {
		int size = this.getListSize();
		for(int i = 1;i<= size;i++) {
			sleep(1);
			if(projectName.equals(driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div/div[3]/div/div/div[1]/div["+i+"]/div/div[1]/footer/div/div[1]/a[2]/small")).getText())) {
				WebElement project = driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div/div[3]/div/div/div[1]/div["+i+"]"));
				//waitVisibility(project);
				project.click();
				if(modifyType.equalsIgnoreCase("VIEW")) {
					sleep(3);
					this.viewMenuOptions();
				}
				else if(modifyType.equalsIgnoreCase("EDIT")) {
					sleep(2);
					this.viewMenuOptions();
					waitVisibility(editEnvironmentName);
					editEnvironmentName.click();
					editEnvironmentName.clear();
					editEnvironmentName.sendKeys(environmentName);
					submitButton.click();
					sleep(4);
					Assert.assertEquals(environmentName,actualEnvironmentName.getText());
				}
				else if(modifyType.equalsIgnoreCase("NOACCESS")) {
					sleep(8);
					Assert.assertEquals("No Environments found",unauthorizedAccess.getText());
				}
			}	
		}	
	}
	public void viewMenuOptions() {
		crashes.click();
		pictures.click();
		videos.click();
		builds.click();
		settings.click();
	}
	private int getListSize() {
		waitVisibility(listOfprojects);
        List<WebElement> myList = driver.findElements(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div/div[3]/div/div/div[1]/div"));
        return myList.size();
	}
	public void inviteUser() {
		sleep(2);
		users.click();
		sleep(3);
		inviteUser.click();
		guerrillaMail = "test"+new Date().getTime();
		replaceUser = guerrillaMail;
		usernameField.sendKeys(guerrillaMail+"@sharklasers.com");
		firstNameField.sendKeys("Testing");
		lastNameField.sendKeys("T");
		phoneNumberField.sendKeys("123456789");
		designationField.sendKeys("QA");
		Select selectRole = new Select(role);
		selectRole.selectByVisibleText("Admin");
		done.click();
	}
	private String RequiredSystemNameXpath = "//div[contains(text(),'xxxxx')]";
	private WebElement prepareWebElementWithDynamicXpath (String xpathValue, String substitutionValue ) {
	    return driver.findElement(By.xpath(xpathValue.replace("xxxxx", substitutionValue)));
	}
	public void deleteSystem (String systemName) {
	    WebElement RequiredSystemName = prepareWebElementWithDynamicXpath(RequiredSystemNameXpath, systemName);
	    RequiredSystemName.click();
	}
}
