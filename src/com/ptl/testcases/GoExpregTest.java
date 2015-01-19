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
import com.ptl.DOC.pages.LoginCredentialsPage;
import com.ptl.DOC.pages.LoginPage;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.TestUtil;

public class GoExpregTest extends TestBase{

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
		
		ExporterRegPage expRegPage = homePage.gotoExporterRegPage2();
		APPLICATION_LOGS.debug("Successfully registered a new exporter");
		
		}
	
	
	@Test(dependsOnMethods="RegNewExpTest")
	public void ReadExporterRegistrationNumberTest(){
		LoginCredentialsPage loginCredPage = PageFactory.initElements(driver, LoginCredentialsPage.class);
		loginCredPage.ReadExporterRegistrationNumber();
	}
	
	
	@Test(dependsOnMethods="RegNewExpTest")
	public void RegisteredExporterLoginTest(){
		LoginCredentialsPage loginCredPage = PageFactory.initElements(driver, LoginCredentialsPage.class);
		String Username = loginCredPage.getAutoGenExpUsername();		
		String Password = loginCredPage.getAutoGenExpPassword();
		LoginPage loginPage = loginCredPage.doLogout();
		loginPage.doLogin(Username, Password);
		
	}
	
	
	@DataProvider
	public Object[][] getExporterRegData(){
		return TestUtil.getTestData("ExpReg", xls);
	}	
	

}
