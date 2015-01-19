package com.ptl.DOC.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ptl.util.Constants;

public class ManageExportersPage {

	WebDriver driver;

	@FindBy(xpath = Constants.ExporterRegNoName)
	WebElement exporterRegNoName;
	@FindBy(xpath = Constants.FirstInlineSearchResult)
	WebElement firstInlineSearchResult;
	@FindBy(xpath = Constants.Status)
	WebElement status;
	@FindBy(xpath = Constants.SearchButton)
	WebElement search;
	@FindBy(xpath = Constants.FirstRegistrationNumberLink)
	WebElement firstRegNumberLink;
	@FindBy(xpath = Constants.FirstSuspendButton)
	WebElement firstSuspendButton;
	@FindBy(xpath = Constants.SuspendSuccessMessage)
	WebElement suspendSuccessMessage;
	@FindBy(xpath = Constants.FirstActivateButton)
	WebElement firstActivateButton;
	@FindBy(xpath = Constants.ActivateSuccessMessage)
	WebElement activateSuccessMessage;

	@FindBy(xpath = Constants.FirstSearchResultBussName)
	WebElement firstBussinessName;
			
	String suspendSuccessMessageText = Constants.SuspendSuccessMessageText;
	String activateSuccessMessageText = Constants.ActivateSuccessMessageText;

	public ManageExportersPage(WebDriver dr) {
		driver = dr;
	}

	public void SearchExporter(String ExpNameNumber, String Status) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		exporterRegNoName.sendKeys(ExpNameNumber);
		firstInlineSearchResult.click();
		status.sendKeys(Status);
		search.click();
	}

	public ModifyExporterPage gotoModifyExporterPage() {
		firstRegNumberLink.click();
		ModifyExporterPage modifyExpPage = PageFactory.initElements(driver,
				ModifyExporterPage.class);
		return modifyExpPage;
	}

	public void VerifySearchbyName(String expectedBussName) {

		String firstSearchName = firstBussinessName.getText();

		Assert.assertTrue(expectedBussName.equalsIgnoreCase(firstSearchName),
				"Search by Bussiness name failed.");
	}

	public void VerifySearchbyStatus(String expectedStatus) {
		
		
		List<WebElement> allStatuses = driver.findElements(By.xpath(Constants.SearchResultStatus));
		
		for (int i = 0; i < allStatuses.size(); i++) {

			String status = allStatuses.get(i).getText();
			System.out.println(status);
			Assert.assertTrue(expectedStatus.equalsIgnoreCase(status),
					"Search by Status has failed.");
		}

	}

	public void doSuspendExporter() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		firstSuspendButton.click();

		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();

		String ActualSuccessMsg = suspendSuccessMessage.getText();
		String ExpectedSuccessMsg = suspendSuccessMessageText;

		Assert.assertTrue(
				ExpectedSuccessMsg.equalsIgnoreCase(ActualSuccessMsg),
				"Exporter Suspend Action Failed");
		System.out.println("Exporter suspended.");
	}

	public void getSuspendStatusMessage() {
		System.out.println(suspendSuccessMessage.getText());

	}

	public void doActivateExporter() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		firstActivateButton.click();
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();

		String ActualSuccessMsg = activateSuccessMessage.getText();
		String ExpectedSuccessMsg = activateSuccessMessageText;

		Assert.assertTrue(
				ExpectedSuccessMsg.equalsIgnoreCase(ActualSuccessMsg),
				"Exporter Activate Action Failed");
	}

	public void getActivateStatusMessage() {
		System.out.println(activateSuccessMessage.getText());

	}

}
