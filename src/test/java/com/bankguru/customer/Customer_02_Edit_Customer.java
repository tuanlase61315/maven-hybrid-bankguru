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

public class Customer_02_Edit_Customer extends BaseTest {
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
		loginPage.inputToTextboxByName(driver, "password", UserData.Login.password);

		log.info("Precondition - Step 04: Click Login button");
		loginPage.clickToButtonByValue(driver, "LOGIN");
		homePage = PageGeneratorManager.getHomePageObject(driver);

		log.info("Precondition - Step 05: Verify homepage message after login successfull");
		verifyTrue(homePage.isHomePageMessageDisplay());

	}

	@Test
	public void Edit_Customer_01_Verify_CustomerID() {
		log.info("Verify CustomerID - Step 01: Click to Edit Customer in Navigate Menu bar");
		customerPage = (CustomerPageObject) homePage.clickToMenuNavByName(driver, "Edit Customer");

		log.info("Verify CustomerID - Step 02: Click to Customer ID textbox");
		customerPage.clickToTextboxByName(driver, "cusid");

		log.info("Verify CustomerID - Step 03: Press Tab key in Customer ID textbox");
		customerPage.sendTabKeyToTextboxByName(driver, "cusid");

		log.info("Verify CustomerID - Step 04: Verify an error message 'Customer ID is required' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Customer ID is required");

		log.info("Verify CustomerID - Step 05: Enter Customer ID textbox with value " + UserData.VerifyData.numericAndCharacter);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.numericAndCharacter);

		log.info("Verify CustomerID - Step 06: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify CustomerID - Step 07: Enter Customer ID textbox with value " + UserData.VerifyData.characterAndNumberic);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.characterAndNumberic);

		log.info("Verify CustomerID - Step 08: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify CustomerID - Step 09: Enter Customer ID textbox with value " + UserData.VerifyData.specialAndNumeric);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.specialAndNumeric);

		log.info(
				"Verify CustomerID - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"),
				"Special characters are not allowed");

		log.info("Verify CustomerID - Step 11: Enter Customer ID textbox with value " + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.special);

		log.info(
				"Verify CustomerID - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"),
				"Special characters are not allowed");

		log.info("Verify CustomerID - Step 13: Enter Customer ID textbox with value " + UserData.VerifyData.character);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.character);

		log.info("Verify CustomerID - Step 14: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

	}

	@Test
	public void Edit_Customer_02_Verify_Address_Field() {	
		log.info("Verify Address - Step 01: Enter Customer ID with value: " + UserData.Customer.cusID);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.Customer.cusID);

		log.info("Verify Address - Step 02: Click to Submit in Edit Customer Form");
		customerPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Verify Address - Step 03: Click to Address Textarea");
		customerPage.clickToAddressTextarea();

		log.info("Verify Address - Step 04: Press Tab key in Address Textarea");
		customerPage.sendTabKeyToAddressTextarea();

		log.info("Verify Address - Step 05: Verify an error message 'Address Field must not be blank' is displayed");
		verifyEquals(customerPage.getCustomerErrorMessageByTextarea(), "Address Field must not be blank");

	}

	@Test
	public void Edit_Customer_03_Verify_City_Field() {
		log.info("Verify City - Step 01: Click to City textbox");
		customerPage.clickToTextboxByName(driver, "city");

		log.info("Verify City - Step 02: Press Tab key in City textbox");
		customerPage.sendTabKeyToTextboxByName(driver, "city");

		log.info("Verify City - Step 03: Verify an error message 'City Field must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "City Field must not be blank");
		
		log.info("Verify City - Step 05: Enter City textbox with value " + UserData.VerifyData.numeric);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.numeric);

		log.info("Verify City - Step 06: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");
		
		log.info("Verify City - Step 07: Enter City textbox with value " + UserData.VerifyData.numericAndCharacter);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.numericAndCharacter);

		log.info("Verify City - Step 08: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");
		
		log.info("Verify City - Step 09: Enter City textbox with value " + UserData.VerifyData.specialAndCharacter);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.specialAndCharacter);

		log.info("Verify City - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Special characters are not allowed");
		
		log.info("Verify City - Step 11: Enter City textbox with value " + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "city", UserData.VerifyData.special);

		log.info("Verify City - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_Customer_04_Verify_State_Field() {
		log.info("Verify State - Step 01: Click to State textbox");
		customerPage.clickToTextboxByName(driver, "state");

		log.info("Verify State - Step 02: Press Tab key in State textbox");
		customerPage.sendTabKeyToTextboxByName(driver, "state");

		log.info("Verify State - Step 03: Verify an error message 'State must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "State must not be blank");
		
		log.info("Verify State - Step 05: Enter State textbox with value " + UserData.VerifyData.numeric);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.numeric);

		log.info("Verify State - Step 06: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");
		
		log.info("Verify State - Step 07: Enter State textbox with value " + UserData.VerifyData.numericAndCharacter);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.numericAndCharacter);

		log.info("Verify State - Step 08: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");
		
		log.info("Verify State - Step 09: Enter State textbox with value " + UserData.VerifyData.specialAndCharacter);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.specialAndCharacter);

		log.info("Verify State - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Special characters are not allowed");
		
		log.info("Verify State - Step 11: Enter City State with value " + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "state", UserData.VerifyData.special);

		log.info("Verify State - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Special characters are not allowed");	
	}
	
	@Test
	public void Edit_Customer_05_Verify_PIN_Field() {
		log.info("Verify PIN - Step 01: Click to PIN textbox");
		customerPage.clickToTextboxByName(driver, "pinno");

		log.info("Verify PIN - Step 02: Press Tab key in PIN textbox");
		customerPage.sendTabKeyToTextboxByName(driver, "pinno");

		log.info("Verify PIN - Step 03: Verify an error message 'PIN Code must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must not be blank");
		
		log.info("Verify PIN - Step 05: Enter PIN textbox with value '1234'" + UserData.VerifyData.digitLessThanSix);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.digitLessThanSix);

		log.info("Verify PIN - Step 06: Verify an error message 'PIN Code must have 6 Digits' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must have 6 Digits");
		
		log.info("Verify PIN - Step 07: Enter PIN textbox with value " + UserData.VerifyData.numericAndCharacter);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.numericAndCharacter);

		log.info("Verify PIN - Step 08: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "Characters are not allowed");
		
		log.info("Verify PIN - Step 09: Enter PIN textbox with value " + UserData.VerifyData.specialAndCharacter);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.specialAndCharacter);

		log.info("Verify PIN - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "Special characters are not allowed");
		
		log.info("Verify PIN - Step 11: Enter PIN State with value " + UserData.VerifyData.special);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.VerifyData.special);

		log.info("Verify PIN - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "Special characters are not allowed");	
	}
	
	@Test
	public void Edit_Customer_06_Verify_Telephone_Field() {
		log.info("Verify Telephone - Step 01: Click to Telephone textbox");
		customerPage.clickToTextboxByName(driver, "telephoneno");

		log.info("Verify Telephone - Step 02: Press Tab key in Telephone textbox");
		customerPage.sendTabKeyToTextboxByName(driver, "telephoneno");

		log.info("Verify Telephone - Step 03: Verify an error message 'Mobile no must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"), "Mobile no must not be blank");
		
		log.info("Verify Telephone - Step 04: Enter Telephone textbox with value " + UserData.VerifyData.numericAndSpecial);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.VerifyData.numericAndSpecial);

		log.info("Verify Telephone - Step 05: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"), "Special characters are not allowed");
		
		log.info("Verify Telephone - Step 06: Enter Telephone State with value " + UserData.VerifyData.specialAndNumeric);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.VerifyData.specialAndNumeric);

		log.info("Verify Telephone - Step 07: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_Customer_07_Verify_Email_Field() {
		log.info("Verify Email - Step 01: Click to Email textbox");
		customerPage.clickToTextboxByName(driver, "emailid");

		log.info("Verify Email - Step 02: Press Tab key in Email textbox");
		customerPage.sendTabKeyToTextboxByName(driver, "emailid");

		log.info("Verify Email - Step 03: Verify an error message 'Email-ID must not be blank' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID must not be blank");
		
		log.info("Verify Email - Step 04: Enter Email textbox with value " + UserData.VerifyData.emailNotDotCom);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.emailNotDotCom);

		log.info("Verify Email - Step 05: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
		
		log.info("Verify Email - Step 04: Enter Email textbox with value " + UserData.VerifyData.emailOnlyName);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.emailOnlyName);

		log.info("Verify Email - Step 05: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
		
		log.info("Verify Email - Step 06: Enter Email textbox with value " + UserData.VerifyData.emailNotGmailDotCom);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.emailNotGmailDotCom);

		log.info("Verify Email - Step 07: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
		
		log.info("Verify Email - Step 08: Enter Email textbox with value " + UserData.VerifyData.emailNotFormat);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.VerifyData.emailNotFormat);

		log.info("Verify Email - Step 09: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
	}

	@Test
	public void Edit_Customer_08_Edit_With_Valid_Value() {
//		log.info("Edit Customer - Step 01: Click to Edit Customer in Navigate Menu bar");
//		customerPage = (CustomerPageObject) homePage.clickToMenuNavByName(driver, "Edit Customer");
//
//		log.info("Edit Customer - Step 02: Enter Customer ID with value: " + Customer_01_New_Customer.cusID);
//		customerPage.inputToTextboxByName(driver, "cusid", Customer_01_New_Customer.cusID);
//
//		log.info("Edit Customer - Step 03: Click to Submit in Edit Customer Form");
//		customerPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Edit Customer - Step 04: Enter Edit Customer Address with value " + UserData.Customer.editCusAddress);
		customerPage.inputToAddressTextarea(UserData.Customer.editCusAddress);

		log.info("Edit Customer - Step 05: Enter Edit Customer City with value " + UserData.Customer.editCusCity);
		customerPage.inputToTextboxByName(driver, "city", UserData.Customer.editCusCity);

		log.info("Edit Customer - Step 06: Enter Edit Customer State with value " + UserData.Customer.editCusState);
		customerPage.inputToTextboxByName(driver, "state", UserData.Customer.editCusState);

		log.info("Edit Customer - Step 07: Enter Edit Customer PIN with value " + UserData.Customer.editCusPIN);
		customerPage.inputToTextboxByName(driver, "pinno", UserData.Customer.editCusPIN);

		log.info("Edit Customer - Step 08: Enter Edit Customer PIN with value " + UserData.Customer.editCusMobilePhone);
		customerPage.inputToTextboxByName(driver, "telephoneno", UserData.Customer.editCusMobilePhone);

		log.info("Edit Customer - Step 09: Enter Edit Customer PIN with value " + UserData.Customer.editCusEmail);
		customerPage.inputToTextboxByName(driver, "emailid", UserData.Customer.editCusEmail);

		log.info("Edit Customer - Step 10: Click to Submit in Edit Customer");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("Edit Customer - Step 11: Verify Customer Edited Successfully Message");
		verifyEquals(customerPage.getHeadingMessage(driver), "Customer details updated Successfully!!!");

		log.info("Edit Customer - Step 12: Verify Customer ID with value " + UserData.Customer.cusID);
		verifyEquals(customerPage.getCustomerInfoByText("Customer ID"), UserData.Customer.cusID);

		log.info("Edit Customer - Step 13: Verify Customer Name with value " + UserData.Customer.cusName);
		verifyEquals(customerPage.getCustomerInfoByText("Customer Name"), UserData.Customer.cusName);

		log.info("Edit Customer - Step 14: Verify Customer Gender with value Female");
		verifyEquals(customerPage.getCustomerInfoByText("Gender"), "female");

		log.info("Edit Customer - Step 15: Verify Customer Birthdate with value "
				+ UserData.Customer.cusDateOfBirthNew);
		verifyEquals(customerPage.getCustomerInfoByText("Birthdate"), UserData.Customer.cusDateOfBirthNew);

		log.info("Edit Customer - Step 16: Verify Customer Address with value " + UserData.Customer.editCusAddress);
		verifyEquals(customerPage.getCustomerInfoByText("Address"), UserData.Customer.editCusAddress);

		log.info("Edit Customer - Step 17: Verify Customer State with value " + UserData.Customer.editCusState);
		verifyEquals(customerPage.getCustomerInfoByText("State"), UserData.Customer.editCusState);

		log.info("Edit Customer - Step 18: Verify Customer Pin with value " + UserData.Customer.editCusPIN);
		verifyEquals(customerPage.getCustomerInfoByText("Pin"), UserData.Customer.editCusPIN);

		log.info("Edit Customer - Step 19: Verify Customer Mobile No with value " + UserData.Customer.editCusMobilePhone);
		verifyEquals(customerPage.getCustomerInfoByText("Mobile No."), UserData.Customer.editCusMobilePhone);

		log.info("Edit Customer - Step 20: Verify Customer Email with value " + UserData.Customer.editCusEmail);
		verifyEquals(customerPage.getCustomerInfoByText("Email"), UserData.Customer.editCusEmail);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	CustomerPageObject customerPage;

}
