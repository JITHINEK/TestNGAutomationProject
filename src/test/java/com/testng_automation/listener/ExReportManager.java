package com.testng_automation.listener;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.testng_automation.context.Constance;

public class ExReportManager {

	private static ExtentReports extentReports;
	private static Map<Long, ExtentTest> map = new HashMap<>();
	
	
	public static ExtentReports getExtentReports() {
		if (extentReports == null) {
			extentReports = new ExtentReports(Constance.REPORT_DIR);
			//extentReports.assignProject(Constants.PROJECT_NAME);
			//extentReports.loadConfig(new File(Constants.EXTENT_CONFIG_PATH));
		}
		return extentReports;
	}
	
	public synchronized static void startTest(String testName, String desc) {
		ExtentTest test = getExtentReports().startTest(testName, desc);
		map.put(Thread.currentThread().threadId(), test);
	}
	
	public synchronized static ExtentTest getCurrentTest() {
		return map.get(Thread.currentThread().threadId());
	}
	
	public synchronized static void endCurrentTest() {
		getExtentReports().endTest(getCurrentTest());
	}
}
