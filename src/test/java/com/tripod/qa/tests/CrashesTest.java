package com.tripod.qa.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.Crashes;

public class CrashesTest {
	
	private Crashes crashes;
	@BeforeTest
	public void initialize() {
		crashes = new Crashes();
	}
	@Test 
	public void verifyCrashes() {
		crashes.loginUser();
		crashes.createCrashInfo();
		crashes.getCrashStackTrace();
		crashes.getCrash();
	}
}
