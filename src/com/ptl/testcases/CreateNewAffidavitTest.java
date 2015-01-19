package com.ptl.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.CostStatementAffidavitDetailsPage;
import com.ptl.DOC.pages.HomePage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.TestUtil;

public class CreateNewAffidavitTest extends TestBase {

	CostStatementAffidavitDetailsPage costStatementPage;
	
	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS
				.debug("Configuration file initialized in New Cost Statement Test");
		initDriver();
		APPLICATION_LOGS
				.debug("Web Driver initialized in New Cost Statement Test");
	}

	@Test(dataProvider = "getCreateNewCostStatementData")
	public void NewAffidavitTest(Hashtable<String, String> data) {
		if (!TestUtil.isTestCaseRunmodeYes("Affidavit Test", xls)
				|| data.get("Runmode").equals("No"))
			throw new SkipException(
					"Skipping the Create New Cost Statement test");

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

		costStatementPage = homePage.gotoCostStatementAffidavitDetailsPage();
		costStatementPage.doCreateNewCostStatement(data.get("Status"),
				data.get("Document Type"), null, data.get("Exporter"), data.get("FTA"), data.get("Product"),
				data.get("Quantity"), data.get("Unit"),
				data.get("Valid Through"), data.get("To"));
		
		APPLICATION_LOGS.debug("Successfully created a cost statement");

	}
	
	@Test(dependsOnMethods = "NewAffidavitTest")
	public void RefNoGenaratedTest() {

		costStatementPage.checkGenaratedRefNo();
	}

	@DataProvider
	public Object[][] getCreateNewCostStatementData() {
		return TestUtil.getTestData("Create Affidavit", xls);
	}

}
