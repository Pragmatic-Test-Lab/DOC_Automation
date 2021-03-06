package com.ptl.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.CitizenHomePage;
import com.ptl.DOC.pages.CitizenLoginPage;
import com.ptl.DOC.pages.HomePage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.TestUtil;

public class LogoutTest extends TestBase {
	
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
	public void LogOut(Hashtable<String, String> data) {

		if (!TestUtil.isTestCaseRunmodeYes("Logout Test", xls)
				|| data.get("Runmode").equals("No") 
				|| data.get("Application Type").equals("Citizen"))
			throw new SkipException("Skipping the test");

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage loginPage;
		HomePage homePage = null;
		
		if(!isLoggedIn_CitizenApp){
			APPLICATION_LOGS.debug("User is not logged in to the system, so navigating to the log in page");
			driver.get(CONFIG.getProperty("BASE_URL"));
			loginPage = PageFactory.initElements(driver, LoginPage.class);
			APPLICATION_LOGS.debug("logged in");
			homePage = loginPage.doLogin(data.get("Username"), data.get("Password"));
			APPLICATION_LOGS.debug("Navigates Home page through the login page");
			
		} else {
			APPLICATION_LOGS.debug("User already loged in, so do not want to go to the log in page");
			topMenu = PageFactory.initElements(driver, TopMenu.class);
			homePage = topMenu.gotoHomePage();
			APPLICATION_LOGS.debug("Navigates Home page through the top menu");			
		}
		
		
		loginPage = homePage.gotoLogout();
		
		loginPage.VerifyIsLoginPage();
		
		APPLICATION_LOGS.debug("Successfully logged out of the system.");
		
		}

	@DataProvider
	public Object[][] getLoginData() {
		return TestUtil.getTestData("Login Test", xls);

	}
}
