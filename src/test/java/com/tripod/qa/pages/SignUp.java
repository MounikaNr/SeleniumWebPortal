package com.tripod.qa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.tripod.qa.base.BaseTest;
import org.testng.Assert;

public class SignUp extends BaseTest{
	public LoginPage loginpage;
	String mailId;
	Date date = new Date();
	String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	
	
	public static String getMailId(String mailID) throws Exception{
		FileInputStream in = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/com/tripod" + "/qa/config/config.properties");		
		Properties props = new Properties();
		props.load(in);
		String inviteUser = props.getProperty(mailID);
		in.close();
		return inviteUser;
	}
	public static String getUserMailId(String userMailId) throws Exception{
		return getMailId(userMailId);
	}
	public static String getInviteUserMailId(String inviteUserMailId) throws Exception{
		return getMailId(inviteUserMailId);
	}
	public  void setMailId(String property,String mailId) throws Exception {
		FileInputStream in = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/com/tripod" + "/qa/config/config.properties");		
		Properties props = new Properties();
		props.load(in);
		in.close();
		FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/src/test/java/com/tripod" + "/qa/config/config.properties");		
		props.setProperty(property, mailId);
		props.store(out, null);
		out.close();
	}
	public  void setInviteMailId(String mailId) throws Exception {
		setMailId("inviteUserMailId",mailId);
	}
	public  void seUserMailId(String mailId) throws Exception {
		setMailId("userMailId",mailId);
	}

	@FindBy(css= "input[name=email]")
	WebElement usernameFiled;
	
	@FindBy(css = "a[href=\"#/access/signup\"]")
	WebElement createAccount;
	
	@FindBy(name = "firstName")
	WebElement firstNameField;
	
	@FindBy(name = "lastName")
	WebElement lastNameField;
	
	@FindBy(name = "email")
	WebElement emailField;
	
	@FindBy(name = "phone")
	WebElement phoneNumberField;
	
	@FindBy(name = "password")
	WebElement passwordField;
	
	@FindBy(name = "confirm_password")
	WebElement confirmPasswordField;
	
	@FindBy(name = "companyName")
	WebElement companyNameField;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/form/div[2]/div[6]/div[2]/select")
	WebElement chooseCountry;
	
	@FindBy(xpath= "//*[@id=\"app\"]/div/div/div[1]/form/div[2]/div[7]/label/i")
	WebElement checkBoxField;
	
	@FindBy(css = "span.text-white")
	WebElement SignUp;
	
	@FindBy(xpath = "//td[contains(text(),'noreply@gettripod.com')]")
	WebElement incomingMail;
	
	@FindBy(xpath = "//*[starts-with(@id,'mr_') and contains(@id, '')]")
	WebElement incomingMailSignUp;
	
	@FindBy(xpath = "//*[@id=\"display_email\"]/div/div[2]/div/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr/td/table/tbody/tr[2]/td[2]/p[3]/a")
	WebElement validateMail;
	
	@FindBy(xpath = "//*[@id=\"inbox-id\"]")
	WebElement nameField;
	
	@FindBy(id = "email-widget")
	WebElement generatedmailId;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[1]/a/span")
	WebElement tripodText;
	
	@FindBy(xpath = "//span[@id='inbox-id']")
	WebElement enterMailId;
	
	@FindBy(xpath = "//*[@id=\"inbox-id\"]/input")
	WebElement enterMail;
	
	@FindBy(xpath = "//*[@id=\"inbox-id\"]/button[1]")
	WebElement set;
	
	@FindBy(xpath = "//*[@id=\"use-alias\"]")
	WebElement uncheckScramble;
	
	@FindBy(xpath = "//*[@id=\"display_email\"]/div/div[2]/div/table/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/a")
	WebElement acceptInvitation;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement passwordCreationField;
	
	@FindBy(xpath = "//input[@placeholder='Confirm Password']")
	WebElement confirmPasswordCreation;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/form/div[2]/button")
	WebElement submitButton;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/p")
	WebElement text;
	
	@FindBy(xpath = "//*[@id=\"display_email\"]/div/div[2]/div/table/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/a")
	WebElement getUrl;
	
	public SignUp() {
		PageFactory.initElements(driver, this);
		loginpage = new LoginPage();
	}
	
	public void createGurillaAccount(){	
		openNewTab(1);
		driver.get(prop.getProperty("guerrillMailUrl"));
		enterMailId.click();
		enterMail.sendKeys(CreateProject.guerrillaMail);
		try {
			setInviteMailId(CreateProject.guerrillaMail+"@sharklasers.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		set.click();
		uncheckScramble.click();
		sleep(10);
		incomingMail.click();
		waitVisibility(acceptInvitation);
		String invitationUrl = acceptInvitation.getAttribute("href");
		openNewTab(2);
		driver.get(invitationUrl);
		sleep(7);
		passwordCreationField.click();
		passwordCreationField.sendKeys("password");
		confirmPasswordCreation.sendKeys("password");
		submitButton.click();
	}
	public void createGuerrillaMail() {
		openNewTab(1);
		driver.get(prop.getProperty("guerrillMailUrl"));
		mailId = generatedmailId.getText();
		try {
			seUserMailId(mailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		nameField.click();		
	}
	public void createAccount() {
		openNewTab(2);
		driver.get(prop.getProperty("url"));
		waitVisibility(createAccount);
		clickElementUsingJs(createAccount);
		firstNameField.sendKeys("Testing");
		lastNameField.sendKeys("T");
		emailField.sendKeys(mailId);
		phoneNumberField.sendKeys("123456789");
		passwordField.sendKeys("password");
		confirmPasswordField.sendKeys("password");
		companyNameField.sendKeys("Gd Test"+str);
		Select selectCountry = new Select(chooseCountry);
		selectCountry.selectByVisibleText("India");
		SignUp.click();
	}
	public void validateMail() {
		openNewTab(3);
		driver.get(prop.getProperty("guerrillMailUrl"));
		sleep(20);
		driver.navigate().refresh();
		sleep(10);
		incomingMail.click();
		waitVisibility(validateMail);
		validateMail.click();
	}
	public void loginWithGuerrillaMail() {
		sleep(10);
		openNewTab(4);
	    driver.get(prop.getProperty("url"));
		try {
			loginpage.login(getUserMailId("userMailId"), "password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sleep(7);
		Assert.assertEquals("Tripod", tripodText.getText());
	}
}
