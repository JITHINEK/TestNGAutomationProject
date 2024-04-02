package com.testng_automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.LogStatus;
import com.testng_automation.context.WebDriverContext;
import com.testng_automation.listener.ExReportManager;

public class ReportUtility {

	public static void addScreenShot(String message) {
		String base64Image = "data:image/png;base64,"
				+ ((TakesScreenshot) WebDriverContext.getDriver()).getScreenshotAs(OutputType.BASE64);
		ExReportManager.getCurrentTest().log(LogStatus.INFO, message,
				ExReportManager.getCurrentTest().addBase64ScreenShot(base64Image));
	}
	
	public static void addScreenShot(LogStatus status, String message) {
		String base64Image = "data:image/png;base64,"
				+ ((TakesScreenshot) WebDriverContext.getDriver()).getScreenshotAs(OutputType.BASE64);
		ExReportManager.getCurrentTest().log(status, message,
				ExReportManager.getCurrentTest().addBase64ScreenShot(base64Image));
	}
	
	public static void logMessage(String message, String details) {
		ExReportManager.getCurrentTest().log(LogStatus.INFO, message, details);
	}
	
	public static void logMessage(LogStatus status, String message, String details) {
		ExReportManager.getCurrentTest().log(status, message, details);
	}
}
