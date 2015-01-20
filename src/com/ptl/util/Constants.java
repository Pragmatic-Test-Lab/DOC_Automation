package com.ptl.util;

public class Constants {

	// paths
	public static String CONFIG_FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\com\\ptl\\config\\config.properties";
	public static String EXCEL_Path = System.getProperty("user.dir")
			+ "\\src\\com\\ptl\\data\\TestData Excel.xlsx";

	// Page Title
	public static final String landingPageTitle = "Net Suite";

	// Login Page
	public static final String username = "//*[@id='userName']";
	public static final String password = "//input[@name='password']";
	public static final String login = "//*[@id='sessionInvalidate_authorizationAction']";
	public static final String LogOut = "//a[@href='logout']";

	// Home Page Links
	public static final String ExporterRegistration = "//*[@id='showExporterRegistration']";
	public static final String ManageExporters = "//*[@id='showManageExporters']";
	public static final String RecordCostAffidavit = "//*[@id='showManageCostStatementAffidavitDetails']";
	public static final String ManageCOOLink = "//*[@id='showManageCoo']";
	public static final String StatReportLink = "//*[@id='viewServiceProviderTransactionHistory']";
	public static final String RequestCopieslink ="//*[@id='requestCopies']";
	public static final String RecordCostStatementLink = "//*[@id='showManageCostStatementAffidavitDetails']";
	public static final String RegisterExpName ="//*[@id='es-content']/div[1]/div[1]";
	public static final String RegisterExpheading = "Exporter Registration";
	//Citizen Home Page Links
	public static final String ApplyCOOLink = "//*[@id='newApplication']";
	//public static final String ApplyCOOLink = "//*[@id='newApplication']";
	public static final String REApplyCOOLink = "//*[@id='newApplication']";
	//public static final String ErrorMessage1 = "//*[@id='es-content']/div[1]/div[1]";
	// Top Menu Links
	public static final String HomePageLink = "//a[@href='home.action']";
	public static final String LogOutLink = "//a[@href='logout']";
	public static final String ForgotPasswordLink1 ="//*[@id='logout_forgotPassword']";

	// Exporter Registration Link
	public static final String CompanyName = "//*[@id='companyName']";
	public static final String BusinessRegistrationNumber = "//*[@id='businessRegistrationNo']";
	public static final String OfficialAddress = "//*[@id='address']";
	public static final String Tel = "//*[@id='contactNo']";
	public static final String ExpRegNo = "//*[@id='exporterRegistrationNo']";
		public static final String Fax = "//*[@id='faxNo']";
	public static final String ContactEmail = "//*[@id='email']";
	public static final String ContactPerson = "//*[@id='contactPerson']";
	public static final String COOName = "//*[@id='cooSubmitterName']";
	public static final String COOMobile = "//*[@id='cooSubmitterContactNo']";
	public static final String COOEmail = "//*[@id='cooSubmitterEmail']";
	public static final String AddButton = "//input[@value='Add']";
	public static final String ErrorMessage= "//*[@id='messagesDiv']/div/ul/li/span";
	public static final String SuccessMessage = "//*[@id='messagesDiv']/div"; 
	public static final String EmailErrorMessage = "Error while trying to create a login for the given email address, Please check the details and resubmit!";

	// Exporter Login Credentials page
	public static final String ExporterRegSuccessMessage = "//*[@id='messagesDiv']/div";
	public static final String ExporterRegSuccessMessageText = "The exporter was successfully saved!";
	public static final String ExpUsername = "//*[@id='userName']";
	public static final String ExpPassword = "//*[@id='password']";
	public static final String ExpRegistrationNumber = ""; //To be entered 
	public static final String RegNo="//*[@id='exporterRegistrationNo']";

	// Manage Exporters page
	public static final String ExporterRegNoName = "//*[@id='exporterText']";
	public static final String FirstInlineSearchResult = "//*[@id='ui-id-1']/li/a";
	public static final String Status = "//*[@id='exporterStatus']";
	public static final String SearchButton = "//input[@value='Search']";
	public static final String FirstRegistrationNumberLink = "//*[@id='middleGridDiv']/descendant::a";
	public static final String FirstSuspendButton = "(.//input[@value='Suspend'])[1]";
	
	public static final String FirstSearchResultBussName = "//*[@id='middleGridDiv']/descendant::td[2]";
	public static final String SearchResultStatus = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[3]";
	
	public static final String SuspendSuccessMessage = "//*[@id='messagesDiv']/descendant::span";
	public static final String SuspendSuccessMessageText = "The exporter status successfully updated!";
	public static final String FirstActivateButton = "(.//input[@value='Activate'])[1]";
	public static final String ActivateSuccessMessage = "//*[@id='messagesDiv']/descendant::span";
	public static final String ActivateSuccessMessageText = "The exporter status successfully updated!";

	// Modify Exporter Page
	public static final String ModExp_CompanyName = "//*[@id='companyName']";
	public static final String ModExp_BusinessRegNo = "//*[@id='businessRegistrationNo']";
	public static final String ModExp_ExpRegNo = "//*[@id='businessRegistrationNo']";
	public static final String ModExp_OfficialAddress = "//*[@id='address']";
	public static final String ModExp_Tel = "//*[@id='contactNo']";
	public static final String ModExp_Fax = "//*[@id='faxNo']";
	public static final String ModExp_ContactEmail = "//*[@id='email']";
	public static final String ModExp_ContactPerson = "//*[@id='contactPerson']";
	public static final String ModExp_COOName = "//*[@id='cooSubmitterName']";
	public static final String ModExp_COOMobile = "//*[@id='cooSubmitterContactNo']";
	public static final String ModExp_COOEmail = "//*[@id='cooSubmitterEmail']";
	public static final String ModExp_ModifyButton = "//input[@value='Modify']";
	public static final String ModExp_VerifyButton = "//input[@value='Ok']";
	public static final String SuccessfullyModifiedMessage = "//*[@id='messagesDiv']/descendant::span";
	public static final String SuccessfullyModifiedMessageText = "The record was successfully updated!";

	// Cost Statement Affidavit Detail page
	public static final String CostStatementDetail_StatusFirstPart = "//*[@id='status_id";
	public static final String CostStatementDetail_StatusLastPart = "']";
	public static final String CostStatementDetail_DocumentType = "//*[@id='documentType_id']";
	public static final String CostStatementDetail_StatementNo = "//*[@id='statementAffidavitNo_id']";
	public static final String CostStatementDetail_FetchButton = "//*[@id='fetchCostStatement']";
	public static final String CostStatementDetail_Exporter = "//*[@id='exporter_id']";
	public static final String CostStatementDetail_ExporterDropdownFirst = "//*[@id='ui-id-1']/li/a[1]";
	public static final String CostStatementDetail_FTA = "//*[@id='fta_id']";
	public static final String CostStatementDetail_Product = "//*[@id='product_id']";
	public static final String CostStatementDetail_ProductDropdownFirst = "//*[@id='ui-id-2']/li/a[1]";
	public static final String CostStatementDetail_Quantity = "//*[@id='quantity_id']";
	public static final String CostStatementDetail_Unit = "//*[@id='unit_id']";
	public static final String CostStatementDetail_ValidThrough = "//*[@id='validFrom_id']";	
	public static final String CostStatementDetail_DatePicker = "//*[@id='ui-datepicker-div']/table/tbody";
	public static final String CostStatementDetail_DatePickerMonth = "//*[@id='ui-datepicker-div']/div/div/span[1]";
	public static final String CostStatementDetail_DatePickerYear = "//*[@id='ui-datepicker-div']/div/div/span[2]";
	public static final String CostStatementDetail_DatePickerNext = "//*[@id='ui-datepicker-div']/div/a[2]";
	public static final String CostStatementDetail_DatePickerPrev = "//*[@id='ui-datepicker-div']/div/a[1]";
	public static final String CostStatementDetail_To = "//*[@id='validTo_id']";
	public static final String CostStatementDetail_Submit = "//*[@id='showManageCostStatementAffidavitDetails_saveOrModify']";
	public static final String SubmitMessage = "//*[@id='messagesDiv']/descendant::span";
	public static final String ExpectedMessage = "Cost Statement/ Affidavit Details Added Successfully with Reference Number";
	public static final String ErrorMessage1 = "//*[@id='es-content']/div[1]/div[1]";
	// Manage COO page
	public static final String ManageCOOfta = "//*[@id='ftaId']";
	public static final String ManageCOOStatus = "//*[@id='cooStatus']";
	public static final String ManageCOOExporter= "//*[@id='exporterText']";
	public static final String ManageCOOTempRef = "//*[@id='tempReferenceNumber']";
	public static final String ManageCOOCode = "//*[@id='cooReferenceNo']";	
	public static final String ManageCOODuration = "//*[@id='fromDate']";
	public static final String ManageCOOToDate = "//*[@id='toDate']";	
	public static final String ManageCOOSearchButton = "//input[@value='Search']";
	public static final String ManageCOOClearButton = "//input[@value='Clear']";	
	public static final String ManageCOO_DatePicker = "//*[@id='ui-datepicker-div']/table/tbody";
	public static final String ManageCOO_DatePickerMonth = "//*[@id='ui-datepicker-div']/div/div/span[1]";
	public static final String ManageCOO_DatePickerYear = "//*[@id='ui-datepicker-div']/div/div/span[2]";
	public static final String ManageCOO_DatePickerPrev = "//*[@id='ui-datepicker-div']/div/a[1]/span";	
	public static final String ManageCOOFirstSearchResult = "//tbody/tr/td[1]/form/div/u";	
	public static final String COOFirstSearchResultCOONumber = "//tbody/tr[1]/td[3]";		
	public static final String COOFirstSearchResultStatus = "//tbody/tr[1]/td[6]";		
	public static final String COOFirstSearchResultPrintButton = "//tbody/tr[1]/td[12]/img";		
	public static final String ManageCOOSearchResult_ListofCOOCodes = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[3]";
	public static final String ManageCOOSearchResult_ListofExporterNames = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[5]";	
	public static final String ManageCOOSearchResult_ListofStatuses = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[6]";
	public static final String ManageCOOSearchResult_ListofCOOTempRefs = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[1]";
	public static final String ManageCOOSearchResult_ListofDates = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[2]";
	public static final String ManageCOOSearchResult_ListofPrintButtons = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[2]";
	public static final String FirstResult_EditStatusButton = "//*[@id='middleGridDiv']/div/table/tbody/tr[1]/td[10]/img";
	public static final String FirstResult_EditStatusDropdown = "//tbody/tr/td[8]/select";
	public static final String FirstResult_SaveStatusButton = "//tbody/tr/td[11]/img";
	public static final String ConfirmStatusChange = "//button[text()='Save']";
	public static final String ExporterCopySelection = "//input[@value='EXCP']";
	public static final String OfficeCopySelection = "//input[@value='OFCP']";
	public static final String COONoOfCopies = "//*[@id='noOfCopiesDiv']";
	public static final String ConfirmPrint = "//*[@id='printModalPopUp']/descendant::button[text()='Print']";
	public static final String PrintPageHeaderField = "//*[@id='es-content']/div[1]/div[1]";
	public static final String CorrectPrintPageHeader = "Print Certificates of Origin";
	
	
	
	
	
	
	//View COO Page
	public static final String COOstatus = "(.//*[@class='input_div'])[1]";
	public static final String COOexporterName = "(.//*[@class='input_div'])[3]";
	public static final String COOTempRefNo = "(.//*[@class='input_div'])[4]";
	public static final String COORefNo = "(.//*[@class='input_div'])[5]";
	public static final String COOfta = "(.//*[@class='input_div'])[6]";
	
	// Apply COO Page
	public static final String tempRefNumber = "//*[@id='temp_reference_no_id']";
	public static final String tempRefNumberListFirst = ".//*[@id='ui-id-2']";	
	public static final String ftaDropdown = "//*[@id='free_tade_agr_name_list_id']";
	public static final String exporterAddress = "//*[@id='exporter_address_id']";
	public static final String transportationMode = "//*[@id='mode_of_transport_id']";
	public static final String VeselNo = "//*[@id='vessel_flight_no_id']";
	public static final String PortofLoading = "//*[@id='port_of_loading_id']";
	public static final String PortofDischarging = "//*[@id='port_of_discharging_id']";
	public static final String CosigneeName = "//*[@id='consignee_name_id']";
	public static final String CosigneeAddress = "//*[@id='consignee_address_id']";
	public static final String CosigneeCountry = "//*[@id='consignee_country_id']";
	public static final String Declaration = "//*[@id='declaration_id']";
	public static final String CUSDECNo = "//*[@id='cusdec_no_id']";
	public static final String OfficeCode = "//*[@id='office_code_id']";
	public static final String VoyageNo = "//*[@id='voyage_no_id']";
	public static final String DepartureDate = "//*[@id='departure_date_id']";
	public static final String HSCode = "//*[@id='hs_code_id']";
	public static final String PackageMark = "//*[@id='marks_id']";
	public static final String PackageAmount = "//*[@id='no_of_packages_id']";
	public static final String OriginCriterion = "//*[@id='origin_criterion_id']";
	public static final String GrossWeight = "//*[@id='gross_weight_or_ther_id']";
	public static final String Unit = "//*[@id='unit_id']";
	public static final String NetWeight = "//*[@id='net_weight_id']";
	public static final String InvoiceNumber = "//*[@id='invoice_number_id']";
	public static final String FOBValue = "//*[@id='fob_value_id']";
	public static final String InvoiceDate = "//*[@id='invoice_date_id']";
	public static final String AddItemButton = "//*[@id='addbtn_id']";
	public static final String ModifyItemButton = "//*[@id='modifybtn_id']";
	public static final String ClearItemButton = "//*[@id='clearbtn_id']";
	public static final String DeclaredCountry = "//*[@id='good_produced_country_id']";
	public static final String NoOfCopies = "//*[@id='no_of_copies_id']";
	public static final String CollectionMethod = "//*[@id='collection_method_list_id']";
	public static final String PostalAddress = "//*[@id='postal_address_id']";
	public static final String SaveFormButton = "//*[@id='save_id']";
	public static final String SubmitFormButton = "//*[@id='submit_id']";	
	public static final String DatePicker = "//*[@id='ui-datepicker-div']/table/tbody";
	public static final String DatePickerMonth = "//*[@id='ui-datepicker-div']/div/div/span[1]";
	public static final String DatePickerYear = "//*[@id='ui-datepicker-div']/div/div/span[2]";
	public static final String DatePickerNext = "//*[@id='ui-datepicker-div']/div/a[2]";
	public static final String PageHeading = "//*[@id='es-content']/div/div";
	public static final String COOSubmitionMessage = "Payment Confirmation";	
	public static final String ResubmitMessage = "//*[@id='messagesDiv']/div/ul/li/span";
	public static final String ResubmitSuccessfullMessage = ""; //resubmit successful message needs to be put here
	public static final String Status1="//*[@id='type_of_coo_application_id2']";

	//ApplyCOO Payment Page
	public static final String TransRefNoField = "//*[@id='payment_confirmation_form_id']/div/div[1]/div[1]/div/div";
	public static final String ApplicationFeeField = "//*[@id='payment_confirmation_form_id']/div/div[1]/div[2]/div[2]/div";
	public static final String AdditionCopyFeeField = "//*[@id='payment_confirmation_form_id']/div/div[1]/div[3]/div[2]/div";
	public static final String DeliveryCostField = "//*[@id='payment_confirmation_form_id']/div/div[1]/div[4]/div[2]/div";
	public static final String TotalCostField = "//*[@id='payment_confirmation_form_id']/div/div[1]/div[5]/div[2]/div";
	public static final String TransRefNoFormat = "TRN/DOC/";
	public static final double ApplicationFee = 1000.00;
	public static final double AdditionalCopyFee = 500.00;
	public static final double PostalFee = 10.00;	
	public static final String PayButton = "//*[@id='pay_id']";
	public static final String ConfirmButton = "//button[@type='submit']";
	public static final String COOPayment_PageHeading = "//*[@id='logo']/img";
	public static final String COOPaymentGatewayMessage = "Lanka Government Payment Service";
	public static final String SaveMessage = "//*[@id='messagesDiv']/div/ul/li";

	//Statistic Report Page
	public static final String DateFrom = "//*[@id='fromDate']";
	public static final String DateTo = "//*[@id='toDate']";
	public static final String StatReport_fta = "//*[@id='ftaId']";
	public static final String StatReport_exportingCountry = "//*[@id='countryId']";
	public static final String StatReport_productCode = "//*[@id='productCode']";
	public static final String StatReport_exporter = "//*[@id='exporterText']";
	public static final String StatReport_searchButton = "//input[@value='Search']";
	public static final String StatReport_DatePicker = "//*[@id='ui-datepicker-div']/table/tbody";
	public static final String StatReport_DatePickerMonth = "//*[@id='ui-datepicker-div']/div/div/span[1]";
	public static final String StatReport_DatePickerYear = "//*[@id='ui-datepicker-div']/div/div/span[2]";
	public static final String StatReport_DatePickerNext = "//*[@id='ui-datepicker-div']/div/a[2]";
	public static final String StatReport_DatePickerPrev = "//*[@id='ui-datepicker-div']/div/a[1]";
	public static final String StatReport_SearchResultsFTAs = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[2]";
	public static final String StatReport_SearchResultsProductCodes = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[5]";
	public static final String StatReport_SearchResultsExportingCountry = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[3]";
	public static final String StatReport_SearchResultsFOBValues = "//*[@id='middleGridDiv']/div/table/tbody/tr/td[8]";
	public static final String StatReport_SearchResultsFOBTotal = "//*[text()='Total']/following::td[2]";

	//Request copies
		public static final String ExpRegNo1 = "//*[@id='exp_registration_no_id']";
		public static final String RecNo1 = "//*[@id='receipt_no_id']";
		public static final String SaveButton = "//*[@id='save_id']";
		public static final String GetFTA = "//*[@id='fta_id']";
		public static final String GetProduct = "//*[@id='product_id']";
		public static final String GetCountry = "//*[@id='consignee_country_id']";
		public static final String GetExpReg2 = "//*[@id='exp_registration_no_id']";
		public static final String GetCOORef = "//*[@id='coo_ref_no_id']";
		public static final String GetCopies = "//*[@id='no_of_copies_list_id']";
		public static final String GetCollection = "//*[@id='collection_method_list_id']";
		public static final String GetPostal = "//*[@id='postal_address_id']";
		public static final String GetReceipt = "//*[@id='receipt_no_id']";
		public static final String Reset="//*[@id='btn_reset_id']";
		public static final String Fetch="//*[@id='btn1_fetch_data_id']";
		public static final String CompanyName1 ="//*[@id='expoter_name_id']";
		public static final String save2 ="//*[@id='save_id']";
		public static final String confirm = "//*[@id='pay_id']";
	 	
}









