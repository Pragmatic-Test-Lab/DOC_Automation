package com.ptl.DOC.pages;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.ptl.util.Constants;

;

public class CostStatementAffidavitDetailsPage {

	WebDriver driver;

	String statusFirstPart = Constants.CostStatementDetail_StatusFirstPart;
	String statusLastPart = Constants.CostStatementDetail_StatusLastPart;
	@FindBy(xpath = Constants.CostStatementDetail_DocumentType)
	WebElement documentType;
	@FindBy(xpath = Constants.CostStatementDetail_StatementNo)
	WebElement statement;
	@FindBy(xpath = Constants.CostStatementDetail_FetchButton)
	WebElement fetchButton;
	@FindBy(xpath = Constants.CostStatementDetail_Exporter)
	WebElement exporter;
	@FindBy(xpath = Constants.CostStatementDetail_ExporterDropdownFirst)
	WebElement exporterDropdownFirst;
	@FindBy(xpath = Constants.CostStatementDetail_FTA)
	WebElement fta;
	@FindBy(xpath = Constants.CostStatementDetail_Product)
	WebElement product;
	@FindBy(xpath = Constants.CostStatementDetail_ProductDropdownFirst)
	WebElement productDropdownFirst;
	@FindBy(xpath = Constants.CostStatementDetail_Quantity)
	WebElement quantity;
	@FindBy(xpath = Constants.CostStatementDetail_Unit)
	WebElement unit;
	@FindBy(xpath = Constants.CostStatementDetail_ValidThrough)
	WebElement validThroughCalendar;
	@FindBy(xpath = Constants.CostStatementDetail_DatePicker)
	WebElement datePicker;
	@FindBy(xpath = Constants.CostStatementDetail_DatePickerMonth)
	WebElement datePickerMonth;
	@FindBy(xpath = Constants.CostStatementDetail_DatePickerYear)
	WebElement datePickerYear;
	@FindBy(xpath = Constants.CostStatementDetail_DatePickerNext)
	WebElement datePickerNext;
	@FindBy(xpath = Constants.CostStatementDetail_DatePickerPrev)
	WebElement datePickerPrev;
	@FindBy(xpath = Constants.CostStatementDetail_To)
	WebElement toCalendar;
	@FindBy(xpath = Constants.CostStatementDetail_Submit)
	WebElement submitButton;
	@FindBy(xpath = Constants.SubmitMessage)
	WebElement SubmitMessage;
	

	List<WebElement> noOfColumns;
	List<String> monthList = Arrays.asList("January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December");
	// Expected Date, Month and Year
	int expMonth;
	int expYear;
	String expDate = null;
	// Calendar Month and Year
	String calMonth = null;
	String calYear = null;
	boolean dateNotFound;

	public CostStatementAffidavitDetailsPage(WebDriver dr) {
		driver = dr;
	}

	public void doCreateNewCostStatement(String Status, String DocumentType, String StatementNo,
			String Exporter, String FTA, String Product, String Quantity,
			String Unit, String ValidThrough, String To) {

		if (Status.equalsIgnoreCase("New")) {
			driver.findElement(By.xpath(statusFirstPart + 1 + statusLastPart))
					.click();
			//checks if CostEstimate/Affidavit No. Field is disabled
			statementNumDisabled();
			
		} else if (Status.equalsIgnoreCase("Modify")) {
			driver.findElement(By.xpath(statusFirstPart + 2 + statusLastPart))
					.click();			
		}

		documentType.sendKeys(DocumentType);
		exporter.sendKeys(Exporter);
		exporterDropdownFirst.click();
		fta.sendKeys(FTA);
		product.sendKeys(Product + Keys.DOWN);
		productDropdownFirst.click();
		quantity.sendKeys(Quantity);
		unit.sendKeys(Unit);

		try {

			validThroughCalendar.click();
			pickDate(ValidThrough);

			toCalendar.click();
			pickDate(To);

		} catch (InterruptedException e) {
		}
		
		submitButton.click();		
		
		String Message = SubmitMessage.getText();		
		String submitResult = Message.substring(0,74);
		
		Assert.assertTrue(submitResult.equals(Constants.ExpectedMessage),
				"Submitting Cost Estimate/Affidavit has failed.");

	}

	
	public void pickDate(String Date) throws InterruptedException {

		dateNotFound = true;

		// Set your expected date, month and year.
		String[] dates = Date.split("/");
		expDate = dates[0];
		String ExpMonth = dates[1];
		expMonth = Integer.parseInt(ExpMonth);
		String ExpYear = dates[2];
		expYear = Integer.parseInt(ExpYear);

		// This loop will be executed continuously till dateNotFound Is true.
		while (dateNotFound) {
			// Retrieve current selected month name from date picker popup.
			calMonth = datePickerMonth.getText();
			// Retrieve current selected year name from date picker popup.
			calYear = datePickerYear.getText();

			// If current selected month and year are same as expected month and
			// year then go Inside this condition.
			if (monthList.indexOf(calMonth) + 1 == expMonth
					&& (expYear == Integer.parseInt(calYear))) {
				// Call selectDate function with date to select and set
				// dateNotFound flag to false.
				selectDate(expDate);
				dateNotFound = false;
			}
			// If current selected month and year are less than expected month
			// and year then go Inside this condition.
			else {
				if (monthList.indexOf(calMonth) + 1 < expMonth && (expYear == Integer.parseInt(calYear))
						|| expYear > Integer.parseInt(calYear)) {
					// Click on next button of date picker.
					datePickerNext.click();
				} else
					datePickerPrev.click();
			}
		}

	}

	public void selectDate(String date) {
		noOfColumns = datePicker.findElements(By.tagName("td"));

		// Loop will rotate till expected date not found.
		for (WebElement cell : noOfColumns) {
			// Select the date from date picker when condition match.
			if (cell.getText().equals(date)) {
				cell.findElement(By.linkText(date)).click();
				break;
			}
		}
	}
	
	public void statementNumDisabled(){
		
		//checks if statement no field in disabled
		String readonly = statement.getAttribute("readonly");
		
		Assert.assertTrue(readonly.equals("true"),
				"Statement Number Field is enabled");
		
	}
	
	
	public void checkGenaratedRefNo(){
		
		String submitMessage = SubmitMessage.getText();
		String CSAffidvitNo = submitMessage.substring(75, (submitMessage.length()-1) );
		
		Assert.assertTrue(!CSAffidvitNo.isEmpty(),
				"Cost Estimate/Affidavit Refference Number was not Genarated.");
		
		System.out.println("Created affidavit received Ref No: " + CSAffidvitNo);
		
	}
	
	

}
