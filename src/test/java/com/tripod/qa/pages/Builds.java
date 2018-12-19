package com.tripod.qa.pages;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tripod.qa.base.BaseTest;

public class Builds extends BaseTest {

	private String fileName;

	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div/nav/ul/li[6]")
	WebElement builds;

	@FindBy(xpath = "//div[contains(@class,'container w-xxl w-auto-xs text-mid text-center m-t-xxl')]//a[contains(@class,'text-white btn btn-default btn-addon ng-scope')]")
	WebElement UploadBuild;

	@FindBy(id = "buildInput")
	WebElement chooseFile;

	@FindBy(xpath = "//textarea[contains(@placeholder,'Enter release notes')]")
	WebElement releaseNotesText;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//button[contains(@class,'btn btn-default ng-scope')]")
	WebElement closeButton;

	@FindBy(xpath = "//button[contains(@type,'submit')]")
	WebElement downloadBuild;

	@FindBy(xpath = "//span[contains(@title,'1.0 (1) 1')]")
	WebElement buildNumber;

	@FindBy(xpath = "//a[contains(@class,'pull-right ng-scope')]")
	WebElement url;

	public Builds() {
		PageFactory.initElements(driver, this);
	}

	public void uploadBuild() {
		sleep(3);
		builds.click();
		sleep(6);
		UploadBuild.click();
		chooseFile.sendKeys(System.getProperty("user.dir") + "/src/main/resources/demo.apk");
		releaseNotesText.click();
		releaseNotesText.sendKeys("demo");
		submitButton.click();
		waitVisibility(closeButton);
		closeButton.click();
		Assert.assertEquals(buildNumber.getText(), "1.0 (1) 1");
		String getUrl = url.getAttribute("href");
		fileName = getUrl.substring(getUrl.lastIndexOf('/') + 1);
		downloadBuild.click();
		sleep(7);
		Assert.assertTrue(isFileDownloaded());
	}

	public boolean isFileDownloaded() {
		boolean flag = false;
		File dir = new File(System.getenv("USERPROFILE") + "\\Downloads");
		File[] dir_contents = dir.listFiles();
		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}
		return flag;
	}
}
