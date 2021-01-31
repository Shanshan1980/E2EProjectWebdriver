package Academe;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import resources.base;

public class Listeners extends base implements ITestListener {
	//onTestStart create a ExtentReports object, using this object create the ExtentTest obj.
	// ExtentReports -> ExtentTest -> ThreadLocal<ExtentTest>
	//    extent     ->     test   ->    extentTest
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	public void onTestStart(ITestResult result) {
		System.out.println("start");
		test=extent.createTest(result.getMethod().getMethodName()); //pass dynamish test method name
		extentTest.set(test);
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());  //failure logs will be sended.
		WebDriver driver= null;
		String testMethodName= result.getMethod().getMethodName();
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			
		} 
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		}
	public void onFinish(ITestContext context) {   //***ITestContext variable type must be correct
		
		extent.flush();
	}
}
