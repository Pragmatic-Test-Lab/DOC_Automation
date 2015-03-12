package com.ptl.DOC.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ptl.testcases.TestBase;
import com.ptl.util.Constants;

public class CitizenLoginPage {

	public WebDriver driver;
	@FindBy(xpath = Constants.username)
	public WebElement username;
	@FindBy(xpath = Constants.password)
	public WebElement password;
	@FindBy(xpath = Constants.login)
	public WebElement login;
	@FindBy(xpath = Constants.LogOut)
	public WebElement logOut;

	@FindBy(xpath = Constants.LoginPageHeader)
	WebElement LoginPageHeader;

	public CitizenLoginPage(WebDriver dr) {
		driver = dr;
	}

	public CitizenHomePage doCitizenLogin(String uName, String pWord) {

		username.sendKeys(uName);
		password.sendKeys(pWord);
		login.click();
		// validate login - successful

		CitizenHomePage homePage = PageFactory.initElements(driver,
				CitizenHomePage.class);
		String LOGOUT = logOut.getText();
		if (LOGOUT.equalsIgnoreCase("Logout")) {
			TestBase.isLoggedIn_CitizenApp = true;
		} else {
			TestBase.isLoggedIn_CitizenApp = false;
		}
		return homePage;
	}

	public void VerifyIsLoginPage() {

		Assert.assertTrue(
				LoginPageHeader.getText().equals(Constants.LoginPageTitle),
				"Did not arrived at Login Page.");
	}

}
