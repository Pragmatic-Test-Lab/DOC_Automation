package com.ptl.DOC.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.ptl.util.Constants;

public class StatisticReportPage {

	WebDriver driver;

	@FindBy(xpath = Constants.DateFrom)
	WebElement DateFrom;
	@FindBy(xpath = Constants.DateTo)
	WebElement DateTo;
	@FindBy(xpath = Constants.StatReport_fta)
	WebElement FTA;
	@FindBy(xpath = Constants.StatReport_exportingCountry)
	WebElement ExportingCountry;
	@FindBy(xpath = Constants.StatReport_productCode)
	WebElement ProductCode;
	@FindBy(xpath = Constants.StatReport_exporter)
	WebElement Exporter;
	@FindBy(xpath = Constants.StatReport_searchButton)
	WebElement SearchButton;
	
	@FindBy(xpath = Constants.StatReport_SearchResultsFOBTotal)
	WebElement FOBTotal;

	@FindBy(xpath = Constants.StatReport_DatePicker)
	WebElement datePicker;
	@FindBy(xpath = Constants.StatReport_DatePickerMonth)
	WebElement datePickerMonth;
	@FindBy(xpath = Constants.StatReport_DatePickerYear)
	WebElement datePickerYear;
	@FindBy(xpath = Constants.StatReport_DatePickerNext)
	WebElement datePickerNext;
	@FindBy(xpath = Constants.StatReport_DatePickerPrev)
	WebElement datePickerPrev;

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
	
	
	public StatisticReportPage(WebDriver dr) {
		driver = dr;
	}

	public void doSearch(String dateFrom, String dateTo, String fta,
			String exportingCountry, String productCode, String exporter) {

		try {
			if (dateFrom != null && !dateFrom.isEmpty()) {
				DateFrom.click();
				pickDate(dateFrom);
			}
			if (dateTo != null && !dateTo.isEmpty()) {
				DateTo.click();
				pickDate(dateTo);
			}

		} catch (InterruptedException e) {
		}

		FTA.sendKeys(fta);
		ExportingCountry.sendKeys(exportingCountry);
		ProductCode.sendKeys(productCode);
		Exporter.sendKeys(exporter);

		SearchButton.click();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {}

	}

	public void verifySearchByFTAResults(String fta) {

		// search results to verify search success/failure
		List<WebElement> SearchResultFtas = driver.findElements
				(By.xpath(Constants.StatReport_SearchResultsFTAs));

		Assert.assertTrue(SearchResultFtas.size() != 0,
					"No Search Results to continue Test");

		if (fta != null && !SearchResultFtas.isEmpty()
				&& !fta.equalsIgnoreCase("ALL"))
			for (int i = 0; i < SearchResultFtas.size() - 1; i++) {
				String FTA = SearchResultFtas.get(i).getText();

				Assert.assertTrue(fta.equalsIgnoreCase(FTA),
						"Report sort by FTA has failed.");
			}


	}
	public void verifySearchByExpoCountryResults(String exportingCountry) {
		
		List<WebElement> SearchResultExpoCountry = driver.findElements(By
				.xpath(Constants.StatReport_SearchResultsExportingCountry));
		
		Assert.assertTrue(SearchResultExpoCountry.size() != 0,
				"No Search Results to continue Test");
		
		if (exportingCountry != null && !SearchResultExpoCountry.isEmpty()
				&& !exportingCountry.equalsIgnoreCase("ALL"))
			for (int i = 0; i < SearchResultExpoCountry.size(); i++) {
				String expoCountry = SearchResultExpoCountry.get(i).getText();

				Assert.assertTrue(exportingCountry.equalsIgnoreCase(expoCountry),
						"Report sort by Exporting Country has failed.");
			}
	}
		
	public void verifySearchByProCodeResults(String productCode) {
		
		List<WebElement> SearchResultProductCodes = driver.findElements(By
				.xpath(Constants.StatReport_SearchResultsProductCodes));
		
		Assert.assertTrue(SearchResultProductCodes.size() != 0,
				"No Search Results to continue Test");

		if (productCode != null && !SearchResultProductCodes.isEmpty())
			for (int i = 0; i < SearchResultProductCodes.size(); i++) {
				String ProductCode = SearchResultProductCodes.get(i).getText();
				
				Assert.assertTrue(productCode.equalsIgnoreCase(ProductCode),
						"Report sort by Product Code has failed.");
			}
		
	}
	
	public void verifyTotalFOBValue(){
		
		List<WebElement> FOBValues = driver.findElements(By
				.xpath(Constants.StatReport_SearchResultsFOBValues));
		
		double fobTotal = Double.parseDouble(FOBTotal.getText());
		double expectedFOBTotal = 0.00;
		
		for(int i = 0; i < FOBValues.size() ; i++){
			
			expectedFOBTotal = expectedFOBTotal + Double.parseDouble(FOBValues.get(i).getText());
		}
		
		Assert.assertTrue(fobTotal == expectedFOBTotal,
				"Total FOB has not been caluclated properly");		
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
				if (monthList.indexOf(calMonth) + 1 < expMonth
						&& (expYear == Integer.parseInt(calYear))
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

}
