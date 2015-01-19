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
import com.ptl.DOC.pages.StatisticReportPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.TestUtil;

public class StatisticReportPageSearchTest extends TestBase{

	HomePage homePage = null;
	StatisticReportPage statReportPage = null;

	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS
				.debug("Configuration file initialized in New Exporter Registration Test");
		initDriver();
		APPLICATION_LOGS
				.debug("Web Driver initialized in New Exporter Registration Test");
	}

	@Test(dataProvider = "getSearchData")
	public void GoToStatisticsPage(Hashtable<String, String> data) {

		if (data != null) {
			if (!TestUtil.isTestCaseRunmodeYes("StatReportSearch", xls)
					|| data.get("Runmode").equals("No"))
				throw new SkipException("Skipping COO Search test");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
		}
		
		statReportPage = homePage.gotoStatisticReportPage();
		
		APPLICATION_LOGS
		.debug("Successfully navigated to Statistics Report Page");
	}

	@Test(dependsOnMethods = "GoToStatisticsPage", dataProvider = "getSearchData")
	public void SearchStatisticsPageTest(Hashtable<String, String> data) {
		
		statReportPage.doSearch(data.get("From"), data.get("To"), data.get("FTA"),
				data.get("Exporting Country"), data.get("Product Code"), data.get("Exporter"));
		
		
		statReportPage.verifySearchByFTAResults(data.get("FTA"));
		APPLICATION_LOGS.debug("Search by fta was done successfully");

		
		statReportPage.verifySearchByProCodeResults(data.get("Product Code"));
		APPLICATION_LOGS.debug("Search  by product code was done successfully");		
		
	}
	
	
	@Test(dependsOnMethods = "GoToStatisticsPage")
	public void verifyDiaplyedFOBTotalTest() {
		
		statReportPage.verifyTotalFOBValue();
		
		APPLICATION_LOGS.debug("FOB Total displayed correctly.");		
		
	}
	

	@DataProvider
	public Object[][] getSearchData() {
		return TestUtil.getTestData("StatReportSearch", xls);
	}
	
}
