package com.selenium.listeners;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.selenium.base.TestBase;
import com.selenium.utilities.CommonMethods;




public class CustomListeners extends TestBase implements ITestListener {
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		
		test = rep.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		

		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		
		
	}

	public void onTestFailure(ITestResult result) {
		
		//CommonMethods.captureScreenshot();
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		Reporter.log("Capturing Screenshot");
		
		Reporter.log("<a target=\"_blank\"href="+CommonMethods.screenshotName+">Screenshot</a>");
		
		//Reporter.log("<a target=\"_blank\"href=\"C:\\Ajay\\Selenium\\DataDrivenFramework\\Screenshots\\FailesScreenshots\">Screenshot</a>");

		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
	    testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"+ "</font>" + "</b >" + "</summary>" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		
		try {

			CommonMethods.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(CommonMethods.screenshotName).build());
		} catch (IOException e) {

		}
		
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// FIXME Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// FIXME Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		
		if (rep != null) {

			rep.flush();
		}

	}

}