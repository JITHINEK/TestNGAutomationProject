package com.testng_automation.test.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testng_automation.page.InventoryPage;
import com.testng_automation.page.LoginPage;
import com.testng_automation.utils.ExcelUtility;

@Test(testName = "Swag Lab login test", description = "Swag Lab login test")
public class LoginTest extends BaseTest {

	@Test(testName = "TEST001: DATA001 - Login successful", description = "login test with valid credentials")
	public void LoginTest_TEST001() {

		HashMap<String, String> data = excelUtility.getData("TEST001").get("DATA001");

		LoginPage login = new LoginPage(driver);
		InventoryPage inventoryPage = login.doLogin(data.get("username"), data.get("password"));

		Assert.assertTrue(inventoryPage.validateInventoryPage());

	}

	@Test(testName = "TEST002 - Login failure", description = "login test with valid credentials", dataProvider = "loginTestDataProvide_TEST002")
	public void LoginTest_TEST002(HashMap<String, String> data) {
		LoginPage login = new LoginPage(driver);
		login.doLogin(data.get("username"), data.get("password"));
		
		System.out.println(data);
		String error =data.get("errormessage");
		Assert.assertTrue(login.verifyErrorMessage(error));
		
		login.pageRefresh();
		
	}

	@DataProvider(name = "loginTestDataProvide_TEST002")
	public static Object[][] testDataDataProvider() {
		return ExcelUtility.getDataProvider("TEST002");
	}

}
