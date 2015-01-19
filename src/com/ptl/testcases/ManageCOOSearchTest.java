package com.ptl.testcases;

import java.text.ParseException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.HomePage;
import com.ptl.DOC.pages.LoginCredentialsPage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.DOC.pages.ManageCOOPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.DOC.pages.ViewCOOPage;
import com.ptl.util.TestUtil;

public class ManageCOOSearchTest extends TestBase {

	ManageCOOPage manCooPage;
	String COODetailsPopupWindow;

	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS
				.debug("Configuration file initialized in New Exporter Registration Test");
		initDriver();
		APPLICATION_LOGS
				.debug("Web Driver initialized in New Exporter Registration Test");
	}

	@Test(dataProvider = "getCOOSearchData")
	public void COOSearchTest(Hashtable<String, String> data) {

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
				homePage = loginPage.doLogin(data.get("Username"),
						data.get("Password"));
				APPLICATION_LOGS
						.debug("Navigates Home page through the login page");

			} else {
				APPLICATION_LOGS
						.debug("User already loged in, so do not want to go to the log in page");
				topMenu = PageFactory.initElements(driver, TopMenu.class);
				homePage = topMenu.gotoHomePage();
				APPLICATION_LOGS
						.debug("Navigates Home page through the top menu");
			}

			manCooPage = homePage.gotoManageCOOPage();

			manCooPage.doSearchforCOO(data.get("FTA"), data.get("Status"),
					data.get("Exporter"), data.get("TempRef"),
					data.get("COO Code"), data.get("From"), data.get("To"));

			// verify search results
			manCooPage.verifySearchResults(data.get("FTA"), data.get("Status"),
					data.get("Exporter"), data.get("TempRef"),
					data.get("COO Code"));
			try {
				manCooPage.verifySearchReultsDate(data.get("From"),
						data.get("To"));

			} catch (ParseException e) {
			}

			APPLICATION_LOGS.debug("Successfully searched for COO");
		}
	}
	
	@Test(dependsOnMethods="COOSearchTest",dataProvider = "getCOOSearchData")
	public void CorrectCOOLinkTest(Hashtable<String, String> data){
		
		ViewCOOPage searchedCoo = manCooPage.goToFirstCOOLink();
		manCooPage.switchToViewCOOPage();
		searchedCoo.verifyCOODetails(data.get("FTA"), data.get("Status"),
					data.get("Exporter"), data.get("TempRef"),
					data.get("COO Code"));

	}

	@DataProvider
	public Object[][] getCOOSearchData() {
		return TestUtil.getTestData("Manage COO Search", xls);
	}
}
