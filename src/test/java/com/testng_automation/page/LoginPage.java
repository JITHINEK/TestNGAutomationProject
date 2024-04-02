package com.testng_automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
		driver.get("https://www.saucedemo.com/");
	}

	private By USERNAME_TEXTBOX = By.xpath("//*[@id='user-name']");
	private By PASSWORD_TEXTBOX	= By.xpath("//*[@id='password']");
	private By LOGIN_BUTTON = By.xpath("//*[@id='login-button']");
	
	private By ERROR_MSG = By.xpath("//*[@id='login_button_container']//h3[@data-test='error']");
	
	public InventoryPage doLogin(String userName, String password) {
		fillText(userName, USERNAME_TEXTBOX);
		fillText(password, PASSWORD_TEXTBOX);
		click(LOGIN_BUTTON);
		return getInstance(InventoryPage.class);
	}
	
	public boolean verifyErrorMessage(String errorMessage) {
		if(getText(ERROR_MSG).equals(errorMessage))
			return true;
		else
			return false;
	}

}
