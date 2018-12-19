package com.tripod.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tripod.qa.util.TestUtil;
import com.tripod.qa.util.WebEventListener;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	private static String OS = System.getProperty("os.name").toLowerCase();

	Logger log = Logger.getLogger(BaseTest.class);

	public BaseTest() {
		prop = new Properties();
		try {
			FileInputStream fip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/com/tripod" + "/qa/config/config.properties");
			prop.load(fip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDriverExecutibleName() {
		if (OS.indexOf("win") >= 0) {
			return ".exe";
		} else if (OS.indexOf("mac") >= 0) {
			return "_mac";
		} else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
			return "_linux";
		}
		return "";
	}

	public void intialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/lib/chrome/chromedriver" + getDriverExecutibleName());
			driver = new ChromeDriver();
			log.info("......Launching chrome browser.....");
		} else if (browserName.equals("phantomjs")) {
			Capabilities caps = new DesiredCapabilities();
			((DesiredCapabilities) caps).setJavascriptEnabled(true);
			((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes"});
			((DesiredCapabilities) caps).setCapability("webStorageEnabled", true);
			((DesiredCapabilities) caps).setCapability("acceptSslCerts", true);
			((DesiredCapabilities) caps).setCapability("databaseEnabled", true);
			
			((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,System.getProperty("user.dir") + "/lib/phantomjs/phantomjs" + getDriverExecutibleName());
			System.setProperty("phantomjs.binary.path", System.getProperty("user.dir") + "/lib/phantomjs/phantomjs" + getDriverExecutibleName());
			driver = new PhantomJSDriver(caps);
		}
		driver.get(prop.getProperty("url"));
		log.info("......entering into the application....");
		driver.manage().window().setSize(new Dimension(1600,900));

		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		EventFiringWebDriver e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
	}

	public void clickElementUsingJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void sleep(int s) {
		try {
			Thread.sleep(s * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void openNewTab(int tabNumber) {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabNumber));
	}

	public boolean waitVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean waitVisible(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return true;

	}

	public void waitStaleness(String identifier) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.DEFAULT_WAIT_UNTIL_DURATION);
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(identifier))));
	}

	public void deletePrevoiusExtentReport() {
		try {
			File file = new File(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("File is created");
			} else {
				// delete a file
				System.out.println("File already exist");
				file.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
