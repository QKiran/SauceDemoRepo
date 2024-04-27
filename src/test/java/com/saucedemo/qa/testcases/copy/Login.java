package com.saucedemo.qa.testcases.copy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {
	
	@Test(priority = 1)
	public void verifyLoginWithValidCredentails()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		String actualMessage=driver.findElement(By.id("item_4_title_link")).getText();
		Assert.assertTrue(actualMessage.contains("Sauce Labs Backpack"), "Sauce Labs Backpack item is not shown");
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.id("logout_sidebar_link")).click();
		driver.quit();
	}
	@Test(priority = 2)
	public void verifyLoginWithEmptyCredentails()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("login-button")).click();
		String actuaErrorMessage=driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertTrue(actuaErrorMessage.contains("Epic sadface: Username is required"), "Epic sadface: Username is required is not shown");
		driver.quit();
	}
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentails()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("Kiran");
		driver.findElement(By.id("password")).sendKeys("12345");
		driver.findElement(By.id("login-button")).click();
		String actuaErrorMessage=driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertTrue(actuaErrorMessage.contains("Epic sadface: Username and password do not match any user in this service"), "Epic sadface: Username and password do not match any user in this service is not shown");
		driver.quit();
	}
}
