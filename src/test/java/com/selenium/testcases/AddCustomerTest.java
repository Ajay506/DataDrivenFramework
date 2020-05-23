package com.selenium.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.base.TestBase;

public class AddCustomerTest extends TestBase{


	@Test
	public void addCustomer() throws InterruptedException {
		
		//System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		log.info("Inside AddCustomerTest testcase");
		
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		
		driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys("Ajay Kumar1");
		
		driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys("Bonala");
		
		driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys("A1B 2B9");
		
		driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains("Customer added successfully"));
		
		//Thread.sleep(3000);
		
		alert.accept();
		
		//Reporter.log("Customer Added successfully");
		
		//Below code will open the screenshot in the same window
		
		//Reporter.log("<a href=\"C:\\Ajay\\Selenium\\TestNGLearning\\screenshots\\sample.jpg\">Screenshot</a>");
		
		//Below code will open the screenshot in a new tab
		//Reporter.log("<a target=\"_blank\"href=\"C:\\Ajay\\Selenium\\TestNGLearning\\screenshots\\sample.jpg\">Screenshot</a>");
	}

	/*
	 * @DataProvider public Object[][] getData(){
	 * 
	 * String sheetname = "AddCustomerTest";
	 * 
	 * int rows = excel.getRowCount(sheetname);
	 * 
	 * int cols = excel.getColumnCount(sheetname);
	 * 
	 * Object[][] data = new Object[rows-1][cols];
	 * 
	 * for (int rowNum =2; rowNum<=rows; rowNum++) {
	 * 
	 * for(int colNum=0; colNum<cols; colNum++) {
	 * 
	 * data[rowNum-2][colNum] = excel.getCellData(sheetname, colNum, rowNum); }
	 * 
	 * }
	 * 
	 * return data;
	 * 
	 * 
	 * }
	 */
}
