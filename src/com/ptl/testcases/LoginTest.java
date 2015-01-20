package com.ptl.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.HomePage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.util.TestUtil;

public class LoginTest extends TestBase {
	HomePage landingPage = null;
	LoginPage loginPage;

	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS.debug("Configuration File initialized in Login Test");
		initDriver();
		APPLICATION_LOGS.debug("Browser initialized in Login Test");
	}

	@Test(dataProvider = "getLoginData")
	public void loginTest(Hashtable<String, String> data) {

		if (!TestUtil.isTestCaseRunmodeYes("Login Test", xls)
				|| data.get("Runmode").equals("No") 
				|| data.get("Application Type").equals("Citizen"))
			throw new SkipException("Skipping the test");
		
		if(isLoggedIn)
			throw new SkipException("Already logged in. Skipping the test");

		System.out.println("************************************************");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(CONFIG.getProperty("BASE_URL"));
		loginPage = PageFactory.initElements(driver, LoginPage.class);

		landingPage = loginPage.doLogin(data.get("Username"),
				data.get("Password"));

		Assert.assertTrue(landingPage != null, "Could not login");
		isLoggedIn = true;
		APPLICATION_LOGS.debug("logged in");

	}


	@DataProvider
	public Object[][] getLoginData() {
		return TestUtil.getTestData("Login Test", xls);

	}

}
