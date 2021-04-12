package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.common.Common_01_Register;
import com.bankguru.customer.Customer_01_New_Customer;
import com.bankguru.customer.Customer_02_Edit_Customer;

import commons.BaseTest;
import pageObjects.AccountPageObject;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;

public class Account_01_New_Account extends BaseTest {
	WebDriver driver;
	String initialDesposit, accountType, accountID;

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
		
		initialDesposit = "50000";
		accountType = "Savings";
	}

	@Test
	public void New_Account_01_Verify_Customer_ID() {
		log.info("Verify Customer ID In New Account - Step 01: Click New Account in Navigation Menu Bar");
		accountPage = (AccountPageObject) homePage.clickToMenuNavByName(driver, "New Account");

		log.info("Verify Customer ID In New Account - Step 02: Click to Customer ID textbox");
		accountPage.clickToTextboxByName(driver, "cusid");

		log.info("Verify Customer ID In New Account - Step 03: Press Tab key in Customer ID textbox");
		accountPage.sendTabKeyToTextboxByName(driver, "cusid");

		log.info(
				"Verify Customer ID In New Account - Step 04: Verify an error message 'Customer ID is required' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "cusid"), "Customer ID is required");

		log.info("Verify Customer ID In New Account - Step 05: Enter Customer ID textbox with value '1234Acc'");
		accountPage.inputToTextboxByName(driver, "cusid", "1234Acc");

		log.info(
				"Verify Customer ID In New Account - Step 06: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 07: Enter Customer ID textbox with value 'Acc123'");
		accountPage.inputToTextboxByName(driver, "cusid", "Acc123");

		log.info(
				"Verify Customer ID In New Account - Step 08: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 09: Enter Customer ID textbox with value '123!@#'");
		accountPage.inputToTextboxByName(driver, "cusid", "123!@#");

		log.info(
				"Verify Customer ID In New Account - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "cusid"),
				"Special characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 11: Enter Customer ID textbox with value '!@#'");
		accountPage.inputToTextboxByName(driver, "cusid", "!@#");

		log.info(
				"Verify Customer ID In New Account - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "cusid"),
				"Special characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 13: Enter Customer ID textbox with value 'xyz'");
		accountPage.inputToTextboxByName(driver, "cusid", "xyz");

		log.info(
				"Verify Customer ID In New Account - Step 14: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 14: Enter Customer ID textbox with value '123 123'");
		accountPage.inputToTextboxByName(driver, "cusid", "123 123");

		log.info(
				"Verify Customer ID In New Account - Step 15: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 16: Enter Customer ID textbox with value ' '");
		accountPage.inputToTextboxByName(driver, "cusid", " ");

		log.info(
				"Verify Customer ID In New Account - Step 17: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "cusid"),
				"First character can not have space");
	}

	@Test
	public void New_Account_02_Verify_Intial_Deposit() {
		log.info("Verify Intial Deposit In New Account - Step 01: Click to Intial Deposit textbox");
		accountPage.clickToTextboxByName(driver, "inideposit");

		log.info("Verify Intial Deposit In New Account - Step 02: Press Tab key in Intial Deposit textbox");
		accountPage.sendTabKeyToTextboxByName(driver, "inideposit");

		log.info(
				"Verify Intial Deposit In New Account - Step 03: Verify an error message 'Initial Deposit must not be blank' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "inideposit"),
				"Initial Deposit must not be blank");
		
		log.info("Verify Intial Deposit In New Account - Step 04: Enter Intial Deposit textbox with value '123ABC'");
		accountPage.inputToTextboxByName(driver, "inideposit", "123ABC");

		log.info(
				"Verify Intial Deposit In New Account - Step 05: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "inideposit"), "Characters are not allowed");

		
		log.info("Verify Intial Deposit In New Account - Step 06: Enter Intial Deposit textbox with value 'ABC123'");
		accountPage.inputToTextboxByName(driver, "inideposit", "ABC123");

		log.info(
				"Verify Intial Deposit In New Account - Step 07: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "inideposit"), "Characters are not allowed");

		log.info("Verify Intial Deposit In New Account - Step 08: Enter Intial Deposit textbox with value '123!@#");
		accountPage.inputToTextboxByName(driver, "inideposit", "123!@#");

		log.info(
				"Verify Intial Deposit In New Account - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "inideposit"), "Special characters are not allowed");
		
		log.info("Verify Intial Deposit In New Account - Step 10: Enter Intial Deposit textbox with value '!@#123'");
		accountPage.inputToTextboxByName(driver, "inideposit", "!@#123");

		log.info(
				"Verify Intial Deposit In New Account - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "inideposit"), "Special characters are not allowed");
		
		log.info("Verify Intial Deposit In New Account - Step 12: Enter Intial Deposit textbox with value '123 123'");
		accountPage.inputToTextboxByName(driver, "inideposit", "123 123");

		log.info(
				"Verify Intial Deposit In New Account - Step 13: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "inideposit"), "Characters are not allowed");
		
		log.info("Verify Intial Deposit In New Account - Step 14: Enter Intial Deposit textbox with value ' '");
		accountPage.inputToTextboxByName(driver, "inideposit", " ");

		log.info(
				"Verify Intial Deposit In New Account - Step 15: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(accountPage.getCustomerErrorMessageByTextboxName(driver, "inideposit"), "First character can not have space");			
	}

	@Test
	public void New_Account_03_New_Account_With_Valid_Data() {
		log.info("New Account - Step 01: Click to Reset button in Add New Account form");
		accountPage.clickToButtonByValue(driver, "reset");
		
		log.info("New Account - Step 02: Enter Customer ID textbox with value " + Customer_01_New_Customer.cusID);
		accountPage.inputToTextboxByName(driver, "cusid", Customer_01_New_Customer.cusID);
		
		log.info("New Account - Step 03: Select Account Type dropbox with value " + accountType);
		accountPage.selectItemInAccountTypeDropbox(accountType);
		
		log.info("New Account - Step 04: Enter Initial Deposit textbox with value " + initialDesposit);
		accountPage.inputToTextboxByName(driver, "inideposit", initialDesposit);
		
		log.info("New Account - Step 05: Click to Submit button in Add New Account form");
		accountPage.clickToButtonByValue(driver, "submit");

		log.info("New Account - Step 06: Verify Account Registered Successfully Message");
		verifyEquals(accountPage.getRegisteredAndEditedSuccessMessage(driver), "Account Generated Successfully!!!");
		
		log.info("New Account - Step 07: Verify Customer ID with value " + Customer_01_New_Customer.cusID);
		verifyEquals(accountPage.getAccountInfoByText("Customer ID"), Customer_01_New_Customer.cusID);
				
		log.info("New Account - Step 08: Verify Customer ID with value " + Customer_01_New_Customer.cusName);
		verifyEquals(accountPage.getAccountInfoByText("Customer Name"), Customer_01_New_Customer.cusName);	
		
		log.info("New Account - Step 09: Verify Customer Email with value " + Customer_02_Edit_Customer.editCusEmail);
		verifyEquals(accountPage.getAccountInfoByText("Email"), Customer_02_Edit_Customer.editCusEmail);
		
		log.info("New Account - Step 10: Verify Account Type with value " + accountType);
		verifyEquals(accountPage.getAccountInfoByText("Account Type"), accountType);
		
		log.info("New Account - Step 11: Verify Current Amount with value " + initialDesposit);
		verifyEquals(accountPage.getAccountInfoByText("Current Amount"), initialDesposit);
		
		accountID = accountPage.getAccountInfoByText("Account ID");
		
		
	
	}
	
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	CustomerPageObject customerPage;
	AccountPageObject accountPage;
}
