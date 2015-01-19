package com.ptl.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ptl.DOC.pages.ExporterRegPage;
import com.ptl.DOC.pages.HomePage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.Constants;
import com.ptl.util.TestUtil;

public class doc244duplicateemail extends TestBase {
	@BeforeSuite
	public void init(){
		initConfiguration();
		APPLICATION_LOGS.debug("Configuration file initialized in New Exporter Registration Test");
		initDriver();
		APPLICATION_LOGS.debug("Web Driver initialized in New Exporter Registration Test");
	}
	
	
	@Test(dataProvider="getExporterRegData")
	public void RegNewExpTest(Hashtable<String,String> data){
		if(!TestUtil.isTestCaseRunmodeYes("ExpReg", xls) || data.get("Runmode").equals("No"))
			throw new SkipException("Skipping the Exporter Registration test");
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
			APPLICATION_LOGS.debug("User already loged in, so do not want to go to the log in page");
			topMenu = PageFactory.initElements(driver, TopMenu.class);
			homePage = topMenu.gotoHomePage();
			APPLICATION_LOGS.debug("Navigates Home page through the top menu");
			
		}
		
		ExporterRegPage expRegPage = homePage.gotoExporterRegPage();
		expRegPage.doRegisterNewExporter(data.get("Company Name"), data.get("Business RegNo"), data.get("Official Address"), 
				data.get("Tel"), data.get("Fax"), data.get("Contact Email"), data.get("Contact Person"), data.get("COO Name"), 
				data.get("COO Mobile"), data.get("COO Email")) ;
		
		APPLICATION_LOGS.debug("Successfully registered a new exporter");
		//String emailerror=expRegPage.getErrorMessage();
		expRegPage.getErrorMessage();
		
		
	}
	@DataProvider
	
	public Object[][] getExporterRegData(){
		return TestUtil.getTestData("ExpReg", xls);
}}
