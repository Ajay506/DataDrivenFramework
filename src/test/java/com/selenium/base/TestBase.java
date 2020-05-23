package com.selenium.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.selenium.utilities.CommonMethods;
import com.selenium.utilities.ExtentManager;
import com.selenium.utilities.ReadingExcel;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;

	public static Properties config = new Properties();

	public static Properties OR = new Properties();

	public static FileInputStream fis;
	
	public static ReadingExcel excel = new ReadingExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	
	public static Logger log = Logger.getLogger(TestBase.class.getName());
	
	public static WebDriverWait wait;
	
	public ExtentReports rep = ExtentManager.createInstance(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html");
	
	public static ExtentTest test;
	
	


	@BeforeSuite
	public void setUp() throws IOException {
		
		PropertyConfigurator.configure("./src/test/resources/properties/Log4j.properties");
		
		if (driver == null) {

			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");

			config.load(fis);
			
			log.info("Logging into the application");

			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");

			OR.load(fis);

		}

		if (config.getProperty("browser").equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

		} else if (config.getProperty("browser").equals("chrome")) {

			WebDriverManager.chromiumdriver().setup();

			driver = new ChromeDriver();
			
			log.info("Launched Chrome Browser Sucessfully");
		}

		driver.get(config.getProperty("testsiteurl"));
		
		log.info("Navigated to the browser");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();
		
		wait = new WebDriverWait(driver, 5000);
	}
	
	
	public Boolean isElementPresent(By by) {
		try {
			
			driver.findElement(by);
			
			return true;
			
			
		}catch(NoSuchElementException e) {
			
			return false;			
			
		}
		
		
	}
	
	public static void verifyEquals(String Expected, String Actual) {
		
		try {
			
			Assert.assertEquals(Actual, Expected);
			
			//assert.assertEquals(Actual, Expected);
		} catch(Throwable t) {
			
			CommonMethods.captureScreenshot();
			
			
		}
	}
	

	@AfterSuite
	public void tearDown() {

		if (driver != null) {

			driver.quit();

		}
	}
	
	
	
}
