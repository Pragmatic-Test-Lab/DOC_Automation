package com.ptl.DOC.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.ptl.util.Constants;

public class ApplyCOOPaymentPage {
	
	WebDriver driver;
	
	@FindBy(xpath=Constants.TransRefNoField)
	WebElement TransRefNoField;	
	@FindBy(xpath=Constants.ApplicationFeeField)
	WebElement ApplicationFeeField;	
	@FindBy(xpath=Constants.AdditionCopyFeeField)
	WebElement AdditionCopyFeeField;	
	@FindBy(xpath=Constants.DeliveryCostField)
	WebElement DeliveryCostField;	
	@FindBy(xpath=Constants.TotalCostField)
	WebElement TotalCostField;	
	@FindBy(xpath=Constants.PayButton)
	WebElement PayButton;
	@FindBy(xpath=Constants.ConfirmButton)
	WebElement ConfirmButton;
	
	@FindBy(xpath=Constants.COOPayment_PageHeading)
	WebElement PageHeading;
	
	
	public ApplyCOOPaymentPage(WebDriver dr){
		driver = dr;
	}
	
	
	public void checkTransactionRefNo() {
		
		String refNo = TransRefNoField.getText();
		Assert.assertTrue(!refNo.isEmpty(), "Transaction Refference Number was not genarated");	

		String reganratedRefNoFormat = refNo.substring(0,8);
		
		Assert.assertTrue(reganratedRefNoFormat.equals(Constants.TransRefNoFormat),
				"Transaction Refference Number format is incorrect");			
		
	}
	
	
	public void checkPayment(String CollectionMethod, int NumberOfCopies){
		
		double applicationFee = Double.parseDouble(ApplicationFeeField.getText()); 
		double copyFee = Double.parseDouble(ApplicationFeeField.getText()); 
		double postalFee = Double.parseDouble(ApplicationFeeField.getText()); 
		
		Assert.assertTrue(applicationFee == Constants.ApplicationFee, "Application Fee is Incorrect");	
		Assert.assertTrue((copyFee == Constants.AdditionalCopyFee * NumberOfCopies),
				"Fee for Additional Copies is Incorrect");	
		
		if(CollectionMethod.equalsIgnoreCase("Post"))
		Assert.assertTrue(postalFee == Constants.PostalFee, "Postal Fee is Incorrect");			
		
	}
	
	public void checkPaymentGateway(){
		
		PayButton.click();
		ConfirmButton.click();
		String message = PageHeading.getAttribute("alt");
		Assert.assertTrue(message.equals(Constants.COOPaymentGatewayMessage),
				"Unsuccesfull at proceeding to payment gateway");
		
	}


}
