package com.ptl.DOC.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ptl.DOC.pages.ApplyCOOPage;
import com.ptl.DOC.pages.RequestCopies;
import com.ptl.util.Constants;

public class CitizenHomePage {
	
	WebDriver driver;
	
	@FindBy(xpath=Constants.ApplyCOOLink)
	WebElement COOApplicationLink;
	@FindBy(xpath=Constants.ErrorMessage1)
	WebElement ErrorMessage1;
	@FindBy(xpath=Constants.RequestCopieslink)
	WebElement RequestcopyLink;
	@FindBy(xpath=Constants.REApplyCOOLink)
	WebElement COOREApplicationLink;
	@FindBy(xpath=Constants.LogOutLink)
	WebElement LogoutLink;

	
	
	public CitizenHomePage(WebDriver dr){
		driver = dr;		
	}
	
	public ApplyCOOPage gotoApplyCOOPage(){
		COOApplicationLink.click();
		ApplyCOOPage applyCOOPage = PageFactory.initElements(driver, ApplyCOOPage.class);
		return applyCOOPage;
	}
	public ApplyCOOPage gotoREApplyCOOPage(){
		COOREApplicationLink.click();
		ApplyCOOPage applyCOOPage = PageFactory.initElements(driver, ApplyCOOPage.class);
		return applyCOOPage;
	}
	
	public CitizenLoginPage gotoLogout(){
		LogoutLink.click();
		CitizenLoginPage LoginPage = PageFactory.initElements(driver, CitizenLoginPage.class);
		return LoginPage;
	}
	
	public RequestCopies gotoRequestcopyPage(){
		RequestcopyLink.click();
		RequestCopies ReqcopiesPage = PageFactory.initElements(driver, RequestCopies.class);
		return ReqcopiesPage;
	}
	public void getErrorMessage(){
		System.out.println(ErrorMessage1.getText());

	}
}
















