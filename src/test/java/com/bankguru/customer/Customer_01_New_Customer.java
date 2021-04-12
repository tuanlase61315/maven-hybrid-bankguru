package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.common.Common_01_Register;

import commons.BaseTest;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import utilities.FakerConfig;

public class Customer_01_New_Customer extends BaseTest {
	WebDriver driver;
	FakerConfig faker;
	public static String cusID, cusName, cusDateOfBirth, cusAddress, cusCity, cusState, cusPin, cusMobileNumber, cusEmail,
			cusPassword, cusDateOfBirthNew;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		log.info("Precondition - Step 01: Open Login Page");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Precondition - Step 02: Enter UserID in Textbox with value: " + Common_01_Register.userID);
		loginPage.inputToTextboxByName(driver, "uid", Common_01_Register.userID);

		log.info("Precondition - Step 03: Enter Password in Textbox with value: " + Common_01_Register.password);
		loginPage.inputToTextboxByName(driver, "password", Common_01_Register.password);

		log.info("Precondition - Step 04: Click Login button");
		loginPage.clickToButtonByValue(driver, "LOGIN");
		homePage = PageGeneratorManager.getHomePageObject(driver);

		log.info("Precondition - Step 05: Verify homepage message after login successfull");
		verifyTrue(homePage.isHomePageMessageDisplay());
		
		cusName = "tuan";
		cusDateOfBirth = "10/01/1993";
		cusAddress = "172 tay ninh";
		cusCity = "tay ninh";
		cusState = "abc";
		cusPin = "123456";
		cusMobileNumber = "0399992426";
		cusEmail = homePage.generateEmail();
		cusPassword = "123123";
	}

	@Test
	public void New_Customer_01_Verify_Name_Field() {
		log.info("Verify Name - Step 01: Open New Customer Page");
		customerPage = (CustomerPageObject) homePage.clickToMenuNavByName(driver, "New Customer");

		log.info("Verify Name - Step 02: Click Customer Name textbox");
		customerPage.clickToTextboxByName(driver, "name");

		log.info("Verify Name - Step 03: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "name");

		log.info("Verify Name - Step 04: Verify an error message 'Customer name must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"),
				"Customer name must not be blank");

		log.info("Verify Name - Step 05: Enter Customer Name in Textbox with value: 1234");
		customerPage.inputToTextboxByName(driver, "name", "1234");

		log.info("Verify Name - Step 06: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"), "Numbers are not allowed");

		log.info("Verify Name - Step 07: Enter Customer Name in Textbox with value: name1234");
		customerPage.inputToTextboxByName(driver, "name", "name1234");

		log.info("Verify Name - Step 08: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"), "Numbers are not allowed");

		log.info("Verify Name - Step 09: Enter Customer Name in Textbox with value: name!@#");
		customerPage.inputToTextboxByName(driver, "name", "name!@#");

		log.info("Verify Name - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"),
				"Special characters are not allowed");

		log.info("Verify Name - Step 11: Enter Customer Name in Textbox with value: !@#");
		customerPage.inputToTextboxByName(driver, "name", "!@#");

		log.info("Verify Name - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"),
				"Special characters are not allowed");

		log.info("Verify Name - Step 13: Enter Customer Name in Textbox with 'First charater blank space'");
		customerPage.inputToTextboxByName(driver, "name", " ");

		log.info("Verify Name - Step 14: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"),
				"First character can not have space");

	}

	@Test
	public void New_Customer_02_Verify_Address_Field() {
		log.info("Verify Address - Step 01: Click Customer Address Textarea");
		customerPage.clickToAddressTextarea();

		log.info("Verify Address - Step 02: Press Tab key");
		customerPage.sendTabKeyToAddressTextarea();

		log.info("Verify Address - Step 03: Verify an error message 'Address Field must not be blank' is displayed");
		verifyEquals(customerPage.getCustomerErrorMessageByTextarea(), "Address Field must not be blank");

		log.info("Verify Address - Step 04: Enter Customer Adress in Textarea with 'First charater blank space'");
		customerPage.inputToAddressTextarea(" ");

		log.info("Verify Address - Step 05: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getCustomerErrorMessageByTextarea(), "First character can not have space");
	}

	@Test
	public void New_Customer_03_Verify_City_Field() {
		log.info("Verify City - Step 01: Click Customer City textbox");
		customerPage.clickToTextboxByName(driver, "city");

		log.info("Verify City - Step 02: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "city");

		log.info("Verify City - Step 03: Verify an error message 'City Field must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "City Field must not be blank");

		log.info("Verify City - Step 04: Enter Customer City in Textbox with value: 1234");
		customerPage.inputToTextboxByName(driver, "city", "1234");

		log.info("Verify City - Step 05: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");

		log.info("Verify City - Step 06: Enter Customer City in Textbox with value: !name1234");
		customerPage.inputToTextboxByName(driver, "city", "city1234");

		log.info("Verify City - Step 07: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");

		log.info("Verify City - Step 08: Enter Customer City in Textbox with value: City!@#");
		customerPage.inputToTextboxByName(driver, "city", "City!@#");

		log.info("Verify City - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"),
				"Special characters are not allowed");

		log.info("Verify City - Step 10: Enter Customer City in Textbox with value: !@#");
		customerPage.inputToTextboxByName(driver, "city", "!@#");

		log.info("Verify City - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"),
				"Special characters are not allowed");

		log.info("Verify City - Step 12: Enter Customer City in Textbox with 'First charater blank space'");
		customerPage.inputToTextboxByName(driver, "city", " ");

		log.info("Verify City - Step 13: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"),
				"First character can not have space");

	}

	@Test
	public void New_Customer_04_Verify_State_Field() {
		log.info("Verify State - Step 01: Click Customer State textbox");
		customerPage.clickToTextboxByName(driver, "state");

		log.info("Verify State - Step 02: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "state");

		log.info("Verify State - Step 03: Verify an error message 'State must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "State must not be blank");

		log.info("Verify State - Step 04: Enter Customer State in Textbox with value: 1234");
		customerPage.inputToTextboxByName(driver, "state", "1234");

		log.info("Verify State - Step 05: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");

		log.info("Verify State - Step 06: Enter Customer State in Textbox with value: state1234");
		customerPage.inputToTextboxByName(driver, "state", "state1234");

		log.info("Verify State - Step 07: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");

		log.info("Verify State - Step 08: Enter Customer State in Textbox with value: State!@#");
		customerPage.inputToTextboxByName(driver, "state", "State!@#");

		log.info("Verify State - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"),
				"Special characters are not allowed");

		log.info("Verify State - Step 10: Enter Customer State in Textbox with value: !@#");
		customerPage.inputToTextboxByName(driver, "state", "!@#");

		log.info("Verify State - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"),
				"Special characters are not allowed");

		log.info("Verify State - Step 12: Enter Customer State in Textbox with 'First charater blank space'");
		customerPage.inputToTextboxByName(driver, "state", " ");

		log.info("Verify State - Step 13: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"),
				"First character can not have space");

	}

	@Test
	public void New_Customer_05_Verify_PIN_Field() {
		log.info("Verify PIN - Step 01: Click Customer PIN textbox");
		customerPage.clickToTextboxByName(driver, "pinno");

		log.info("Verify PIN - Step 02: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "pinno");

		log.info("Verify PIN - Step 03: Verify an error message 'PIN Code must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must not be blank");

		log.info("Verify PIN - Step 04: Enter Customer PIN in Textbox with value: 1234");
		customerPage.inputToTextboxByName(driver, "pinno", "1234");

		log.info("Verify PIN - Step 05: Verify an error message 'PIN Code must have 6 Digits' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must have 6 Digits");

		log.info("Verify PIN - Step 06: Enter Customer PIN in Textbox with value: PIN1234");
		customerPage.inputToTextboxByName(driver, "pinno", "PIN1234");

		log.info("Verify PIN - Step 07: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "Characters are not allowed");

		log.info("Verify PIN - Step 08: Enter Customer PIN in Textbox with value: PIN!@#");
		customerPage.inputToTextboxByName(driver, "pinno", "PIN!@#");

		log.info("Verify PIN - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"),
				"Special characters are not allowed");

		log.info("Verify PIN - Step 10: Enter Customer PIN in Textbox with value: !@#");
		customerPage.inputToTextboxByName(driver, "pinno", "!@#");

		log.info("Verify PIN - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"),
				"Special characters are not allowed");

		log.info("Verify PIN - Step 12: Enter Customer PIN in Textbox with 'First charater blank space'");
		customerPage.inputToTextboxByName(driver, "pinno", " ");

		log.info("Verify PIN - Step 13: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"),
				"First character can not have space");

		log.info("Verify PIN - Step 14: Enter Customer PIN in Textbox with value: 123");
		customerPage.inputToTextboxByName(driver, "pinno", "123");

		log.info("Verify PIN - Step 15: Verify an error message 'PIN Code must have 6 Digits' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must have 6 Digits");

		log.info("Verify PIN - Step 16: Enter Customer PIN in Textbox with value: '123 12'");
		customerPage.inputToTextboxByName(driver, "pinno", "123 12");

		log.info("Verify PIN - Step 17: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "Characters are not allowed");
	}

	@Test
	public void New_Customer_06_Verify_Telephone_Field() {
		log.info("Verify Telephone - Step 01: Click Customer Telephone textbox");
		customerPage.clickToTextboxByName(driver, "telephoneno");

		log.info("Verify Telephone - Step 02: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "telephoneno");

		log.info("Verify Telephone - Step 03: Verify an error message 'Mobile no must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Mobile no must not be blank");

		log.info("Verify Telephone - Step 04: Enter Customer Telephone in Textbox with 'First charater blank space'");
		customerPage.inputToTextboxByName(driver, "telephoneno", " ");

		log.info(
				"Verify Telephone - Step 05: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"First character can not have space");

		log.info("Verify Telephone - Step 06: Enter Customer Telephone in Textbox with value: '123 1213'");
		customerPage.inputToTextboxByName(driver, "telephoneno", "123 1213");

		log.info("Verify Telephone - Step 07: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Characters are not allowed");

		log.info("Verify Telephone - Step 08: Enter Customer State in Textbox with value: 888!123123");
		customerPage.inputToTextboxByName(driver, "telephoneno", "888!123123");

		log.info(
				"Verify Telephone - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Special characters are not allowed");

		log.info("Verify Telephone - Step 10: Enter Customer State in Textbox with value: !888123123");
		customerPage.inputToTextboxByName(driver, "telephoneno", "!888123123");

		log.info(
				"Verify Telephone - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Special characters are not allowed");

		log.info("Verify Telephone - Step 12: Enter Customer State in Textbox with value: 888123123!");
		customerPage.inputToTextboxByName(driver, "telephoneno", "888123123!");

		log.info(
				"Verify Telephone - Step 13: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Special characters are not allowed");

	}

	@Test
	public void New_Customer_07_Verify_Email_Field() {
		log.info("Verify Email - Step 01: Click Customer Email textbox");
		customerPage.clickToTextboxByName(driver, "emailid");

		log.info("Verify Email - Step 02: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "emailid");

		log.info("Verify Email - Step 03: Verify an error message 'Email-ID must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"),
				"Email-ID must not be blank");

		log.info("Verify Email - Step 04: Enter Customer Email in Textbox with value: 'test222@gmail'");
		customerPage.inputToTextboxByName(driver, "emailid", "test222@gmail");

		log.info("Verify Email - Step 05: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 06: Enter Customer Email in Textbox with value: 'test222'");
		customerPage.inputToTextboxByName(driver, "emailid", "test222");

		log.info("Verify Email - Step 07: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 08: Enter Customer Email in Textbox with value: 'test222@'");
		customerPage.inputToTextboxByName(driver, "emailid", "test222@");

		log.info("Verify Email - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 10: Enter Customer Email in Textbox with value: 'test222@gmail.'");
		customerPage.inputToTextboxByName(driver, "emailid", "test222@gmail.");

		log.info("Verify Email - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 12: Enter Customer Email in Textbox with value: 'test222gmail.com'");
		customerPage.inputToTextboxByName(driver, "emailid", "test222gmail.com");

		log.info("Verify Email - Step 13: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 14: Enter Customer Email in Textbox with 'First charater blank space'");
		customerPage.inputToTextboxByName(driver, "emailid", " ");

		log.info("Verify Email - Step 15: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"),
				"First character can not have space");

	}

	@Test
	public void New_Customer_08_Verify_All_Field_Requirement() {
		log.info("Verify All - Step 01: Click Resert button");
		customerPage.clickToButtonByValue(driver, "Reset");

		log.info("Verify All - Step 02: Click Customer Name textbox");
		customerPage.clickToTextboxByName(driver, "name");

		log.info("Verify All - Step 03: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "name");

		log.info("Verify All - Step 04: Verify an error message 'Customer name must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"),
				"Customer name must not be blank");

		log.info("Verify All - Step 05: Click Customer Date of Birth textbox");
		customerPage.clickToTextboxByName(driver, "dob");

		log.info("Verify All - Step 06: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "dob");

		log.info("Verify All - Step 07: Verify an error message 'Date Field must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "dob"), "Date Field must not be blank");

		log.info("Verify All - Step 08: Click Customer Address Textarea");
		customerPage.clickToAddressTextarea();

		log.info("Verify All - Step 09: Press Tab key");
		customerPage.sendTabKeyToAddressTextarea();

		log.info("Verify All - Step 10: Verify an error message 'Address Field must not be blank' is displayed");
		verifyEquals(customerPage.getCustomerErrorMessageByTextarea(), "Address Field must not be blank");

		log.info("Verify All - Step 11: Click Customer City textbox");
		customerPage.clickToTextboxByName(driver, "city");

		log.info("Verify All - Step 12: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "city");

		log.info("Verify All - Step 13: Verify an error message 'Customer City must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "City Field must not be blank");

		log.info("Verify All - Step 14: Click Customer State textbox");
		customerPage.clickToTextboxByName(driver, "state");

		log.info("Verify All - Step 15: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "state");

		log.info("Verify All - Step 16: Verify an error message 'State must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "State must not be blank");

		log.info("Verify All - Step 17: Click Customer PIN textbox");
		customerPage.clickToTextboxByName(driver, "pinno");

		log.info("Verify All - Step 18: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "pinno");

		log.info("Verify All - Step 19: Verify an error message 'PIN Code must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must not be blank");

		log.info("Verify All - Step 20: Click Customer Phone textbox");
		customerPage.clickToTextboxByName(driver, "telephoneno");

		log.info("Verify All - Step 21: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "telephoneno");

		log.info("Verify All - Step 22: Verify an error message 'Mobile no must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Mobile no must not be blank");

		log.info("Verify All - Step 23: Click Customer Email textbox");
		customerPage.clickToTextboxByName(driver, "emailid");

		log.info("Verify All - Step 24: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "emailid");

		log.info("Verify All - Step 25: Verify an error message 'Email-ID must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"),
				"Email-ID must not be blank");

		log.info("Verify All - Step 26: Click Customer Password textbox");
		customerPage.clickToTextboxByName(driver, "password");

		log.info("Verify All - Step 27: Press Tab key");
		customerPage.sendTabKeyToTextboxByName(driver, "password");

		log.info("Verify All - Step 28: Verify an error message 'Password must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "password"),
				"Password must not be blank");

		log.info("Verify All - Step 29: Click Submit button");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("Verify All - Step 30: Verify Alert text 'please fill all fields' is displayed");
		verifyEquals(customerPage.getTextSubmitAlert(), "please fill all fields");

		log.info("Verify All - Step 31: Click OK button in Alert");
		customerPage.acceptAlert(driver);
	}
	
	@Test
	public void New_Customer_09_New_Customer_With_Valid_Data() {
		log.info("New Customer - Step 01: Open New Customer Page");
		customerPage = (CustomerPageObject) homePage.clickToMenuNavByName(driver, "New Customer");

		log.info("New Customer - Step 02: Enter Customer Name in Textbox with value: " + cusName);
		customerPage.inputToTextboxByName(driver, "name", cusName);

		log.info("New Customer - Step 03: Select Female Gender");
		customerPage.clickToRadioButtonByValue(driver, "f");

		log.info("New Customer - Step 04: Enter Data of Birth with value: " + cusDateOfBirth);
		customerPage.inputToTextboxByName(driver, "dob", cusDateOfBirth);
		
		
		log.info("New Customer - Step 05:  Enter Customer Address in Textbox with value: " + cusAddress);
		customerPage.inputToAddressTextarea(cusAddress);

		log.info("New Customer - Step 06:  Enter Customer City in Textbox with value: " + cusCity);
		customerPage.inputToTextboxByName(driver, "city", cusCity);

		log.info("New Customer - Step 07:  Enter Customer State in Textbox with value: " + cusState);
		customerPage.inputToTextboxByName(driver, "state", cusState);

		log.info("New Customer - Step 08:  Enter Customer PIN in Textbox with value: " + cusPin);
		customerPage.inputToTextboxByName(driver, "pinno", cusPin);

		log.info("New Customer - Step 09: Enter Customer Mobile Phone in Textbox with value: " + cusMobileNumber);
		customerPage.inputToTextboxByName(driver, "telephoneno", cusMobileNumber);

		log.info("New Customer - Step 10: Enter Customer Email in Textbox with value: " + cusEmail);
		customerPage.inputToTextboxByName(driver, "emailid", cusEmail);

		log.info("New Customer - Step 11: Enter Customer Password in Textbox with value: " + cusPassword);
		customerPage.inputToTextboxByName(driver, "password", cusPassword);

		log.info("New Customer - Step 12: Click button Submit");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("New Customer - Step 13: Verify Customer Registered Successfully Message");
		verifyEquals(customerPage.getRegisteredAndEditedSuccessMessage(driver), "Customer Registered Successfully!!!");
		
		log.info("New Customer - Step 14: Verify Customer Name with value " + cusName);
		verifyEquals(customerPage.getCustomerInfoByText("Customer Name"), cusName);
		
		log.info("New Customer - Step 15: Verify Customer Gender with value Female" );
		verifyEquals(customerPage.getCustomerInfoByText("Gender"), "female");		
		cusDateOfBirthNew = customerPage.newFormatDate(cusDateOfBirth);
		
		log.info("New Customer - Step 16: Verify Customer Birthdate with value " + cusDateOfBirthNew);
		verifyEquals(customerPage.getCustomerInfoByText("Birthdate"), cusDateOfBirthNew);
		
		log.info("New Customer - Step 17: Verify Customer Address with value " + cusAddress);
		verifyEquals(customerPage.getCustomerInfoByText("Address"), cusAddress);
		
		log.info("New Customer - Step 18: Verify Customer State with value " + cusState);
		verifyEquals(customerPage.getCustomerInfoByText("State"), cusState);
		
		log.info("New Customer - Step 19: Verify Customer Pin with value " + cusPin);
		verifyEquals(customerPage.getCustomerInfoByText("Pin"), cusPin);
		
		log.info("New Customer - Step 20: Verify Customer Mobile No with value " + cusMobileNumber);
		verifyEquals(customerPage.getCustomerInfoByText("Mobile No."), cusMobileNumber);
		
		log.info("New Customer - Step 21: Verify Customer Email with value " + cusEmail);
		verifyEquals(customerPage.getCustomerInfoByText("Email"), cusEmail);
		
		cusID = customerPage.getCustomerInfoByText("Customer ID");

	}
	


	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	CustomerPageObject customerPage;

}
