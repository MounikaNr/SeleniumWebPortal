/*package com.qa.ExtentReportListener;

import java.io.IOException;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tripod.qa.tests.InitializeDriver;
import com.tripod.qa.util.TestUtil;

public class ExtentTestNGITestListener extends InitializeDriver implements ITestListener{
	
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	public String screenshotPath;
	
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extent.startTest(result.getName());
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
		}

	}
	@Override
	public void onTestFailure(ITestResult result){
		// TODO Auto-generated method stub
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			try {
				screenshotPath = TestUtil.takeScreenshotAtEndOfTest(result.getName());

			}catch(IOException e) {
				e.printStackTrace();
			}
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
		}

		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}

	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		Date str = new Date();
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", false);
		extent.addSystemInfo("Host Name", "DESKTOP-4O4L6RP");
		extent.addSystemInfo("User Name", "Gd Dell");
		extent.addSystemInfo("Environment", "QA");
	}
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
}
*/