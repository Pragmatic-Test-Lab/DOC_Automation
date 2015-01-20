package com.ptl.DOC.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;

import com.ptl.util.Constants;

public class ManageCOOPage {

	WebDriver driver;

	String statusFirstPart = Constants.CostStatementDetail_StatusFirstPart;
	String statusLastPart = Constants.CostStatementDetail_StatusLastPart;

	// Date picking fields in Searching for COOs
	@FindBy(xpath = Constants.ManageCOO_DatePicker)
	WebElement datePicker;
	@FindBy(xpath = Constants.ManageCOO_DatePickerMonth)
	WebElement datePickerMonth;
	@FindBy(xpath = Constants.ManageCOO_DatePickerYear)
	WebElement datePickerYear;
	@FindBy(xpath = Constants.ManageCOO_DatePickerPrev)
	WebElement datePickerPrev;

	// search fields in ManageCOO
	@FindBy(xpath = Constants.ManageCOOfta)
	WebElement Fta;
	@FindBy(xpath = Constants.ManageCOOStatus)
	WebElement Status;
	@FindBy(xpath = Constants.ManageCOOExporter)
	WebElement Exporter;
	@FindBy(xpath = Constants.ManageCOOTempRef)
	WebElement TempRefference;
	@FindBy(xpath = Constants.ManageCOOCode)
	WebElement COOCode;
	@FindBy(xpath = Constants.ManageCOOSearchButton)
	WebElement SearchCOOButton;
	@FindBy(xpath = Constants.ManageCOODuration)
	WebElement durationFromCalender;
	@FindBy(xpath = Constants.ManageCOOToDate)
	WebElement durationToCalender;
	@FindBy(xpath = Constants.ManageCOOFirstSearchResult)
	WebElement FirstSearchResult;
	@FindBy(xpath = Constants.FirstResult_EditStatusButton)
	WebElement FirstResult_EditButton;
	@FindBy(xpath = Constants.FirstResult_EditStatusDropdown)
	WebElement FirstResult_StatusDropdown;
	@FindBy(xpath = Constants.FirstResult_SaveStatusButton)
	WebElement FirstResult_SaveButton;
	@FindBy(xpath = Constants.ConfirmStatusChange)
	WebElement ConfirmStatusChange;
	@FindBy(xpath = Constants.COOFirstSearchResultCOONumber)
	WebElement FirstSearchResultCOONumber;
	@FindBy(xpath = Constants.COOFirstSearchResultStatus)
	WebElement FirstSearchResultStatus;
	@FindBy(xpath = Constants.COOFirstSearchResultPrintButton)
	WebElement FirstSearchResultPrintButton;
	@FindBy(xpath = Constants.ExporterCopySelection)
	WebElement ExporterCopySelection;
	@FindBy(xpath = Constants.OfficeCopySelection)
	WebElement OfficeCopySelection;
	@FindBy(xpath = Constants.COONoOfCopies)
	WebElement NoOfCopiesField;
	@FindBy(xpath = Constants.ConfirmPrint)
	WebElement ConfirmPrint;
	@FindBy(xpath = Constants.PrintPageHeaderField)
	WebElement PrintPageHeaderField;
	@FindBy(xpath=Constants.SuccessMessage3)
	WebElement SuccessMessage3;

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

	Set<String> newCOODetailsPage;

	public ManageCOOPage(WebDriver dr) {
		driver = dr;
	}

	public void doSearchforCOO(String FTA, String status, String exporter,
			String tempRef, String cooCode, String ValidFrom, String ValidTo) {

		Fta.sendKeys(FTA);
		Status.sendKeys(status);
		Exporter.sendKeys(exporter);
		TempRefference.sendKeys(tempRef);
		COOCode.sendKeys(cooCode);

		if (ValidFrom != null && !ValidFrom.isEmpty()) {
			durationFromCalender.click();
			try {
				pickExpDate(ValidFrom);
			} catch (InterruptedException e1) {
			}
		}

		if (ValidTo != null && !ValidTo.isEmpty()) {
			durationToCalender.click();
			try {
				pickExpDate(ValidTo);
			} catch (InterruptedException e) {
			}
		}

		SearchCOOButton.click();

	}

	public void pickExpDate(String Date) throws InterruptedException {

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
			else if (monthList.indexOf(calMonth) + 1 > expMonth
					&& (expYear == Integer.parseInt(calYear))
					|| expYear < Integer.parseInt(calYear)) {
				// Click on next button of date picker.
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

	public void verifySearchResults(String FTA, String expectedStatus,
			String expectedExporter, String expectedTempRef,
			String expectedCooCode) {

		// search results to verify search success/failure
		List<WebElement> SearchResultCOOList = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOCodes));
		List<WebElement> SearchResultExporterList = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofExporterNames));
		List<WebElement> SearchResultStatusList = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofStatuses));
		List<WebElement> SearchResultTempRefList = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOTempRefs));

		if (expectedCooCode != null && !expectedCooCode.isEmpty())
			for (int i = 0; i < SearchResultCOOList.size(); i++) {
				String COOCode = SearchResultCOOList.get(i).getText();

				Assert.assertTrue(expectedCooCode.equalsIgnoreCase(COOCode),
						"Search by COO Code has failed.");
			}

		if (expectedStatus != null && !expectedStatus.isEmpty()
				&& !expectedStatus.equalsIgnoreCase("ALL"))
			for (int i = 0; i < SearchResultStatusList.size(); i++) {
				String Status = SearchResultStatusList.get(i).getText();

				Assert.assertTrue(expectedStatus.equalsIgnoreCase(Status),
						"Search by Status has failed.");
			}

		if (expectedExporter != null && !expectedExporter.isEmpty())
			for (int i = 0; i < SearchResultExporterList.size(); i++) {
				String Exporter = SearchResultExporterList.get(i).getText();

				Assert.assertTrue(expectedExporter.equalsIgnoreCase(Exporter),
						"Search by Exporter has failed.");
			}

		if (expectedTempRef != null && !expectedTempRef.isEmpty())
			for (int i = 0; i < SearchResultTempRefList.size(); i++) {
				String TempRef = SearchResultTempRefList.get(i).getText();

				Assert.assertTrue(expectedTempRef.equalsIgnoreCase(TempRef),
						"Search by TempRef has failed.");
			}
	}

	public void verifySearchReultsDate(String expectedValidFrom,
			String expectedValidTo) throws ParseException {

		if (expectedValidFrom != null && expectedValidTo != null) {

			SimpleDateFormat SearchDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");
			SimpleDateFormat ResultDateFormat = new SimpleDateFormat(
					"yyyy/MM/dd");

			Date fromDate = SearchDateFormat.parse(expectedValidFrom);
			Date toDate = SearchDateFormat.parse(expectedValidTo);

			List<WebElement> SearchResultDates = driver.findElements(By
					.xpath(Constants.ManageCOOSearchResult_ListofDates));

			for (int i = 0; i < SearchResultDates.size(); i++) {

				String actualDatefield = SearchResultDates.get(i).getText();

				Date actualDate = ResultDateFormat.parse(actualDatefield);

				if (!(actualDate.equals(toDate) || actualDate.equals(fromDate)))
					Assert.assertTrue(
							actualDate.before(toDate)
									&& actualDate.after(fromDate),
							"Search by Date has failed.");
			}
		}
	}

	public ViewCOOPage goToFirstCOOLink() {

		FirstSearchResult.click();

		newCOODetailsPage = driver.getWindowHandles();

		ViewCOOPage CooPage = PageFactory.initElements(driver,
				ViewCOOPage.class);
		return CooPage;

	}

	public void switchToViewCOOPage() {

		for (String winHandle : newCOODetailsPage)
			driver.switchTo().window(winHandle);

	}

	public void NoOfSearchResults() {

		List<WebElement> TempRefList = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOTempRefs));

		Assert.assertTrue(TempRefList.size() > 0, "No COO founds under search.");

		System.out.println(TempRefList.size()
				+ " results displayed from search");

	}

	public String changeStatus(String status) {

		String firstResultTempRefNo = FirstSearchResult.getText();

		FirstResult_EditButton.click();
		FirstResult_StatusDropdown.sendKeys(status);
		FirstResult_SaveButton.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		ConfirmStatusChange.click();

		Alert javaScriptAlert = driver.switchTo().alert();
		System.out.println(javaScriptAlert.getText());
		javaScriptAlert.accept();

		return firstResultTempRefNo;

	}

	public void verifyCOOCodeAvailable() {

		List<WebElement> COONumbers = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOCodes));

		List<WebElement> COOStatuses = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofStatuses));

		List<WebElement> COORefNos = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOTempRefs));

		for (int i = 0; i < COONumbers.size(); i++) {

			String COONumber = COONumbers.get(i).getText();

			Assert.assertTrue(!COONumber.isEmpty(), COOStatuses.get(i)
					.getText()
					+ " COO (Ref No):"
					+ COORefNos.get(i).getText()
					+ " has no COO Number");
		}
	}

	public void verifyCOOCodeUnAvailable() {

		List<WebElement> COONumbers = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOCodes));

		List<WebElement> COOStatuses = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofStatuses));

		List<WebElement> COORefNos = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOTempRefs));

		for (int i = 0; i < COONumbers.size(); i++) {

			String COONumber = COONumbers.get(i).getText();

			Assert.assertTrue(COONumber.isEmpty(), COOStatuses.get(i).getText()
					+ " COO (Ref No):" + COORefNos.get(i).getText()
					+ " has COO Number" + COONumber);
		}

	}

	public void verifyPrintEnabled() {

		List<WebElement> PrintButtons = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofPrintButtons));

		List<WebElement> COOStatuses = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofStatuses));

		List<WebElement> COORefNos = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOTempRefs));

		for (int i = 0; i < PrintButtons.size(); i++) {

			String PrintButton = PrintButtons.get(i).getAttribute("onclick");

			Assert.assertTrue(PrintButton != null,
					"Unabled to print COO (Ref No):"
							+ COORefNos.get(i).getText() + " with status of "
							+ COOStatuses.get(i).getText());
		}
	}

	public void verifyPrintDisabled() {

		List<WebElement> PrintButtons = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofPrintButtons));

		List<WebElement> COOStatuses = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofStatuses));

		List<WebElement> COORefNos = driver.findElements(By
				.xpath(Constants.ManageCOOSearchResult_ListofCOOTempRefs));

		for (int i = 0; i < PrintButtons.size(); i++) {

			String PrintButton = PrintButtons.get(i).getAttribute("onclick");

			Assert.assertTrue(PrintButton == null,
					"Print enabled for COO (Ref No):"
							+ COORefNos.get(i).getText() + " with status of "
							+ COOStatuses.get(i).getText());
		}
	}
	public String getErrorMessage2(){
		Assert.assertTrue(SuccessMessage3.getText().equals(Constants.SuccessMessage4),
				"error in changing status");
						
		return SuccessMessage3.getText();	
		
	}

	public void printCOO(String type, String NoOfCopies) {

		FirstSearchResultPrintButton.click();

		if (type.equals("Office"))
			OfficeCopySelection.click();
		if (type.equals("Exporter"))
			ExporterCopySelection.click();

		if (!NoOfCopies.isEmpty())
			NoOfCopiesField.sendKeys(NoOfCopies);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		// confirms print Action
		ConfirmPrint.click();

		Alert javaScriptAlert = driver.switchTo().alert();
		System.out.println(javaScriptAlert.getText());
		javaScriptAlert.accept();

		// switches to Print window
		newCOODetailsPage = driver.getWindowHandles();

		for (String winHandle : newCOODetailsPage)
			driver.switchTo().window(winHandle);

		// validates if made to Print Window		
		String PageHeader = PrintPageHeaderField.getText();
		Assert.assertTrue(
				PageHeader.equals(Constants.CorrectPrintPageHeader),
				"Could not navigate to Print page of the COO");
	}

	
}
