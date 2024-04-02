package com.testng_automation.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;
import com.testng_automation.utils.ReportUtility;

public class ReportListener implements ITestListener{

	public String getTestName(ITestResult result) {
		return result.getTestName() != null ? result.getTestName()
				: result.getMethod().getConstructorOrMethod().getName();
	}

	public String getTestDescription(ITestResult result) {
		return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExReportManager.startTest(getTestName(result), getTestDescription(result));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ReportUtility.addScreenShot(LogStatus.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Throwable throwable = result.getThrowable();
		String cause = "";
		if (throwable != null)
			cause = throwable.getMessage();
		ReportUtility.addScreenShot(LogStatus.FAIL, "Test Failed : " + cause);
	}

	@Override
	public void onFinish(ITestContext context) {
		ExReportManager.endCurrentTest();
		ExReportManager.getExtentReports().flush();
	}
}
