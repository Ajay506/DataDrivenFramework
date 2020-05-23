package com.selenium.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	
	public void loginAsBankManager() throws InterruptedException {
		
		log.info("Inside Bank Manager Login test");
		
		driver.findElement(By.cssSelector(OR.getProperty("bankManagerLoginButton"))).click();
		
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login Not sucessfull");
	}

}