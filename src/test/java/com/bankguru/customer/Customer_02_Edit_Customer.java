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

public class Customer_02_Edit_Customer extends BaseTest {
	WebDriver driver;
	public static String editCusAddress, editCusCity, editCusState, editCusPIN, editCusMobilePhone, editCusEmail;

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

		editCusAddress = "cmt";
		editCusCity = "hanoi";
		editCusState = "alo";
		editCusPIN = "222333";
		editCusMobilePhone = "333222444";
		editCusEmail = homePage.generateEmail();
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

		log.info("Verify CustomerID - Step 05: Enter Customer ID textbox with value '1234Acc'");
		customerPage.inputToTextboxByName(driver, "cusid", "1234Acc");

		log.info("Verify CustomerID - Step 06: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify CustomerID - Step 07: Enter Customer ID textbox with value 'Acc123'");
		customerPage.inputToTextboxByName(driver, "cusid", "Acc123");

		log.info("Verify CustomerID - Step 08: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify CustomerID - Step 09: Enter Customer ID textbox with value '123!@#'");
		customerPage.inputToTextboxByName(driver, "cusid", "123!@#");

		log.info(
				"Verify CustomerID - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"),
				"Special characters are not allowed");

		log.info("Verify CustomerID - Step 11: Enter Customer ID textbox with value '!@#'");
		customerPage.inputToTextboxByName(driver, "cusid", "!@#");

		log.info(
				"Verify CustomerID - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"),
				"Special characters are not allowed");

		log.info("Verify CustomerID - Step 13: Enter Customer ID textbox with value 'xyz'");
		customerPage.inputToTextboxByName(driver, "cusid", "xyz");

		log.info("Verify CustomerID - Step 14: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

	}

	@Test
	public void Edit_Customer_02_Verify_Address_Field() {	
		log.info("Verify Address - Step 01: Enter Customer ID with value: " + Customer_01_New_Customer.cusID);
		customerPage.inputToTextboxByName(driver, "cusid", Customer_01_New_Customer.cusID);

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
		
		log.info("Verify City - Step 05: Enter City textbox with value '1234'");
		customerPage.inputToTextboxByName(driver, "city", "1234");

		log.info("Verify City - Step 06: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");
		
		log.info("Verify City - Step 07: Enter City textbox with value 'city1234'");
		customerPage.inputToTextboxByName(driver, "city", "city1234");

		log.info("Verify City - Step 08: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");
		
		log.info("Verify City - Step 09: Enter City textbox with value 'city!@#'");
		customerPage.inputToTextboxByName(driver, "city", "city!@#");

		log.info("Verify City - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "city"), "Special characters are not allowed");
		
		log.info("Verify City - Step 11: Enter City textbox with value '!@#'");
		customerPage.inputToTextboxByName(driver, "city", "!@#");

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
		
		log.info("Verify State - Step 05: Enter State textbox with value '1234'");
		customerPage.inputToTextboxByName(driver, "state", "1234");

		log.info("Verify State - Step 06: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");
		
		log.info("Verify State - Step 07: Enter State textbox with value 'state1234'");
		customerPage.inputToTextboxByName(driver, "state", "state1234");

		log.info("Verify State - Step 08: Verify an error message 'Numbers are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");
		
		log.info("Verify State - Step 09: Enter State textbox with value 'state!@#'");
		customerPage.inputToTextboxByName(driver, "state", "state!@#");

		log.info("Verify State - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "state"), "Special characters are not allowed");
		
		log.info("Verify State - Step 11: Enter City State with value '!@#'");
		customerPage.inputToTextboxByName(driver, "state", "!@#");

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
		
		log.info("Verify PIN - Step 05: Enter PIN textbox with value '1234'");
		customerPage.inputToTextboxByName(driver, "pinno", "1234");

		log.info("Verify PIN - Step 06: Verify an error message 'PIN Code must have 6 Digits' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "PIN Code must have 6 Digits");
		
		log.info("Verify PIN - Step 07: Enter PIN textbox with value 'PIN234'");
		customerPage.inputToTextboxByName(driver, "pinno", "PIN234");

		log.info("Verify PIN - Step 08: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "Characters are not allowed");
		
		log.info("Verify PIN - Step 09: Enter PIN textbox with value 'pin!@#'");
		customerPage.inputToTextboxByName(driver, "pinno", "pin!@#");

		log.info("Verify PIN - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "pinno"), "Special characters are not allowed");
		
		log.info("Verify PIN - Step 11: Enter PIN State with value '!@#'");
		customerPage.inputToTextboxByName(driver, "pinno", "!@#");

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
		
		log.info("Verify Telephone - Step 04: Enter Telephone textbox with value '123123!@12'");
		customerPage.inputToTextboxByName(driver, "telephoneno", "123123!@12");

		log.info("Verify Telephone - Step 05: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"), "Special characters are not allowed");
		
		log.info("Verify Telephone - Step 06: Enter Telephone State with value '!@#123123213'");
		customerPage.inputToTextboxByName(driver, "telephoneno", "!@#123123213");

		log.info("Verify Telephone - Step 07: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "telephoneno"), "Special characters are not allowed");
		
		log.info("Verify Telephone - Step 08: Enter Telephone State with value '123123213!@#'");
		customerPage.inputToTextboxByName(driver, "telephoneno", "123123213!@#");

		log.info("Verify Telephone - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
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
		
		log.info("Verify Email - Step 04: Enter Email textbox with value 'guru99@gmail'");
		customerPage.inputToTextboxByName(driver, "emailid", "guru99@gmail");

		log.info("Verify Email - Step 05: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
		
		log.info("Verify Email - Step 04: Enter Email textbox with value 'guru99'");
		customerPage.inputToTextboxByName(driver, "emailid", "guru99");

		log.info("Verify Email - Step 05: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
		
		log.info("Verify Email - Step 06: Enter Email textbox with value 'guru99@'");
		customerPage.inputToTextboxByName(driver, "emailid", "guru99@");

		log.info("Verify Email - Step 07: Verify an error message 'Email-ID is not valid' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
		
		log.info("Verify Email - Step 08: Enter Email textbox with value 'guru99gmail.com'");
		customerPage.inputToTextboxByName(driver, "emailid", "guru99gmail.com");

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
		
		log.info("Edit Customer - Step 04: Enter Edit Customer Address with value " + editCusAddress);
		customerPage.inputToAddressTextarea(editCusAddress);

		log.info("Edit Customer - Step 05: Enter Edit Customer City with value " + editCusCity);
		customerPage.inputToTextboxByName(driver, "city", editCusCity);

		log.info("Edit Customer - Step 06: Enter Edit Customer State with value " + editCusState);
		customerPage.inputToTextboxByName(driver, "state", editCusState);

		log.info("Edit Customer - Step 07: Enter Edit Customer PIN with value " + editCusPIN);
		customerPage.inputToTextboxByName(driver, "pinno", editCusPIN);

		log.info("Edit Customer - Step 08: Enter Edit Customer PIN with value " + editCusMobilePhone);
		customerPage.inputToTextboxByName(driver, "telephoneno", editCusMobilePhone);

		log.info("Edit Customer - Step 09: Enter Edit Customer PIN with value " + editCusEmail);
		customerPage.inputToTextboxByName(driver, "emailid", editCusEmail);

		log.info("Edit Customer - Step 10: Click to Submit in Edit Customer");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("Edit Customer - Step 11: Verify Customer Edited Successfully Message");
		verifyEquals(customerPage.getRegisteredAndEditedSuccessMessage(driver), "Customer details updated Successfully!!!");

		log.info("Edit Customer - Step 12: Verify Customer ID with value " + Customer_01_New_Customer.cusID);
		verifyEquals(customerPage.getCustomerInfoByText("Customer ID"), Customer_01_New_Customer.cusID);

		log.info("Edit Customer - Step 13: Verify Customer Name with value " + Customer_01_New_Customer.cusName);
		verifyEquals(customerPage.getCustomerInfoByText("Customer Name"), Customer_01_New_Customer.cusName);

		log.info("Edit Customer - Step 14: Verify Customer Gender with value Female");
		verifyEquals(customerPage.getCustomerInfoByText("Gender"), "female");

		log.info("Edit Customer - Step 15: Verify Customer Birthdate with value "
				+ Customer_01_New_Customer.cusDateOfBirthNew);
		verifyEquals(customerPage.getCustomerInfoByText("Birthdate"), Customer_01_New_Customer.cusDateOfBirthNew);

		log.info("Edit Customer - Step 16: Verify Customer Address with value " + editCusAddress);
		verifyEquals(customerPage.getCustomerInfoByText("Address"), editCusAddress);

		log.info("Edit Customer - Step 17: Verify Customer State with value " + editCusState);
		verifyEquals(customerPage.getCustomerInfoByText("State"), editCusState);

		log.info("Edit Customer - Step 18: Verify Customer Pin with value " + editCusPIN);
		verifyEquals(customerPage.getCustomerInfoByText("Pin"), editCusPIN);

		log.info("Edit Customer - Step 19: Verify Customer Mobile No with value " + editCusMobilePhone);
		verifyEquals(customerPage.getCustomerInfoByText("Mobile No."), editCusMobilePhone);

		log.info("Edit Customer - Step 20: Verify Customer Email with value " + editCusEmail);
		verifyEquals(customerPage.getCustomerInfoByText("Email"), editCusEmail);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	CustomerPageObject customerPage;

}
