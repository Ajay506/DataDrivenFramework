package com.selenium.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.selenium.base.TestBase;

public class OpenAccount extends TestBase {

	@Test
	public void openAccountTest() throws InterruptedException {
		
		log.info("Inside OpenAccount test");
		
		driver.findElement(By.cssSelector(OR.getProperty("openAcct"))).click();
		
		WebElement dropdownofCust = driver.findElement(By.cssSelector(OR.getProperty("Cust")));
		
		Select select = new Select(dropdownofCust);
		
		select.selectByVisibleText("Ajay Kumar1 Bonala");
		
		WebElement dropdownofCurrency = driver.findElement(By.cssSelector(OR.getProperty("Currency")));
		
		select = new Select(dropdownofCurrency);
		
		select.selectByVisibleText("Rupee");
		
		driver.findElement(By.cssSelector(OR.getProperty("Process"))).click();
		
		Thread.sleep(2000);
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		alert.accept();

	}

}
