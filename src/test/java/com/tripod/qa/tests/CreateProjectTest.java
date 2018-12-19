package com.tripod.qa.tests;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import com.tripod.qa.pages.CreateProject;
import com.tripod.qa.pages.DeleteEnvironmentandApp;
import com.tripod.qa.pages.LoginPage;
import com.tripod.qa.pages.Logout;
import com.tripod.qa.pages.SignUp;

public class CreateProjectTest extends InitializeDriver{
	public LoginPage loginpage;
	public CreateProject createproject;
	public Logout logout;
	private DeleteEnvironmentandApp deleteApp;
	private SignUp signUp;
	public CreateProjectTest() {
		super();	
	}
	@BeforeTest
	public void initialize() {
		loginpage = new LoginPage();
		createproject = new CreateProject();
		logout = new Logout();
		deleteApp = new DeleteEnvironmentandApp();
		signUp = new SignUp();
	}
	@Test(priority = 1)
	public void addviewPermissions() throws Exception{
		loginpage.doLogin();
		createproject.inviteUser();
		signUp.createGurillaAccount();
		driver.get(prop.getProperty("url"));
		createproject.createNewProject("ANDROID");	
		createproject.addPermissionsToUser("VIEW",true);
		driver.get(prop.getProperty("url"));
		createproject.verifyCreatedProject();
		driver.navigate().back();
		logout.logoutUser();
		loginpage.login(SignUp.getInviteUserMailId("inviteUserMailId"),prop.getProperty("invitedUserPassword"));
		createproject.verifyUserAbleToModifyDetails("VIEW");
		driver.navigate().back();
		sleep(5);
		logout.logoutUser();
		loginpage.doLogin();
		createproject.verifyCreatedProject();
		deleteApp.deleteApp();	
		logout.logoutUser();
	}
	@Test(priority = 2)
	public void addEditPermissions() throws Exception{
		loginpage.doLogin();
		driver.get(prop.getProperty("url"));
		createproject.createNewProject("ANDROID");	
		createproject.addPermissionsToUser("EDIT",true);
		driver.get(prop.getProperty("url"));
		createproject.verifyCreatedProject();
		driver.navigate().back();
		logout.logoutUser();
		loginpage.login(SignUp.getInviteUserMailId("inviteUserMailId"),prop.getProperty("invitedUserPassword"));
		createproject.verifyUserAbleToModifyDetails("EDIT");
		driver.navigate().back();
		sleep(5);
		logout.logoutUser();
		loginpage.doLogin();
		createproject.verifyCreatedProject();
		deleteApp.deleteApp();
		logout.logoutUser();
	}
	@Test(priority = 3)
	public void addNoPermissions() throws Exception{
		loginpage.doLogin();
		driver.get(prop.getProperty("url"));
		createproject.createNewProject("ANDROID");	
		createproject.addPermissionsToUser("",false);
		driver.get(prop.getProperty("url"));
		createproject.verifyCreatedProject();
		driver.navigate().back();
		logout.logoutUser();
		loginpage.login(SignUp.getInviteUserMailId("inviteUserMailId"),prop.getProperty("invitedUserPassword"));
		createproject.verifyUserAbleToModifyDetails("NOACCESS");
		driver.navigate().back();
		sleep(5);
		logout.logoutUser();
		loginpage.doLogin();
		createproject.verifyCreatedProject();
		deleteApp.deleteApp();
		logout.logoutUser();
	}
}
