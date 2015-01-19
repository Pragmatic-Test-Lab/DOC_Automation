package com.ptl.DOC.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ptl.util.Constants;

public class CitizenTopMenu {
	WebDriver driver;

	@FindBy(xpath = Constants.HomePageLink)
	WebElement homePageTopLink;

	public CitizenTopMenu(WebDriver dr) {
		driver = dr;
	}

	public CitizenHomePage gotoCitizenHomePage() {
		homePageTopLink.click();
		CitizenHomePage homePage = PageFactory.initElements(driver, CitizenHomePage.class);
		return homePage;
	}

}
