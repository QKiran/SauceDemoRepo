package com.saucedemo.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.qa.base.Base;

public class buyAProductTest extends Base {
	
	WebDriver driver;
	
	public buyAProductTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUP()
	{
		driver=initilizeBrowserAndOpenApplicationURL(prop.getProperty("BrowserName"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyBuyAProductWithValidDetails()
	{
		driver.findElement(By.id("user-name")).sendKeys(prop.getProperty("UserName"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("Password"));
		driver.findElement(By.id("login-button")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.className("shopping_cart_link")).click();
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("Kiran");
		driver.findElement(By.id("last-name")).sendKeys("Konduri");
		driver.findElement(By.id("postal-code")).sendKeys("12345");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("finish")).click();
		String actualMessage=driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
		Assert.assertTrue(actualMessage.contains(dataProp.getProperty("actuaMessage_BuyAProduct")), "Thank you for your order! is not shown");
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.id("logout_sidebar_link")).click();
		
	}
	@Test(priority = 2)
	public void verifyEmpyUserDetailsInCheckoutPage()
	{
		driver.findElement(By.id("user-name")).sendKeys(prop.getProperty("UserName"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("Password"));
		driver.findElement(By.id("login-button")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.className("shopping_cart_link")).click();
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("");
		driver.findElement(By.id("last-name")).sendKeys("");
		driver.findElement(By.id("postal-code")).sendKeys("");
		driver.findElement(By.id("continue")).click();
		String actualErrorMessage=driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertTrue(actualErrorMessage.contains(dataProp.getProperty("actualErrorMessage_BuyAProduct")), "Error: First Name is required is not shown");
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.id("logout_sidebar_link")).click();
	}

}
