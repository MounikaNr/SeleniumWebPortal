package com.tripod.qa.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.CreateProject;
import com.tripod.qa.pages.DeleteEnvironmentandApp;
import com.tripod.qa.pages.LoginPage;
import com.tripod.qa.pages.Logout;

public class DeleteEnvironmentandAppTest extends InitializeDriver{
	private LoginPage loginpage;
	private CreateProject createproject;
	private DeleteEnvironmentandApp deleteEnvironment;
	private Logout logout;
	
	public DeleteEnvironmentandAppTest(){
		super();
	}
	@BeforeTest
	public void initialize() {
		loginpage = new LoginPage();
		createproject = new CreateProject();
		deleteEnvironment = new DeleteEnvironmentandApp();
		logout = new Logout();
	}

	@Test(priority = 1)
	public void deleteEnvironmentAndroid() {
		loginpage.doLogin();
		driver.get(prop.getProperty("url"));
		createproject.createNewProject("ANDROID");
		deleteEnvironment.deleteEnvironment();
		createproject.verifyCreatedProject();
		deleteEnvironment.verifyEnvironmentDeletedorNot();
		driver.navigate().back();
		logout.logoutUser();
	}
	@Test(priority = 2)
	public void deleteAppAndroid() {
		loginpage.doLogin();
		driver.get(prop.getProperty("url"));
		createproject.createNewProject("ANDROID");
		deleteEnvironment.deleteApp();
		createproject.verifyCreatedProject();
	}
	@AfterTest
	public void logout() {
		logout.logoutUser();
	}
}
