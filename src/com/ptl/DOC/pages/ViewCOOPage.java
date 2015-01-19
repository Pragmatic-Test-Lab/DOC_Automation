package com.ptl.DOC.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.ptl.util.Constants;

public class ViewCOOPage {
	
	WebDriver driver;
	
	@FindBy(xpath = Constants.COOfta)
	WebElement Fta;
	@FindBy(xpath = Constants.COOstatus)
	WebElement Type;
	@FindBy(xpath = Constants.COOexporterName)
	WebElement Exporter;
	@FindBy(xpath = Constants.COOTempRefNo)
	WebElement TempRef;
	@FindBy(xpath = Constants.COORefNo)
	WebElement CooCode;
	
	public ViewCOOPage(WebDriver dr) {
		driver = dr;
	}

	public void verifyCOODetails(String FTA, String type, String exporter,
			String tempRef, String cooCode) {
		
		if(FTA != null && !FTA.isEmpty())
			Assert.assertTrue(FTA.equalsIgnoreCase(Fta.getText()),
					"COO fta details missmatch.");
			
		if(type != null && !type.isEmpty() && !type.equalsIgnoreCase("ALL"))
			Assert.assertTrue(type.equalsIgnoreCase(Type.getText()),
					"COO status details missmatch.");
		
		if(exporter != null && !exporter.isEmpty())
			Assert.assertTrue(exporter.equalsIgnoreCase(Exporter.getText()),
					"COO exporter name details missmatch.");
		
		if(tempRef != null && !tempRef.isEmpty())
			Assert.assertTrue(tempRef.equalsIgnoreCase(TempRef.getText()),
					"COO temp Refference missmatched.");
		
		if(cooCode != null && !cooCode.isEmpty())
			Assert.assertTrue(cooCode.equalsIgnoreCase(CooCode.getText()),
					"COO Code missmatched.");
		
	}

}
