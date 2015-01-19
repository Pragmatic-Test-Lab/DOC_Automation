package com.ptl.DOC.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ptl.util.Constants;

public class TopMenu {
	
	WebDriver driver;
	
	@FindBy(xpath=Constants.HomePageLink)
	WebElement homePageTopLink;
	@FindBy(xpath=Constants.ForgotPasswordLink1)
	WebElement forgotpassword;
	
	public TopMenu(WebDriver dr){
		driver = dr;
	}
	
	public HomePage gotoHomePage(){
		homePageTopLink.click();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		return homePage;
	}

	public CitizenHomePage gotoCitizenHomePage(){
		homePageTopLink.click();
		CitizenHomePage homePage = PageFactory.initElements(driver, CitizenHomePage.class);
		return homePage;
	}
	

}
