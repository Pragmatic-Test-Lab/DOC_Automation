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

public class CancelCOOTest extends TestBase {

	HomePage homePage = null;
	ManageCOOPage manCOOPage = null;

	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS
				.debug("Configuration file initialized in New Exporter Registration Test");
		initDriver();
		APPLICATION_LOGS
				.debug("Web Driver initialized in New Exporter Registration Test");
	}

	@Test(dataProvider = "getLoginData")
	public void LoginTest(Hashtable<String, String> data) {

		if (data != null) {
			if (!TestUtil.isTestCaseRunmodeYes("ManageCOOSearch", xls)
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
		manCOOPage = homePage.gotoManageCOOPage();
	}

	@Test(dependsOnMethods = "LoginTest")
	public void CancelApprovedCOOTest() {

		manCOOPage.doSearchforCOO(null, "Approved", null, null, null, null, null);
		
		manCOOPage.NoOfSearchResults();
		manCOOPage.verifySearchResults(null,"Approved",null,null,null);
		
		String ChangedCOORefNo = manCOOPage.changeStatus("Cancelled");
		
		manCOOPage.doSearchforCOO(null, "Cancelled", null, ChangedCOORefNo, null, null, null);
		manCOOPage.verifySearchResults(null,"Cancelled",null,ChangedCOORefNo,null);
		
		APPLICATION_LOGS
		.debug("Status changed from Approved to Cancelled Successfully");

	}
	
	@Test(dependsOnMethods = "LoginTest")
	public void CancelPrintedCOOTest() {

		manCOOPage.doSearchforCOO(null, "Printed", null, null, null, null, null);
		
		manCOOPage.NoOfSearchResults();
		manCOOPage.verifySearchResults(null,"Printed",null,null,null);
		
		String ChangedCOORefNo = manCOOPage.changeStatus("Cancelled");
		
		manCOOPage.doSearchforCOO(null, "Cancelled", null, ChangedCOORefNo, null, null, null);
		manCOOPage.verifySearchResults(null,"Cancelled",null,ChangedCOORefNo,null);
		
		APPLICATION_LOGS
		.debug("Status changed from Printed to Cancelled Successfully");

	}
	
	
	@Test(dependsOnMethods = "LoginTest")
	public void CancelReprintedCOOTest() {

		manCOOPage.doSearchforCOO(null, "Reprinted", null, null, null, null, null);
		
		manCOOPage.NoOfSearchResults();
		manCOOPage.verifySearchResults(null,"Reprinted",null,null,null);
		
		String ChangedCOORefNo = manCOOPage.changeStatus("Cancelled");
		
		manCOOPage.doSearchforCOO(null, "Cancelled", null, ChangedCOORefNo, null, null, null);
		manCOOPage.verifySearchResults(null,"Cancelled",null,ChangedCOORefNo,null);
		
		APPLICATION_LOGS
		.debug("Status changed from Reprinted to Cancelled Successfully");

	}

	

	@DataProvider
	public Object[][] getLoginData() {
		return TestUtil.getTestData("Checked COO Actions", xls);
	}
	
}
