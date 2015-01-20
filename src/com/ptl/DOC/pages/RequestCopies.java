package com.ptl.DOC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ptl.util.Constants;

public class RequestCopies {
	WebDriver driver;

	@FindBy(xpath=Constants.ExpRegNo1)
	WebElement expRegNo1;
	@FindBy(xpath=Constants.RecNo1)
	WebElement recNo1;	
	@FindBy(xpath=Constants.SaveButton)
	WebElement savebut;	
	@FindBy(xpath=Constants.GetFTA)
	WebElement FTA;	
	@FindBy(xpath=Constants.GetProduct)
	WebElement product;	
	@FindBy(xpath=Constants.GetCountry)
	WebElement country;	
	@FindBy(xpath=Constants.GetExpReg2)
	WebElement ExporterRegNo;	
	@FindBy(xpath=Constants.GetCOORef)
	WebElement COOReferenceNo;	
	@FindBy(xpath=Constants.GetCopies)
	WebElement NoOfCopies;	
	@FindBy(xpath=Constants.GetCollection)
	WebElement CollectionMethod;	
	@FindBy(xpath=Constants.GetPostal)
	WebElement PostalAddress;	
	@FindBy(xpath=Constants.GetReceipt)
	WebElement ReceiptNo;	
	@FindBy(xpath=Constants.Reset)
	WebElement resetbutton;
	@FindBy(xpath=Constants.Fetch)
	WebElement fetch;
	@FindBy(xpath=Constants.CompanyName1)
	WebElement name;
	@FindBy(xpath=Constants.save2)
	WebElement save;
	@FindBy(xpath=Constants.confirm)
	WebElement confirm;
	
	
	public RequestCopies(WebDriver dr){
		driver = dr;
	}

	public void ApplyNewCOO(){


		//click departure and select code portion

	}


	public void AddNewProduct(){



	}
	public void getExpRegNoAttribute(){
		//String readOnly = expRegNo.getAttribute("readonly");
		//System.out.println("Read Only Text "+readOnly);
		if(expRegNo1.getAttribute("readonly").equals("true"))
			System.out.println("Exporter Registration No Uneditable");
		else System.out.println("Exporter Registration No editable");

	} 
	public void getReceiptNoAttribute(){
		if(recNo1.getAttribute("readonly").equals("true")){
			System.out.println("Receipt No Uneditable");	}
		else {System.out.println("Exporter Registration No editable");
		}
	}
	public void getsavebutton(){
		if(savebut.getAttribute("disabled").equals("true"))
			System.out.println("Save Button disabled");	

	}
	public void getFTA(){
		if(FTA.getAttribute("readonly").equals("true"))
			System.out.println("FTA Uneditable");	

	}
	public void getProduct(){
		if(product.getAttribute("readonly").equals("true"))
			System.out.println("product Uneditable");	

	}
	public void getCountry(){
		if(country.getAttribute("readonly").equals("true"))
			System.out.println("Consignee Country Uneditable");	

	}
	
	public void getNoofCopies(){
		String numbercopies= NoOfCopies.getAttribute("value");
		System.out.println("No of copies selected:  "+numbercopies);
		
	}
	
	public String getName(){
		String Name=name.getAttribute("value");
		
			System.out.println("Name of company: "+Name);
			return Name;	

	}
	
	public void saveit(){
		save.click();
	}
	public void getconfirm(){
		confirm.click();
	}
	
	public void clickCOORefField(){
		COOReferenceNo.click();
	}
	public String doresetcheck(){
		System.out.println("no copies:"+NoOfCopies.getAttribute("value"));
		System.out.println("collect:"+ CollectionMethod.getAttribute("value"));
		System.out.println("cooref:"+COOReferenceNo.getText());
		CollectionMethod.getText();
		
		int foo=  Integer.parseInt(NoOfCopies.getAttribute("value"));
		int foo1=  Integer.parseInt(CollectionMethod.getAttribute("value"));
		Assert.assertTrue(COOReferenceNo.getText().equals("") && foo==0 && foo1==0,
				"not reset");
		
return COOReferenceNo.getText();	
		
	}
	
	public LoginCredentialsPage doRequestCopies(String ExpRegNo, String COORefNo, String NoofCopies, 
			String CollectMethod, String Postal, String RecNo){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		ExporterRegNo.sendKeys(ExpRegNo);
		fetch.click();
		COOReferenceNo.sendKeys(COORefNo);
		COOReferenceNo.click();
		NoOfCopies.sendKeys(NoofCopies);
		//tel.click();
		CollectionMethod.sendKeys(CollectMethod);
		//fax.click();
		PostalAddress.sendKeys(Postal);
		ReceiptNo.sendKeys(RecNo);
		

		

		//String actualMsgText = actualSuccessMessage.getText();
	//	String expectedMsgText = expectedSuccessMsgText;
	//	System.out.println(ErrorMessage.getText());
	//	Assert.assertTrue(actualMsgText.equalsIgnoreCase(expectedMsgText), "Exporter Registration Fail");

		LoginCredentialsPage loginCredPage = PageFactory.initElements(driver, LoginCredentialsPage.class);
		return loginCredPage;

	}
	public LoginCredentialsPage doReset(String ExpRegNo, String COORefNo, String NoofCopies, 
			String CollectMethod, String Postal, String RecNo){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		ExporterRegNo.sendKeys(ExpRegNo);
		fetch.click();
		
		COOReferenceNo.sendKeys(COORefNo);
		NoOfCopies.sendKeys(NoofCopies);
		//tel.click();
		CollectionMethod.sendKeys(CollectMethod);
		//fax.click();
		try {
			Thread.sleep(2500);
			PostalAddress.sendKeys(Postal);
		} catch (Exception e) {
			e.getMessage();
		}
		
		ReceiptNo.sendKeys(RecNo);
		
		//String PostalAddBefore = PostalAddress.getText();
		String PostalAddBeforeText = PostalAddress.getAttribute("value");
		String COORefBefore = COOReferenceNo.getAttribute("value");
		
		System.out.println("Postal Before" +PostalAddBeforeText);
		System.out.println("COORef Before"+ COORefBefore);
		resetbutton.click();			
		
		PostalAddBeforeText = PostalAddress.getAttribute("value");
		 COORefBefore =COOReferenceNo.getAttribute("value");
		System.out.println("Postal After"+PostalAddBeforeText);
		
		System.out.println("COOref After"+ COORefBefore);
		//Assert.assertTrue(PostalAdd, message);

		

		//String actualMsgText = actualSuccessMessage.getText();
	//	String expectedMsgText = expectedSuccessMsgText;
	//	System.out.println(ErrorMessage.getText());
	//	Assert.assertTrue(actualMsgText.equalsIgnoreCase(expectedMsgText), "Exporter Registration Fail");

		LoginCredentialsPage loginCredPage = PageFactory.initElements(driver, LoginCredentialsPage.class);
		return loginCredPage;

	}

}
