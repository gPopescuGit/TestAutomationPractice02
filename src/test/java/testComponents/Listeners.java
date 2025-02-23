package testComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extentReport = ExtentReporterNG.getReportObject();
	
	@Override
	public void onTestStart(ITestResult result) {
		 test = extentReport.createTest(result.getMethod().getMethodName());
	  }
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
		  }
	
	@Override
	  public void onFinish(ITestContext context) {
		extentReport.flush();
		}
	
	/*
	 * TODO: add listener for testFail
	 * Missing listener make failing tests look as passed in testNG Report
	 */

}
