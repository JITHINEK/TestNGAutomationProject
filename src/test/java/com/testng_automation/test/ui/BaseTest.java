package com.testng_automation.test.ui;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.testng_automation.context.WebDriverContext;
import com.testng_automation.listener.LogListener;
import com.testng_automation.listener.ReportListener;
import com.testng_automation.utils.ExcelUtility;
import com.testng_automation.utils.LoggerUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({ReportListener.class, LogListener.class})
public class BaseTest {
	
	protected WebDriver driver;
	protected ExcelUtility excelUtility;
	
	@BeforeClass
	protected void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverContext.setDriver(driver);
		
		excelUtility = new ExcelUtility();
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(ITestContext context) {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
		int total = context.getAllTestMethods().length;
		int passed = context.getPassedTests().size();
		int failed = context.getFailedTests().size();
		int skipped = context.getSkippedTests().size();
		LoggerUtility.log("Total number of testcases : " + total);
		LoggerUtility.log("Number of testcases Passed : " + passed);
		LoggerUtility.log("Number of testcases Failed : " + failed);
		LoggerUtility.log("Number of testcases Skipped  : " + skipped);
		//boolean mailSent = MailUtil.sendMail(total, passed, failed, skipped);
		//LoggerUtility.log("Mail sent : " + mailSent);
		LoggerUtility.log("************************** Test Execution Finished ************************************");
	}

}
