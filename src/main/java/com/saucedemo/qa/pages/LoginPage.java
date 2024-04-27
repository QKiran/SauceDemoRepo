package com.saucedemo.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(id="user-name")
	private WebElement enterUsername;
	
	@FindBy(id="password")
	private WebElement enterPassword;
	
	@FindBy(id="login-button")
	private WebElement loginButton;
	
	@FindBy(id="item_4_title_link")
	private WebElement actualMessage;
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement burgerMenuButton;
	
	@FindBy(id="logout_sidebar_link")
	private WebElement logoutButton;
	
	@FindBy(xpath="//h3[@data-test='error']")
	private WebElement actualErrorMessage1;
	
	@FindBy(xpath="//h3[@data-test='error']")
	private WebElement actualErrorMessage2;
	
	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void inputUserName(String inputUserName)
	{
		enterUsername.sendKeys(inputUserName);
	}
	
	public void inputPassword(String inputPassword)
	{
		enterPassword.sendKeys(inputPassword);
	}
	
	public void clickLogin()
	{
		loginButton.click();
	}
	
//	public void actuaMessageShown()
//	{
//		actualMessage.getText();
//	}
	
	public void clickburgerMenuButton()
	{
		burgerMenuButton.click();
	}
	
	public void clicklogoutButton()
	{
		logoutButton.click();
	}

}
