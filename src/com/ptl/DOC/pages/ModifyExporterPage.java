package com.ptl.DOC.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.ptl.util.Constants;

public class ModifyExporterPage {

	WebDriver driver;

	@FindBy(xpath = Constants.ModExp_CompanyName)
	WebElement companyName;
	@FindBy(xpath = Constants.ModExp_BusinessRegNo)
	WebElement registrationNumber;
	@FindBy(xpath = Constants.ModExp_ExpRegNo)
	WebElement expRegNo;
	@FindBy(xpath = Constants.ModExp_OfficialAddress)
	WebElement officialAddress;
	@FindBy(xpath = Constants.ModExp_Tel)
	WebElement tel;
	@FindBy(xpath = Constants.ModExp_Fax)
	WebElement fax;
	@FindBy(xpath = Constants.ModExp_ContactEmail)
	WebElement contactEmail;
	@FindBy(xpath = Constants.ModExp_ContactPerson)
	WebElement contactPerson;
	@FindBy(xpath = Constants.ModExp_COOName)
	WebElement cooName;
	@FindBy(xpath = Constants.ModExp_COOMobile)
	WebElement cooMobile;
	@FindBy(xpath = Constants.ModExp_COOEmail)
	WebElement cooEmail;
	@FindBy(xpath = Constants.ModExp_ModifyButton)
	WebElement modifyButton;
	@FindBy(xpath = Constants.SuccessfullyModifiedMessage)
	WebElement modifiedMessage;

	public ModifyExporterPage(WebDriver dr) {
		driver = dr;
	}

	public void doUpdateExporterDetails(String CompanyName,
			String BusinessRegNo, String OfficialAddress, String Tel,
			String Fax, String ContactEmail, String ContactPerson,
			String COOName, String COOMobile, String COOEmail) {

		if (!companyName.getAttribute("value").equalsIgnoreCase(CompanyName)) {
			companyName.clear();
			companyName.sendKeys(CompanyName);
		}

		if (!expRegNo.getAttribute("value").equalsIgnoreCase(BusinessRegNo)) {
			expRegNo.clear();
			expRegNo.sendKeys(BusinessRegNo);
		}

		if (!officialAddress.getAttribute("value").equalsIgnoreCase(
				OfficialAddress)) {
			officialAddress.clear();
			officialAddress.sendKeys(OfficialAddress);
		}

		if (!tel.getAttribute("value").equalsIgnoreCase(Tel)) {
			tel.clear();
			tel.sendKeys(Tel);
		}

		if (!fax.getAttribute("value").equalsIgnoreCase(Fax)) {
			fax.clear();
			fax.sendKeys(Fax);
		}

		if (!contactEmail.getAttribute("value").equalsIgnoreCase(ContactEmail)) {
			contactEmail.clear();
			contactEmail.sendKeys(ContactEmail);
		}

		if (!contactPerson.getAttribute("value")
				.equalsIgnoreCase(ContactPerson)) {
			contactPerson.clear();
			contactPerson.sendKeys(ContactPerson);
		}

		if (!cooName.getAttribute("value").equalsIgnoreCase(COOName)) {
			cooName.clear();
			cooName.sendKeys(COOName);
		}

		if (!cooMobile.getAttribute("value").equalsIgnoreCase(COOMobile)) {
			cooMobile.clear();
			cooMobile.sendKeys(COOMobile);
		}

		if (!cooEmail.getAttribute("value").equalsIgnoreCase(COOEmail)) {
			cooEmail.clear();
			cooEmail.sendKeys(COOEmail);
		}

		modifyButton.click();
		
		Alert javaScriptAlert = driver.switchTo().alert();
		System.out.println(javaScriptAlert.getText());
		javaScriptAlert.accept();

	}

	public void verifyModification() {
		
		String actualMsgText = modifiedMessage.getText();
		String expectedMsgText = Constants.SuccessfullyModifiedMessageText;
		Assert.assertTrue(actualMsgText.equalsIgnoreCase(expectedMsgText),
				"Exporter Detail Modification has failed.");

	}


}
