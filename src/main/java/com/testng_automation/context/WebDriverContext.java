package com.testng_automation.context;

import org.openqa.selenium.WebDriver;

public class WebDriverContext {

	public static InheritableThreadLocal<WebDriver> driverInstance = new InheritableThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		if (driverInstance.get() == null)
			throw new IllegalStateException(
					"WebDriver has not been set, Please set WebDriver instance by WebDriverContext.setDriver...");
		else
			return driverInstance.get();
	}

	public static void setDriver(WebDriver driver) {
		driverInstance.set(driver);
	}

	public static void removeDriver() {
		driverInstance.remove();
	}
}
