package com.tripod.qa.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.Builds;
import com.tripod.qa.pages.CreateProject;
import com.tripod.qa.pages.LoginPage;
import com.tripod.qa.pages.Logout;

public class BuildsTest extends InitializeDriver{
	
	private CreateProject createproject;
	private LoginPage loginpage;
	private Builds builds;
	private Logout logout;
	
	public BuildsTest() {
		super();
	}
	@BeforeTest
	public void init() {
		createproject = new CreateProject();
		loginpage = new LoginPage();
		builds = new Builds();
		logout = new Logout();
	}
	@Test
	public void buildsTest() {
		loginpage.doLogin();
		createproject.createNewProject("ANDROID");
		driver.navigate().back();
		createproject.verifyCreatedProject();
		builds.uploadBuild();	
	}
	@AfterTest
	public void logout() {
		logout.logoutUser();
	}
}
