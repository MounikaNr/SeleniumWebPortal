package com.tripod.qa.util;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.tripod.qa.base.BaseTest;

public class TestUtil extends BaseTest{
	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 20;
	public static  int DEFAULT_WAIT_UNTIL_DURATION = 40;
	
	public static String takeScreenshotAtEndOfTest(String name) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir")+ "/screenshots/" +name+ System.currentTimeMillis() + ".png";
		FileUtils.copyFile(scrFile, new File(currentDir));
		return currentDir;
	}
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir")+ "/screenshots/"+ System.currentTimeMillis() + ".png";
		FileUtils.copyFile(scrFile, new File(currentDir));
	}
}
