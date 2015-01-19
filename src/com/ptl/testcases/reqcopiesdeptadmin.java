package com.ptl.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.CitizenHomePage;
import com.ptl.DOC.pages.CitizenLoginPage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.DOC.pages.RequestCopies;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.TestUtil;

public class reqcopiesdeptadmin extends TestBase {
	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS
				.debug("Configuration file initialized in New Exporter Registration Test");
		initDriver();
		APPLICATION_LOGS
				.debug("Web Driver initialized in New Exporter Registration Test");
	}

	@Test(dataProvider = "getRequestData")
	public void ReqCopies(Hashtable<String, String> data) {

		if (data != null) {
			if (!TestUtil.isTestCaseRunmodeYes("RequestCopies", xls)
					|| data.get("Runmode").equals("No"))
				throw new SkipException("Skipping the test");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CitizenHomePage homePage = null;

			if (!isLoggedIn_CitizenApp) {
				APPLICATION_LOGS
						.debug("User is not logged in to the system, so navigating to the log in page");
				driver.get(CONFIG.getProperty("BASE_URL_CITIZEN_APP"));
				CitizenLoginPage loginPage = PageFactory.initElements(driver,
						CitizenLoginPage.class);
				APPLICATION_LOGS.debug("logged in");
				homePage = loginPage.doCitizenLogin(data.get("Username"),
						data.get("Password"));
				APPLICATION_LOGS
						.debug("Navigates Home page through the login page");
			} else {
				APPLICATION_LOGS
						.debug("User already loged in, so do not want to go to the log in page");
				topMenu = PageFactory.initElements(driver, TopMenu.class);
				homePage = citizenTopMenu.gotoCitizenHomePage();
				APPLICATION_LOGS
						.debug("Navigates Home page through the top menu");
			}

			RequestCopies ReqcopiesPage = homePage.gotoRequestcopyPage();
			ReqcopiesPage.doRequestCopies(data.get("Exporter Registration"),
					data.get("COO Ref No"), data.get("No of Copies"),
					data.get("Collection Method"), data.get("Postal Address"),
					data.get("Receipt No"));
			ReqcopiesPage.getName();
			ReqcopiesPage.getNoofCopies();
			ReqcopiesPage.saveit();

			APPLICATION_LOGS.debug("Successfully registered a new exporter");
		}

	}

	@DataProvider
	public Object[][] getRequestData() {
		return TestUtil.getTestData("RequestCopies", xls);
	}
}
