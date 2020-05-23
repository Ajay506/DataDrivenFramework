package com.selenium.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation test results");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Ajay Kumar");
		extent.setSystemInfo("Organization", "Telus Health");
		extent.setSystemInfo("Build no", "W2A-1234");

		return extent;
	}
}

