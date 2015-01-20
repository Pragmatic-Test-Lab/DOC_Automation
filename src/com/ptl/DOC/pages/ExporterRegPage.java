package com.ptl.DOC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ptl.util.Constants;

public class ExporterRegPage {
	
	WebDriver driver;
	
	@FindBy(xpath=Constants.CompanyName)
	WebElement companyName;
	@FindBy(xpath=Constants.BusinessRegistrationNumber)
	WebElement businessRegNum;
	@FindBy(xpath=Constants.OfficialAddress)
	WebElement offialAddress;
	@FindBy(xpath=Constants.Tel)
	WebElement tel;
	@FindBy(xpath=Constants.Fax)
	WebElement fax;
	@FindBy(xpath=Constants.ContactEmail)
	WebElement contactEmail;
	@FindBy(xpath=Constants.ContactPerson)
	WebElement contactPerson;
	@FindBy(xpath=Constants.COOName)
	WebElement cooName;
	@FindBy(xpath=Constants.COOMobile)
	WebElement cooMobile;
	@FindBy(xpath=Constants.COOEmail)
	WebElement cooEmail;
	@FindBy(xpath=Constants.AddButton)
	WebElement addButton;
	@FindBy(xpath=Constants.ExporterRegSuccessMessage)
	WebElement actualSuccessMessage;
		@FindBy(xpath=Constants.ErrorMessage)
	WebElement EmailErrorMessage;
	@FindBy(xpath=Constants.EmailErrorMessage)
	WebElement ErrorMessage;
	@FindBy(xpath=Constants.ExpRegNo)
	WebElement expRegNo;
	
	String expectedSuccessMsgText = Constants.ExporterRegSuccessMessageText;
	
	public ExporterRegPage(WebDriver dr){
		driver = dr;
	}
	
	
	public LoginCredentialsPage doRegisterNewExporter(String CompanyName, String BusinessRegNumber, String OffialAddress, 
			String Tel, String Fax, String ContactEmail, String ContactPerson , String COOName, String COOMobile, String COOEmail){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		companyName.sendKeys(CompanyName);
		businessRegNum.click();
		businessRegNum.sendKeys(BusinessRegNumber);
		offialAddress.sendKeys(OffialAddress);
		tel.click();
		tel.sendKeys(Tel);
		fax.click();
		fax.sendKeys(Fax);
		contactEmail.sendKeys(ContactEmail);
		contactPerson.sendKeys(ContactPerson);
		cooName.sendKeys(COOName);
		cooMobile.click();
		cooMobile.sendKeys(COOMobile);
		cooEmail.sendKeys(COOEmail);
		addButton.click();
		
		Alert javaScriptAlert = driver.switchTo().alert();
		System.out.println(javaScriptAlert.getText());
		javaScriptAlert.accept();
		
		String actualMsgText = actualSuccessMessage.getText();
		String expectedMsgText = expectedSuccessMsgText;
		Assert.assertTrue(actualMsgText.equalsIgnoreCase(expectedMsgText), "Exporter Registration Fail");
		
		LoginCredentialsPage loginCredPage = PageFactory.initElements(driver, LoginCredentialsPage.class);
		return loginCredPage;
		
	}
	public LoginCredentialsPage doRegisterNewExporter2(String CompanyName, String BusinessRegNumber, String OffialAddress, 
			String Tel, String Fax, String ContactEmail, String ContactPerson , String COOName, String COOEmail){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		companyName.sendKeys(CompanyName);
		businessRegNum.click();
		businessRegNum.sendKeys(BusinessRegNumber);
		offialAddress.sendKeys(OffialAddress);
		tel.click();
		tel.sendKeys(Tel);
		fax.click();
		fax.sendKeys(Fax);
		contactEmail.sendKeys(ContactEmail);
		contactPerson.sendKeys(ContactPerson);
		cooName.sendKeys(COOName);
		cooMobile.click();

		cooEmail.sendKeys(COOEmail);
		addButton.click();

		Alert javaScriptAlert = driver.switchTo().alert();

		System.out.println(javaScriptAlert.getText());
		javaScriptAlert.accept();		
		String actualMsgText = actualSuccessMessage.getText();
		String expectedMsgText = expectedSuccessMsgText;
		Assert.assertTrue(actualMsgText.equalsIgnoreCase(expectedMsgText), "Exporter Registration Passed.");

		LoginCredentialsPage loginCredPage = PageFactory.initElements(driver, LoginCredentialsPage.class);
		return loginCredPage;

	}

	public void getExpRegNoAttribute(){
		String readOnly = expRegNo.getAttribute("readonly");
		System.out.println("Read Only Text "+readOnly);
		Assert.assertTrue(expRegNo.getAttribute("readonly").equals("true"),
				"Editable");
		
	//	if(expRegNo.getAttribute("readonly").equals("true"))
	//		System.out.println("Uneditable");

	}
	public String getErrorMessage(){
	//	System.out.println(EmailErrorMessage.getText());
	//	System.out.println(	EmailErrorMessage.getText());
	//	System.out.println(Constants.EmailErrorMessage);
						Assert.assertTrue(EmailErrorMessage.getText().equals(Constants.EmailErrorMessage),
				"can register using same email");
						
		return EmailErrorMessage.getText();	

	}
	
	//public void getSuccessMessage(){
		//System.out.println(SuccessMessage2.getText());
//	}
	
	
}
