package com.ptl.DOC.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ptl.util.Constants;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath=Constants.ExporterRegistration)
	WebElement exporterRegLink;
	@FindBy(xpath=Constants.ManageExporters)
	WebElement manageExportersLink;
	@FindBy(xpath=Constants.RecordCostAffidavit)
	WebElement recordAffidavitLink;
	@FindBy(xpath=Constants.ManageCOOLink)
	WebElement manageCOOLink;
	@FindBy(xpath=Constants.StatReportLink)
	WebElement statReportLink;
	@FindBy(xpath=Constants.RecordCostStatementLink)
	WebElement recordCostStatementAffidavitsLink;
	@FindBy(xpath=Constants.RegisterExpName)
	WebElement RegExpName;
	public HomePage(WebDriver dr){
		driver = dr;
		
	}
	
	public ExporterRegPage gotoExporterRegPage(){
		exporterRegLink.click();
		ExporterRegPage expRegPage = PageFactory.initElements(driver, ExporterRegPage.class);
		return expRegPage;
	}
	
	public ExporterRegPage gotoExporterRegPage2(){
		exporterRegLink.click();
		//PayButton.click();
		//System.out.println(RegExpName.getText());
		String heading=RegExpName.getText();
		System.out.println(Constants.RegisterExpheading);
		Assert.assertTrue(heading.equals(Constants.RegisterExpheading),
				"Unsuccesful at proceeding to Register Exporter page");	
		ExporterRegPage expRegPage = PageFactory.initElements(driver, ExporterRegPage.class);
		return expRegPage;


		
	}
	
	public ManageExportersPage gotoManageExportersPage(){
		manageExportersLink.click();
		ManageExportersPage manageExpPage = PageFactory.initElements(driver, ManageExportersPage.class);
		return manageExpPage;
	}
	
	public CostStatementAffidavitDetailsPage gotoCostStatementAffidavitDetailsPage(){
		recordAffidavitLink.click();
		CostStatementAffidavitDetailsPage costStatementAffidavitPage = PageFactory.initElements(driver, CostStatementAffidavitDetailsPage.class);
		return costStatementAffidavitPage;
	}
	
	public ManageCOOPage gotoManageCOOPage(){
		manageCOOLink.click();
		ManageCOOPage manageCOOPage = PageFactory.initElements(driver, ManageCOOPage.class);
		return manageCOOPage;
	}
	
	public StatisticReportPage gotoStatisticReportPage(){
		statReportLink.click();
		StatisticReportPage statReportPage = PageFactory.initElements(driver, StatisticReportPage.class);
		return statReportPage;
	}
	
}
