package com.ptl.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.HomePage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.DOC.pages.ManageCOOPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.TestUtil;

public class reprintcancelled extends TestBase{
	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS
				.debug("Configuration file initialized in Suspend Exporter Test");
		initDriver();
		APPLICATION_LOGS
				.debug("Web Driver initialized in Suspend Exporter Test");
	}

	@Test(dataProvider = "getActivateExporterData")
	public void TestSuspendExporter(Hashtable<String, String> data) {

		if (data != null) {
			if (!TestUtil.isTestCaseRunmodeYes("ManageCOOSearch", xls)
					|| data.get("Runmode").equals("No"))
				throw new SkipException("Skipping COO Search test");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			HomePage homePage = null;

		if (!isLoggedIn) {
			APPLICATION_LOGS
					.debug("User is not logged in to the system, so navigating to the log in page");
			driver.get(CONFIG.getProperty("BASE_URL"));
			LoginPage loginPage = PageFactory.initElements(driver,
					LoginPage.class);
			APPLICATION_LOGS.debug("logged in");

			homePage = loginPage.doLogin("docdeptadmin","1qaz2wsx@");
			APPLICATION_LOGS
					.debug("Navigates Home page through the login page");

		} else {
			APPLICATION_LOGS
					.debug("User already loged in, so do not want to log in again");
			topMenu = PageFactory.initElements(driver, TopMenu.class);
			homePage = topMenu.gotoHomePage();
			APPLICATION_LOGS.debug("Move to the Home Page using the top menu");

		}
		
		ManageCOOPage manCooPage=homePage.gotoManageCOOPage();
		manCooPage.doSearchforCOO(null, "Reprinted", null, null, null, null, null);;
		manCooPage.changeStatus("Cancelled");	
	

	}
	}
	@DataProvider
	public Object[][] getActivateExporterData() {
		return TestUtil.getTestData("Manage COO Search", xls);
	}

	

}
