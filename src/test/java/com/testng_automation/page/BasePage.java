package com.testng_automation.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testng_automation.context.WebDriverContext;

public class BasePage {
	
	protected WebDriver driver;
	
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public WebElement getElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public List<WebElement> getElements(By locator) {
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public void fillText(String text, By locator) {
		getElement(locator).sendKeys(text);
	}

	public void click(By locator) {
		getElement(locator).click();
	}
	
	public String getText(By locator) {
		return getElement(locator).getText();
	}
	
	public void pageRefresh() {
		driver.navigate().refresh();
	}
	
	public static <T extends BasePage> T getInstance(Class<T> type) {
		try {
			return type.getConstructor(WebDriver.class).newInstance(WebDriverContext.getDriver());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
