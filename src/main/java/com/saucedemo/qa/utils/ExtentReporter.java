package com.saucedemo.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport()
	{
		ExtentReports report=new ExtentReports();
		File file=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter esr=new ExtentSparkReporter(file);
		esr.config().setTheme(Theme.STANDARD);
		esr.config().setReportName("Sauce Demo");
		esr.config().setDocumentTitle("Sauce Demo Application");
		esr.config().setTimeStampFormat("dd/MM/YY hh:mm:ss");
		report.attachReporter(esr);
		Properties cp=new Properties();
		File cf=new File(System.getProperty("user.dir")+"com\\saucelabs\\qa\\config\\config.properties");
		try {
		FileInputStream fis=new FileInputStream(cf);
		cp.load(fis);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		report.setSystemInfo("Application URL",cp.getProperty("URL"));
		report.setSystemInfo("Browser Name", cp.getProperty("BrowserName"));
		report.setSystemInfo("Username", cp.getProperty("UserName"));
		report.setSystemInfo("Password", cp.getProperty("Password"));
		report.setSystemInfo("Operating System", System.getProperty("os.name"));
		report.setSystemInfo("System Name", cp.getProperty("user.name"));
		report.setSystemInfo("Java Version", cp.getProperty("java.version"));
		
		return report;
	}

}
