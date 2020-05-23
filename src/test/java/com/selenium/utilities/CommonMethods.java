package com.selenium.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.selenium.base.TestBase;

public class CommonMethods extends TestBase  {
	

	public static String screenshotName;
	
	public static void captureScreenshot() {
		
		Date d = new Date();

		//String name = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		
		//String screenshotPath = "C:\\Ajay\\Selenium\\DataDrivenFramework\\screenshots\\FailesScreenshots\"";
		
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			
			FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html"+ "\\" + screenshotName));

			//FileUtils.copyFile(source, new File("C:\\Ajay\\Selenium\\DataDrivenFramework\\screenshots\\FailesScreenshots" + "\\" + "" + screenshotName));

		} catch (IOException e) {

			e.printStackTrace();
		}		
		
	}

}


