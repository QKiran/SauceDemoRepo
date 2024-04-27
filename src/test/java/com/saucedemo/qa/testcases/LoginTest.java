package com.saucedemo.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.qa.base.Base;
import com.saucedemo.qa.pages.LoginPage;
import com.saucedemo.qa.utils.Utilities;

public class LoginTest extends Base{
	
	WebDriver driver;

	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		driver=initilizeBrowserAndOpenApplicationURL(prop.getProperty("BrowserName"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
//	@DataProvider(name="validCredentailsSupplier")
//	public Object[][] supplyTestData()
//	{
//		Object[][] data= {{"standard_user","secret_sauce"},{"problem_user","secret_sauce"},{"error_user","secret_sauce"}};
//		return data;
//	}
	
	@DataProvider(name="validCredentailsSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 1, dataProvider = "validCredentailsSupplier")
	public void verifyLoginWithValidCredentails(String Username, String Password)
	{
		LoginPage loginPage=new LoginPage(driver);
		loginPage.inputUserName(Username);
		loginPage.inputPassword(Password);
		loginPage.clickLogin();
		String actualMessage=driver.findElement(By.id("item_4_title_link")).getText();
		Assert.assertTrue(actualMessage.contains(dataProp.getProperty("expectedMessage")), "Sauce Labs Backpack item is not shown");
		loginPage.clickburgerMenuButton();
		loginPage.clicklogoutButton();
		
	}
	@Test(priority = 2)
	public void verifyLoginWithEmptyCredentails()
	{

		driver.findElement(By.id("user-name")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("login-button")).click();
		String actuaErrorMessage=driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertTrue(actuaErrorMessage.contains(dataProp.getProperty("expectedErrorMessage1")), "Epic sadface: Username is required is not shown");
	}
	@Test(priority = 2, dependsOnMethods = {"verifyLoginWithEmptyCredentails"})
	public void verifyLoginWithInvalidCredentails()
	{
		driver.findElement(By.id("user-name")).sendKeys("InvalidUserName");
		driver.findElement(By.id("password")).sendKeys(dataProp.getProperty("InvalidPassword"));
		driver.findElement(By.id("login-button")).click();
		String actuaErrorMessage=driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertTrue(actuaErrorMessage.contains(dataProp.getProperty("expectedErrorMessage2")), "Epic sadface: Username and password do not match any user in this service is not shown");
	}
}
