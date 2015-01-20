package com.ptl.DOC.pages;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ptl.util.Constants;
import com.thoughtworks.selenium.webdriven.commands.Click;

public class ApplyCOOPage {

	WebDriver driver;

	@FindBy(xpath = Constants.tempRefNumber)
	WebElement TempRefNumber;
	@FindBy(xpath = Constants.tempRefNumberListFirst)
	WebElement FirstResult;
	@FindBy(xpath = Constants.ftaDropdown)
	WebElement FTA;
	@FindBy(xpath = Constants.exporterAddress)
	WebElement ExporterAddress;
	@FindBy(xpath = Constants.transportationMode)
	WebElement ModeofTransport;
	@FindBy(xpath = Constants.VeselNo)
	WebElement VesselNo;
	@FindBy(xpath = Constants.PortofLoading)
	WebElement PortOfLoading;
	@FindBy(xpath = Constants.PortofDischarging)
	WebElement PortOfDischarging;
	@FindBy(xpath = Constants.CosigneeName)
	WebElement cosigneeeName;
	@FindBy(xpath = Constants.CosigneeAddress)
	WebElement cosigneeeAddress;
	@FindBy(xpath = Constants.CosigneeCountry)
	WebElement cosigneeeCountry;
	@FindBy(xpath = Constants.Declaration)
	WebElement Declaration;
	@FindBy(xpath = Constants.CUSDECNo)
	WebElement CUSDEC;
	@FindBy(xpath = Constants.OfficeCode)
	WebElement OfficeCode;
	@FindBy(xpath = Constants.VoyageNo)
	WebElement VoyageCode;
	@FindBy(xpath = Constants.DepartureDate)
	WebElement DepartureDateField;

	@FindBy(xpath = Constants.HSCode)
	WebElement HSCode;
	@FindBy(xpath = Constants.PackageMark)
	WebElement ProductMark;
	@FindBy(xpath = Constants.PackageAmount)
	WebElement ProductNo;
	@FindBy(xpath = Constants.OriginCriterion)
	WebElement Criterion;
	@FindBy(xpath = Constants.GrossWeight)
	WebElement Weight;
	@FindBy(xpath = Constants.Unit)
	WebElement Unit;
	@FindBy(xpath = Constants.NetWeight)
	WebElement NetWeight;
	@FindBy(xpath = Constants.InvoiceNumber)
	WebElement InvoiceNo;
	@FindBy(xpath = Constants.FOBValue)
	WebElement FOB;
	@FindBy(xpath = Constants.InvoiceDate)
	WebElement InvoiceDateField;
	@FindBy(xpath = Constants.AddItemButton)
	WebElement AddProduct;

	@FindBy(xpath = Constants.DatePicker)
	WebElement datePicker;
	@FindBy(xpath = Constants.DatePickerMonth)
	WebElement datePickerMonth;
	@FindBy(xpath = Constants.DatePickerYear)
	WebElement datePickerYear;
	@FindBy(xpath = Constants.DatePickerNext)
	WebElement datePickerNext;

	@FindBy(xpath = Constants.DeclaredCountry)
	WebElement DeclaredCountry;
	@FindBy(xpath = Constants.NoOfCopies)
	WebElement NoOfCopies;
	@FindBy(xpath = Constants.CollectionMethod)
	WebElement CollectionMethod;
	@FindBy(xpath = Constants.PostalAddress)
	WebElement PostalAddress;

	@FindBy(xpath = Constants.SubmitFormButton)
	WebElement Submit;
	@FindBy(xpath = Constants.SaveFormButton)
	WebElement Save;
	@FindBy(xpath = Constants.PageHeading)
	WebElement PageHeading;
	@FindBy(xpath = Constants.SaveMessage)
	WebElement SaveMessage;
	@FindBy(xpath = Constants.ResubmitMessage)
	WebElement ResubmitMessage;
	@FindBy(xpath = Constants.Status1)
	WebElement status1;
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

	public ApplyCOOPage(WebDriver dr) {
		driver = dr;
	}

	public void ApplyNewCOO(String fta, String exporterAddress,
			String modeOfTransport, String vesselNo, String portOfLoading,
			String portOfDischarging, String CosigneeeName,
			String CosigneeAddress, String CosigneeCountry, String declaration,
			String CUSDECCode, String officeCode, String voyageCode,
			String departureDate, String declaredCountry, String noOfCopies,
			String collectionMethod, String postalAddress) {

		FTA.sendKeys(fta);
		ExporterAddress.sendKeys(exporterAddress);
		ModeofTransport.sendKeys(modeOfTransport);
		VesselNo.sendKeys(vesselNo);
		PortOfLoading.sendKeys(portOfLoading);
		PortOfDischarging.sendKeys(portOfDischarging);

		cosigneeeName.sendKeys(CosigneeeName);
		cosigneeeAddress.sendKeys(CosigneeAddress);
		cosigneeeCountry.sendKeys(CosigneeCountry);

		Declaration.sendKeys(declaration);
		CUSDEC.sendKeys(CUSDECCode);
		OfficeCode.sendKeys(officeCode);
		VoyageCode.sendKeys(voyageCode);

		DepartureDateField.click();
		try {
			pickDate(departureDate);
		} catch (InterruptedException e) {
		}

		DeclaredCountry.sendKeys(declaredCountry);
		NoOfCopies.sendKeys(noOfCopies);
		CollectionMethod.sendKeys(collectionMethod);

		if (!collectionMethod.equalsIgnoreCase("Post"))
			PostalAddress.sendKeys(postalAddress);

	}

	public ApplyCOOPaymentPage SubmitCOOApplication() {

		Submit.click();

		Alert javaScriptAlert = driver.switchTo().alert();
		System.out.println(javaScriptAlert.getText());
		javaScriptAlert.accept();

		Assert.assertTrue(
				PageHeading.getText().equals(Constants.COOSubmitionMessage),
				"COO Submition Failed.");
		ApplyCOOPaymentPage paymentPage = PageFactory.initElements(driver,
				ApplyCOOPaymentPage.class);
		return paymentPage;

	}

	public String SaveCOOApplication() {

		Save.click();
		String saveMessage = SaveMessage.getText();

		Assert.assertTrue(!saveMessage.isEmpty(), "COO Save Failed.");
		String TempRefNumber = saveMessage.substring(
				saveMessage.indexOf("T/ISFTA"), saveMessage.indexOf("."));

		return TempRefNumber;
	}

	public void AddNewProduct(String hsCode, String productMark,
			String productNo, String criterion, String weight, String unit,
			String netWeight, String invoiceNo, String fob, String invoiceDate) {

		String[] hsCodes = hsCode.split(",");
		String[] productMarks = productMark.split(",");
		String[] productNos = productNo.split(",");
		String[] criterions = criterion.split(",");
		String[] weights = weight.split(",");
		String[] units = unit.split(",");
		String[] netWeights = netWeight.split(",");
		String[] invoiceNos = invoiceNo.split(",");
		String[] fobs = fob.split(",");
		String[] invoiceDates = invoiceDate.split(",");

		for (int i = 0; i < hsCodes.length; i++) {

			HSCode.sendKeys(hsCodes[i]);
			ProductMark.sendKeys(productMarks[i]);
			ProductNo.sendKeys(productNos[i]);
			Criterion.sendKeys(criterions[i]);
			Weight.sendKeys(weights[i]);
			Unit.sendKeys(units[i] + Keys.TAB);

			if (!(units[i].equalsIgnoreCase("Tonns") || units[i]
					.equalsIgnoreCase("Item Units")))
				NetWeight.sendKeys(netWeights[i]);

			InvoiceNo.sendKeys(invoiceNos[i]);
			FOB.sendKeys(fobs[i]);

			InvoiceDateField.click();
			try {
				pickDate(invoiceDates[i]);
			} catch (InterruptedException e) {
			}

			AddProduct.click();

		}
	}

	public void retrieveSavedApplication(String tempRefNum) {

		TempRefNumber.sendKeys(tempRefNum);
		FirstResult.click();

	}

	public void ResubmitCOOApplication() {
		Submit.click();

		Assert.assertTrue(
				ResubmitMessage.getText().equals(
						Constants.ResubmitSuccessfullMessage),
				"COO Resubmition Failed.");

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
			else if (monthList.indexOf(calMonth) + 1 < expMonth
					&& (expYear == Integer.parseInt(calYear))
					|| expYear > Integer.parseInt(calYear)) {
				// Click on next button of date picker.
				datePickerNext.click();
			}

		}
		Thread.sleep(3000);
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

	public void doReapplyCOO(String Status) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		status1.click();

	}

	public void AddNewProduct(String hsCode, String productMark,
			String productNo, String criterion, String weight, String unit,
			String invoiceNo, String fob, String invoiceDate) {

	}

	public void ChangeCOOApplicationData(String fta, String exporterAddress,
			String modeOfTransport, String vesselNo, String portOfLoading,
			String portOfDischarging, String CosigneeeName,
			String CosigneeAddress, String CosigneeCountry, String declaration,
			String CUSDECCode, String officeCode, String voyageCode,
			String departureDate, String declaredCountry, String noOfCopies,
			String collectionMethod, String postalAddress) {

		FTA.sendKeys(fta);
		ExporterAddress
				.sendKeys(Keys.chord(Keys.CONTROL, "a"), exporterAddress);
		ModeofTransport.sendKeys(modeOfTransport);
		VesselNo.sendKeys(Keys.chord(Keys.CONTROL, "a"), vesselNo);
		PortOfLoading.sendKeys(portOfLoading);
		PortOfDischarging.sendKeys(Keys.chord(Keys.CONTROL, "a"),
				portOfDischarging);
		cosigneeeName.sendKeys(Keys.chord(Keys.CONTROL, "a"), CosigneeeName);
		cosigneeeAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"),
				CosigneeAddress);
		cosigneeeCountry.sendKeys(CosigneeCountry);
		Declaration.sendKeys(Keys.chord(Keys.CONTROL, "a"), declaration);
		CUSDEC.sendKeys(Keys.chord(Keys.CONTROL, "a"), CUSDECCode);
		OfficeCode.sendKeys(Keys.chord(Keys.CONTROL, "a"), officeCode);
		VoyageCode.sendKeys(Keys.chord(Keys.CONTROL, "a"), voyageCode);

		DepartureDateField.click();
		try {
			pickDate(departureDate);
		} catch (InterruptedException e) {
		}

		DeclaredCountry.sendKeys(declaredCountry);
		NoOfCopies.sendKeys(Keys.chord(Keys.CONTROL, "a"), noOfCopies);
		CollectionMethod.sendKeys(collectionMethod);

		if (!collectionMethod.equalsIgnoreCase("Post"))
			PostalAddress
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), postalAddress);
	}

	public void CompareCOOApplicationData(String fta, String exporterAddress,
			String modeOfTransport, String vesselNo, String portOfLoading,
			String portOfDischarging, String CosigneeeName,
			String CosigneeAddress, String CosigneeCountry, String declaration,
			String CUSDECCode, String officeCode, String voyageCode,
			String departureDate, String declaredCountry, String noOfCopies,
			String collectionMethod, String postalAddress) {
		
		String selectedFTA = (new Select(FTA)).getFirstSelectedOption().getText();
		Assert.assertTrue(selectedFTA.equals(fta),
				"Retrived COO Application FTA is different");

		Assert.assertTrue(ExporterAddress.getAttribute("value").equals(exporterAddress),
				"Retrived COO Application Exporter Address is different");
		
		String selectedMOT = new Select(ModeofTransport).getFirstSelectedOption().getText();
		Assert.assertTrue(selectedMOT.equals(modeOfTransport),
				"Retrived COO Application Mode of Transport is different");

		Assert.assertTrue(VesselNo.getAttribute("value").equals(vesselNo),
				"Retrived COO Application Vessel No is different");

		String selectedPOL = new Select(PortOfLoading).getFirstSelectedOption().getText();
		Assert.assertTrue(selectedPOL.equals(portOfLoading),
				"Retrived COO Application Port Of Loading is different");

		Assert.assertTrue(
				PortOfDischarging.getAttribute("value").equals(portOfDischarging),
				"Retrived COO Application Port Of Discharging is different");

		Assert.assertTrue(cosigneeeName.getAttribute("value").equals(CosigneeeName),
				"Retrived COO Application Cosigneee Name is different");

		Assert.assertTrue(cosigneeeAddress.getAttribute("value").equals(CosigneeAddress),
				"Retrived COO Application Cosigneee Address is different");

		String cosigneeCountry = new Select(cosigneeeCountry).getFirstSelectedOption().getText();
		Assert.assertTrue(cosigneeCountry.equals(CosigneeCountry),
				"Retrived COO Application Cosigneee Country is different");

		Assert.assertTrue(Declaration.getAttribute("value").equals(declaration),
				"Retrived COO Application Declaration is different");

		Assert.assertTrue(CUSDEC.getAttribute("value").equals(CUSDECCode),
				"Retrived COO Application CUSDEC Code is different");

		Assert.assertTrue(OfficeCode.getAttribute("value").equals(officeCode),
				"Retrived COO Application OfficeCode is different");

		Assert.assertTrue(VoyageCode.getAttribute("value").equals(voyageCode),
				"Retrived COO Application Voyage Code is different");

		String DecCountry = new Select(DeclaredCountry).getFirstSelectedOption().getText();
		Assert.assertTrue(DecCountry.equals(declaredCountry),
				"Retrived COO Application Declared Country is different");

		Assert.assertTrue(NoOfCopies.getAttribute("value").equals(noOfCopies),
				"Retrived COO Application No Of Copies is different");

		String ColMethod = new Select(CollectionMethod).getFirstSelectedOption().getText();
		Assert.assertTrue(ColMethod.equals(collectionMethod),
				"Retrived COO Application Collection Method is different");

	}

}
