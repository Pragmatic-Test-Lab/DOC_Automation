package com.ptl.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.ApplyCOOPage;
import com.ptl.DOC.pages.ApplyCOOPaymentPage;
import com.ptl.DOC.pages.CitizenHomePage;
import com.ptl.DOC.pages.CitizenLoginPage;
import com.ptl.DOC.pages.CitizenTopMenu;
import com.ptl.util.TestUtil;

public class SaveNewCOOTest extends TestBase {
	

	String TempRefNumber = null;

	@BeforeSuite
	public void init() {
		initConfiguration();
		APPLICATION_LOGS
				.debug("Configuration file initialized in New Exporter Registration Test");
		initDriver();
		APPLICATION_LOGS
				.debug("Web Driver initialized in New Exporter Registration Test");
	}

	@Test(dataProvider = "getCOOApplicationData")
	public void SaveCOOTest(Hashtable<String, String> data) {

		if (data != null) {
			if (!TestUtil.isTestCaseRunmodeYes("ApplyCOO", xls)
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
				citizenTopMenu = PageFactory.initElements(driver,
						CitizenTopMenu.class);
				homePage = citizenTopMenu.gotoCitizenHomePage();
				APPLICATION_LOGS
						.debug("Navigates Home page through the top menu");
			}

			ApplyCOOPage applyCOOPage = homePage.gotoApplyCOOPage();	
			
			
			applyCOOPage.ApplyNewCOO(data.get("FTA"),
					data.get("ExporterAddress"), data.get("ModeofTransport"),
					data.get("VesselNo"), data.get("PortofLoading"),
					data.get("PortofDischarging"), data.get("cosigneeeName"),
					data.get("cosigneeeAddress"), data.get("cosigneeeCountry"),
					data.get("Declaration"), data.get("CUSDEC"),
					data.get("OfficeCode"), data.get("VoyageCode"),
					data.get("DepartureDate"), data.get("DeclaredCountry"),
					data.get("NoOfCopies"), data.get("CollectionMethod"),
					data.get("PostalAddress"));

			applyCOOPage.AddNewProduct(data.get("HSCode"),
					data.get("ProductMark"), data.get("ProductNo"),
					data.get("Criterion"), data.get("Weight"),
					data.get("Unit"), data.get("Net Weight"),
					data.get("InvoiceNo"), data.get("FOB"),
					data.get("InvoiceDateField"));

		TempRefNumber = applyCOOPage.SaveCOOApplication();

		APPLICATION_LOGS.debug("Successfully saved COO application");
		}
	}

	@Test(dependsOnMethods="SaveCOOTest", dataProvider = "getCOOApplicationData")
	public void VerifySavedNewCOO(Hashtable<String, String> data){
		CitizenHomePage citihomePage = null;
		
		citizenTopMenu = PageFactory.initElements(driver,
				CitizenTopMenu.class);
		
		citihomePage = citizenTopMenu.gotoCitizenHomePage();
		ApplyCOOPage applySavedCOOPage = citihomePage.gotoApplyCOOPage();
		
		applySavedCOOPage.retrieveSavedApplication(TempRefNumber);

		
		applySavedCOOPage.CompareCOOApplicationData(data.get("FTA"),
				data.get("ExporterAddress"), data.get("ModeofTransport"),
				data.get("VesselNo"), data.get("PortofLoading"),
				data.get("PortofDischarging"), data.get("cosigneeeName"),
				data.get("cosigneeeAddress"), data.get("cosigneeeCountry"),
				data.get("Declaration"), data.get("CUSDEC"),
				data.get("OfficeCode"), data.get("VoyageCode"),
				data.get("DepartureDate"), data.get("DeclaredCountry"),
				data.get("NoOfCopies"), data.get("CollectionMethod"),
				data.get("PostalAddress"));
		
	}


	@DataProvider
	public Object[][] getCOOApplicationData() {
		return TestUtil.getTestData("SaveNewCOO", xls);
	}

}
