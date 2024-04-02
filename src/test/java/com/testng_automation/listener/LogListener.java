package com.testng_automation.listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.testng_automation.utils.LoggerUtility;

public class LogListener implements ITestListener {

	public String getTestName(ITestResult result) {
		return result.getTestName() != null ? result.getTestName()
				: result.getMethod().getConstructorOrMethod().getName();
	}
	
	public String getTestDescription(ITestResult result) {
		return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
	}

	@Override
	public void onTestStart(ITestResult result) {
		LoggerUtility.log(getTestName(result) + ": Test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LoggerUtility.log(getTestName(result) + " : Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Throwable throwable = result.getThrowable();
		String cause = "";
		if (throwable != null)
			cause = throwable.getMessage();
		LoggerUtility.getLogger().fatal(getTestName(result) + " : Test Failed : " + cause);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LoggerUtility.log(getTestName(result) + " : Test Skipped");
	}
	
	
}
