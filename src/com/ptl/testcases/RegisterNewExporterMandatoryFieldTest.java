package com.ptl.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.ExporterRegPage;
import com.ptl.DOC.pages.HomePage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.TestUtil;

public class RegisterNewExporterMandatoryFieldTest extends TestBase {

	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS
				.debug("Configuration file initialized in Suspend Exporter Test");
		initDriver();
		APPLICATION_LOGS
				.debug("Web Driver initialized in Suspend Exporter Test");
	}

	@Test(dataProvider = "getExporterRegData")
	public void MandatoryFieldTest(Hashtable<String, String> data) {

		if (!TestUtil.isTestCaseRunmodeYes("ExpReg", xls)
				|| data.get("Runmode").equals("No"))
			throw new SkipException("Skipping the Exporter Registration test");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage homePage = null;

		if (!isLoggedIn) {
			APPLICATION_LOGS
					.debug("User is not logged in to the system, so navigating to the log in page");
			driver.get(CONFIG.getProperty("BASE_URL"));
			LoginPage loginPage = PageFactory.initElements(driver,
					LoginPage.class);
			APPLICATION_LOGS.debug("logged in");
			homePage = loginPage.doLogin(data.get("Username"),
					data.get("Password"));
			APPLICATION_LOGS
					.debug("Navigates Home page through the login page");

		} else {
			APPLICATION_LOGS
					.debug("User already loged in, so do not want to log in again");
			topMenu = PageFactory.initElements(driver, TopMenu.class);
			homePage = topMenu.gotoHomePage();
			APPLICATION_LOGS.debug("Move to the Home Page using the top menu");

		}

		ExporterRegPage expRegPage = homePage.gotoExporterRegPage();
		expRegPage.doRegisterExporterwithBlankFields(data.get("Company Name"),
				data.get("Business RegNo"), data.get("Official Address"),
				null, data.get("Fax"), data.get("Contact Email"),
				data.get("Contact Person"), null,
				data.get("COO Email"));

		APPLICATION_LOGS.debug("Mandetory field check successfull");

	}

	@DataProvider
	public Object[][] getExporterRegData() {
		return TestUtil.getTestData("ExpReg", xls);
	}
}