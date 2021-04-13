package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.testdata.UserData;

import commons.BaseTest;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;

public class Customer_01_New_Customer extends BaseTest {
	WebDriver driver;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		log.info("Precondition - Step 01: Open Login Page");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Precondition - Step 02: Enter UserID in Textbox with value: " + UserData.Login.userID);
		loginPage.inputToTextboxByName(driver, "uid", UserData.Login.userID);

		log.info("Precondition - Step 03: Enter Password in Textbox with value: " + UserData.Login.password);
		loginPage.inputToTextboxByName(driver, "password",UserData.Login.password);

		log.info("Precondition - Step 04: Click Login button");
		loginPage.clickToButtonByValue(driver, "LOGIN");
		homePage = PageGeneratorManager.getHomePageObject(driver);

		log.info("Precondition - Step 05: Verify homepage message after login successfull");
		verifyTrue(homePage.isHomePageMessageDisplay());
		
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

		log.info("Verify Name - Step 05: Enter Customer Name in Textbox with value: " + UserData.VerifyData.numeric);
		customerPage.inputToTextboxByName(driver, "name", UserData.VerifyData.numeric);

		log.info("Verify Name - Step 06: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"), "Numbers are not allowed");

		log.info("Verify Name - Step 07: Enter Customer Name in Textbox with value: " +  UserData.VerifyData.numericAndCharacter);
		customerPage.inputToTextboxByName(driver, "name", UserData.VerifyData.numericAndCharacter);

		log.info("Verify Name - Step 08: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"), "Numbers are not allowed");

		log.info("Verify Name - Step 09: Enter Customer Name in Textbox with value: name!@#" + UserData.VerifyData.specialAndCharacter);
		customerPage.inputToTextboxByName(driver, "name", UserData.VerifyData.specialAndCharacter);

		log.info("Verify Name - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"),
				"Special characters are not allowed");

		log.info("Verify Name - Step 11: Enter Customer Name in Textbox with value: !@#" + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "name", UserData.VerifyData.special);

		log.info("Verify Name - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "name"),
				"Special characters are not allowed");

		log.info("Verify Name - Step 13: Enter Customer Name in Textbox with 'First charater blank space' " + UserData.VerifyData.firstCharacterIsSpace);
		customerPage.inputToTextboxByName(driver, "name", UserData.VerifyData.firstCharacterIsSpace);

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

		log.info("Verify Address - Step 04: Enter Customer Adress in Textarea with 'First charater blank space'" + UserData.VerifyData.firstCharacterIsSpace);
		customerPage.inputToAddressTextarea(UserData.VerifyData.firstCharacterIsSpace);

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

		log.info("Verify City - Step 04: Enter Customer City in Textbox with value: " + UserData.VerifyData.numeric);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.numeric);

		log.info("Verify City - Step 05: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");

		log.info("Verify City - Step 06: Enter Customer City in Textbox with value: " + UserData.VerifyData.numericAndCharacter);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.numericAndCharacter);

		log.info("Verify City - Step 07: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");

		log.info("Verify City - Step 08: Enter Customer City in Textbox with value: " + UserData.VerifyData.specialAndCharacter);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.specialAndCharacter);

		log.info("Verify City - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"),
				"Special characters are not allowed");

		log.info("Verify City - Step 10: Enter Customer City in Textbox with value: " + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.special);

		log.info("Verify City - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"),
				"Special characters are not allowed");

		log.info("Verify City - Step 12: Enter Customer City in Textbox with 'First charater blank space'" + UserData.VerifyData.firstCharacterIsSpace);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.firstCharacterIsSpace);

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

		log.info("Verify State - Step 04: Enter Customer State in Textbox with value: " + UserData.VerifyData.numeric);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.numeric);

		log.info("Verify State - Step 05: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");

		log.info("Verify State - Step 06: Enter Customer State in Textbox with value: " + UserData.VerifyData.numericAndCharacter);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.numericAndCharacter);

		log.info("Verify State - Step 07: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");

		log.info("Verify State - Step 08: Enter Customer State in Textbox with value: " + UserData.VerifyData.specialAndCharacter);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.specialAndCharacter);

		log.info("Verify State - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"),
				"Special characters are not allowed");

		log.info("Verify State - Step 10: Enter Customer State in Textbox with value: " + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.special);

		log.info("Verify State - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"),
				"Special characters are not allowed");

		log.info("Verify State - Step 12: Enter Customer State in Textbox with 'First charater blank space' " + UserData.VerifyData.firstCharacterIsSpace);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.firstCharacterIsSpace);

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

		log.info("Verify PIN - Step 04: Enter Customer PIN in Textbox with value: " + UserData.VerifyData.digitLessThanSix);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.digitLessThanSix);

		log.info("Verify PIN - Step 05: Verify an error message 'PIN Code must have 6 Digits' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must have 6 Digits");

		log.info("Verify PIN - Step 06: Enter Customer PIN in Textbox with value: " + UserData.VerifyData.numericAndCharacter);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.numericAndCharacter);

		log.info("Verify PIN - Step 07: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "Characters are not allowed");

		log.info("Verify PIN - Step 08: Enter Customer PIN in Textbox with value: " + UserData.VerifyData.specialAndCharacter);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.specialAndCharacter);

		log.info("Verify PIN - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"),
				"Special characters are not allowed");

		log.info("Verify PIN - Step 10: Enter Customer PIN in Textbox with value: " + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.special);

		log.info("Verify PIN - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"),
				"Special characters are not allowed");

		log.info("Verify PIN - Step 12: Enter Customer PIN in Textbox with 'First charater blank space' " + UserData.VerifyData.firstCharacterIsSpace);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.firstCharacterIsSpace);

		log.info("Verify PIN - Step 13: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"),
				"First character can not have space");

		log.info("Verify PIN - Step 14: Enter Customer PIN in Textbox with value: " + UserData.VerifyData.digitLessThanSix);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.digitLessThanSix);

		log.info("Verify PIN - Step 15: Verify an error message 'PIN Code must have 6 Digits' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must have 6 Digits");

		log.info("Verify PIN - Step 16: Enter Customer PIN in Textbox with value: " + UserData.VerifyData.numericAndBlank);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.numericAndBlank);

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

		log.info("Verify Telephone - Step 04: Enter Customer Telephone in Textbox with 'First charater blank space' " + UserData.VerifyData.firstCharacterIsSpace);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.VerifyData.firstCharacterIsSpace);

		log.info(
				"Verify Telephone - Step 05: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"First character can not have space");

		log.info("Verify Telephone - Step 06: Enter Customer Telephone in Textbox with value: " + UserData.VerifyData.numericAndBlank);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.VerifyData.numericAndBlank);

		log.info("Verify Telephone - Step 07: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Characters are not allowed");

		log.info("Verify Telephone - Step 08: Enter Customer State in Textbox with value: " + UserData.VerifyData.numericAndSpecial);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.VerifyData.numericAndSpecial);

		log.info(
				"Verify Telephone - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Special characters are not allowed");

		log.info("Verify Telephone - Step 10: Enter Customer State in Textbox with value: " + UserData.VerifyData.specialAndNumeric);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.VerifyData.specialAndNumeric);

		log.info(
				"Verify Telephone - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"),
				"Special characters are not allowed");

		log.info("Verify Telephone - Step 12: Enter Customer State in Textbox with value: " + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.VerifyData.special);

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

		log.info("Verify Email - Step 04: Enter Customer Email in Textbox with value: " + UserData.VerifyData.emailNotCom);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.emailNotCom);

		log.info("Verify Email - Step 05: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 06: Enter Customer Email in Textbox with value: " + UserData.VerifyData.emailOnlyName);
		customerPage.inputToTextboxByName(driver, "emailid",  UserData.VerifyData.emailOnlyName);

		log.info("Verify Email - Step 07: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 08: Enter Customer Email in Textbox with value: " +  UserData.VerifyData.emailNotGmailDotCom);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.emailNotGmailDotCom);

		log.info("Verify Email - Step 09: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 10: Enter Customer Email in Textbox with value: " + UserData.VerifyData.emailNotCom);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.emailNotCom);

		log.info("Verify Email - Step 11: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 12: Enter Customer Email in Textbox with value: 'test222gmail.com'" + UserData.VerifyData.emailNotFormat);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.emailNotFormat);

		log.info("Verify Email - Step 13: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");

		log.info("Verify Email - Step 14: Enter Customer Email in Textbox with 'First charater blank space' " + UserData.VerifyData.firstCharacterIsSpace);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.firstCharacterIsSpace);

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

		log.info("New Customer - Step 02: Enter Customer Name in Textbox with value: " + UserData.Customer.cusName);
		customerPage.inputToTextboxByName(driver, "name", UserData.Customer.cusName);

		log.info("New Customer - Step 03: Select Female Gender");
		customerPage.clickToRadioButtonByValue(driver, "f");

		log.info("New Customer - Step 04: Enter Data of Birth with value: " + UserData.Customer.cusDateOfBirth);
		customerPage.inputToTextboxByName(driver, "dob", UserData.Customer.cusDateOfBirth);
		
		
		log.info("New Customer - Step 05:  Enter Customer Address in Textbox with value: " + UserData.Customer.cusAddress);
		customerPage.inputToAddressTextarea(UserData.Customer.cusAddress);

		log.info("New Customer - Step 06:  Enter Customer City in Textbox with value: " + UserData.Customer.cusCity);
		customerPage.inputToTextboxByName(driver, "city", UserData.Customer.cusCity);

		log.info("New Customer - Step 07:  Enter Customer State in Textbox with value: " + UserData.Customer.cusState);
		customerPage.inputToTextboxByName(driver, "state", UserData.Customer.cusState);

		log.info("New Customer - Step 08:  Enter Customer PIN in Textbox with value: " + UserData.Customer.cusPin);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.Customer.cusPin);

		log.info("New Customer - Step 09: Enter Customer Mobile Phone in Textbox with value: " + UserData.Customer.cusMobileNumber);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.Customer.cusMobileNumber);

		log.info("New Customer - Step 10: Enter Customer Email in Textbox with value: " + UserData.Customer.cusEmail);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.Customer.cusEmail);

		log.info("New Customer - Step 11: Enter Customer Password in Textbox with value: " + UserData.Customer.cusPassword);
		customerPage.inputToTextboxByName(driver, "password", UserData.Customer.cusPassword);

		log.info("New Customer - Step 12: Click button Submit");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("New Customer - Step 13: Verify Customer Registered Successfully Message");
		verifyEquals(customerPage.getHeadingMessage(driver), "Customer Registered Successfully!!!");
		
		log.info("New Customer - Step 14: Verify Customer Name with value " + UserData.Customer.cusName);
		verifyEquals(customerPage.getCustomerInfoByText("Customer Name"), UserData.Customer.cusName);
		
		log.info("New Customer - Step 15: Verify Customer Gender with value Female" );
		verifyEquals(customerPage.getCustomerInfoByText("Gender"), "female");		
		UserData.Customer.cusDateOfBirthNew = customerPage.newFormatDate(UserData.Customer.cusDateOfBirth);
		
		log.info("New Customer - Step 16: Verify Customer Birthdate with value " + UserData.Customer.cusDateOfBirthNew);
		verifyEquals(customerPage.getCustomerInfoByText("Birthdate"), UserData.Customer.cusDateOfBirthNew);
		
		log.info("New Customer - Step 17: Verify Customer Address with value " + UserData.Customer.cusAddress);
		verifyEquals(customerPage.getCustomerInfoByText("Address"), UserData.Customer.cusAddress);
		
		log.info("New Customer - Step 18: Verify Customer State with value " + UserData.Customer.cusState);
		verifyEquals(customerPage.getCustomerInfoByText("State"), UserData.Customer.cusState);
		
		log.info("New Customer - Step 19: Verify Customer Pin with value " + UserData.Customer.cusPin);
		verifyEquals(customerPage.getCustomerInfoByText("Pin"), UserData.Customer.cusPin);
		
		log.info("New Customer - Step 20: Verify Customer Mobile No with value " + UserData.Customer.cusMobileNumber);
		verifyEquals(customerPage.getCustomerInfoByText("Mobile No."), UserData.Customer.cusMobileNumber);
		
		log.info("New Customer - Step 21: Verify Customer Email with value " + UserData.Customer.cusEmail);
		verifyEquals(customerPage.getCustomerInfoByText("Email"), UserData.Customer.cusEmail);
		
		UserData.Customer.cusID = customerPage.getCustomerInfoByText("Customer ID");
		System.out.println("Customer ID : " + UserData.Customer.cusID);
	}
	


	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	CustomerPageObject customerPage;

}
