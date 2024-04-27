package com.saucedemo.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.saucedemo.qa.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public Base()
	{
		dataProp=new Properties();
		File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\saucedemo\\qa\\testdata\\testdata.properties");
		
		try 
		{
			FileInputStream fis = new FileInputStream(dataPropFile);
			dataProp.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\saucelabs\\qa\\config\\config.properties");
		
		try 
		{
			FileInputStream fis1 = new FileInputStream(propFile);
			prop.load(fis1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	public WebDriver initilizeBrowserAndOpenApplicationURL(String browserName)
	{
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firfox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("URL"));
		return driver;
	}

}
