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
import com.ptl.DOC.pages.ManageExportersPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.TestUtil;

public class ExporterSuspendTest extends TestBase {
	
	@BeforeSuite
	public void init(){
		initConfiguration();
		APPLICATION_LOGS.debug("Configuration file initialized in Suspend Exporter Test");
		initDriver();
		APPLICATION_LOGS.debug("Web Driver initialized in Suspend Exporter Test");
	}
	
	
	@Test(dataProvider="getSuspendExporterData")
	public void TestSuspendExporter(Hashtable<String, String> data) {
		if(!TestUtil.isTestCaseRunmodeYes("Suspend Exp", xls) || data.get("Runmode").equals("No"))
			throw new SkipException("Skipping the Suspend Exporter test");
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		HomePage homePage = null;
		

		if(!isLoggedIn){
			APPLICATION_LOGS.debug("User is not logged in to the system, so navigating to the log in page");
			driver.get(CONFIG.getProperty("BASE_URL"));
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			APPLICATION_LOGS.debug("logged in");
			homePage = loginPage.doLogin(data.get("Username"), data.get("Password"));
			APPLICATION_LOGS.debug("Navigates Home page through the login page");

		} else {
			APPLICATION_LOGS.debug("User already loged in, so do not want to log in again");
			topMenu = PageFactory.initElements(driver, TopMenu.class);
			homePage = topMenu.gotoHomePage();
			APPLICATION_LOGS.debug("Move to the Home Page using the top menu");

		}
		
		ManageExportersPage manageExpPage = homePage.gotoManageExportersPage();
		manageExpPage.SearchExporter(data.get("Exp RegNo"), data.get("Status"));
		APPLICATION_LOGS.debug("Searched an exporter");
		
		manageExpPage.doSuspendExporter();
		APPLICATION_LOGS.debug("Suspend an exporter");
		manageExpPage.getSuspendStatusMessage();
		
	}
	
	
	@DataProvider
	public Object[][] getSuspendExporterData(){
		return TestUtil.getTestData("Suspend Exp", xls);
	}		

}
