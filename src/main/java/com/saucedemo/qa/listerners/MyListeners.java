package com.saucedemo.qa.listerners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucedemo.qa.utils.ExtentReporter;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport=ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testName=result.getName();
		ExtentTest extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+"started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName()+" test got successfully executed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getName()+" test got failed");
		System.out.println(result.getThrowable());
		System.out.println("SCreenshot taken");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName()+" test got skipped");
		System.out.println(result.getThrowable());
	}


	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Finished the test case execution.");
	}
	
	

}
