package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.testdata.UserData;

import commons.BaseTest;
import pageObjects.AccountPageObject;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;

public class Account_01_New_Account extends BaseTest {
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
	public void New_Account_01_Verify_Customer_ID() {
		log.info("Verify Customer ID In New Account - Step 01: Click New Account in Navigation Menu Bar");
		accountPage = (AccountPageObject) homePage.clickToMenuNavByName(driver, "New Account");

		log.info("Verify Customer ID In New Account - Step 02: Click to Customer ID textbox");
		accountPage.clickToTextboxByName(driver, "cusid");

		log.info("Verify Customer ID In New Account - Step 03: Press Tab key in Customer ID textbox");
		accountPage.sendTabKeyToTextboxByName(driver, "cusid");

		log.info(
				"Verify Customer ID In New Account - Step 04: Verify an error message 'Customer ID is required' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Customer ID is required");

		log.info("Verify Customer ID In New Account - Step 05: Enter Customer ID textbox with value " + UserData.VerifyData.numericAndCharacter);
		accountPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.numericAndCharacter);

		log.info(
				"Verify Customer ID In New Account - Step 06: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 07: Enter Customer ID textbox with value " + UserData.VerifyData.characterAndNumberic);
		accountPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.characterAndNumberic);

		log.info(
				"Verify Customer ID In New Account - Step 08: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 09: Enter Customer ID textbox with value " + UserData.VerifyData.numericAndSpecial);
		accountPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.numericAndSpecial);

		log.info(
				"Verify Customer ID In New Account - Step 10: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "cusid"),
				"Special characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 11: Enter Customer ID textbox with value " + UserData.VerifyData.special);
		accountPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.special);

		log.info(
				"Verify Customer ID In New Account - Step 12: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "cusid"),
				"Special characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 13: Enter Customer ID textbox with value " + UserData.VerifyData.character);
		accountPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.character);

		log.info(
				"Verify Customer ID In New Account - Step 14: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 14: Enter Customer ID textbox with value " + UserData.VerifyData.numericAndBlank);
		accountPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.numericAndBlank);

		log.info(
				"Verify Customer ID In New Account - Step 15: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");

		log.info("Verify Customer ID In New Account - Step 16: Enter Customer ID textbox with First character have space " + UserData.VerifyData.firstCharacterIsSpace);
		accountPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.firstCharacterIsSpace);

		log.info(
				"Verify Customer ID In New Account - Step 17: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "cusid"),
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
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "inideposit"),
				"Initial Deposit must not be blank");
		
		log.info("Verify Intial Deposit In New Account - Step 04: Enter Intial Deposit textbox with value '123ABC'" + UserData.VerifyData.numericAndCharacter);
		accountPage.inputToTextboxByName(driver, "inideposit", UserData.VerifyData.numericAndCharacter);

		log.info(
				"Verify Intial Deposit In New Account - Step 05: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "inideposit"), "Characters are not allowed");

		
		log.info("Verify Intial Deposit In New Account - Step 06: Enter Intial Deposit textbox with value " + UserData.VerifyData.characterAndNumberic);
		accountPage.inputToTextboxByName(driver, "inideposit", UserData.VerifyData.characterAndNumberic);

		log.info(
				"Verify Intial Deposit In New Account - Step 07: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "inideposit"), "Characters are not allowed");

		log.info("Verify Intial Deposit In New Account - Step 08: Enter Intial Deposit textbox with value " + UserData.VerifyData.numericAndSpecial);
		accountPage.inputToTextboxByName(driver, "inideposit", UserData.VerifyData.numericAndSpecial);

		log.info(
				"Verify Intial Deposit In New Account - Step 09: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "inideposit"), "Special characters are not allowed");
		
		log.info("Verify Intial Deposit In New Account - Step 10: Enter Intial Deposit textbox with value " + UserData.VerifyData.specialAndNumeric);
		accountPage.inputToTextboxByName(driver, "inideposit", UserData.VerifyData.specialAndNumeric);

		log.info(
				"Verify Intial Deposit In New Account - Step 11: Verify an error message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "inideposit"), "Special characters are not allowed");
		
		log.info("Verify Intial Deposit In New Account - Step 12: Enter Intial Deposit textbox with value " + UserData.VerifyData.numericAndBlank);
		accountPage.inputToTextboxByName(driver, "inideposit", UserData.VerifyData.numericAndBlank);

		log.info(
				"Verify Intial Deposit In New Account - Step 13: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "inideposit"), "Characters are not allowed");
		
		log.info("Verify Intial Deposit In New Account - Step 14: Enter Intial Deposit textbox with First character have space " + UserData.VerifyData.firstCharacterIsSpace);
		accountPage.inputToTextboxByName(driver, "inideposit", UserData.VerifyData.firstCharacterIsSpace);

		log.info(
				"Verify Intial Deposit In New Account - Step 15: Verify an error message 'First character can not have space' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "inideposit"), "First character can not have space");			
	}

	@Test
	public void New_Account_03_New_Account_With_Valid_Data() {
		log.info("New Account - Step 01: Click to Reset button in Add New Account form");
		accountPage.clickToButtonByValue(driver, "reset");
		
		log.info("New Account - Step 02: Enter Customer ID textbox with value " + UserData.Customer.cusID);
		accountPage.inputToTextboxByName(driver, "cusid", UserData.Customer.cusID);
		
		log.info("New Account - Step 03: Select Account Type dropbox with value " + UserData.Account.accountType);
		accountPage.selectItemInAccountTypeDropbox(UserData.Account.accountType);
		
		log.info("New Account - Step 04: Enter Initial Deposit textbox with value " + UserData.Account.initialDesposit);
		accountPage.inputToTextboxByName(driver, "inideposit", UserData.Account.initialDesposit);
		
		log.info("New Account - Step 05: Click to Submit button in Add New Account form");
		accountPage.clickToButtonByValue(driver, "submit");

		log.info("New Account - Step 06: Verify Account Registered Successfully Message");
		verifyEquals(accountPage.getHeadingMessage(driver), "Account Generated Successfully!!!");
		
		log.info("New Account - Step 07: Verify Customer ID with value " + UserData.Customer.cusID);
		verifyEquals(accountPage.getAccountInfoByText("Customer ID"), UserData.Customer.cusID);
				
		log.info("New Account - Step 08: Verify Customer Name with value " + UserData.Customer.cusName);
		verifyEquals(accountPage.getAccountInfoByText("Customer Name"), UserData.Customer.cusName);	
		
		log.info("New Account - Step 09: Verify Customer Email with value " + UserData.Customer.editCusEmail);
		verifyEquals(accountPage.getAccountInfoByText("Email"), UserData.Customer.editCusEmail);
		
		log.info("New Account - Step 10: Verify Account Type with value " + UserData.Account.accountType);
		verifyEquals(accountPage.getAccountInfoByText("Account Type"), UserData.Account.accountType);
		
		log.info("New Account - Step 11: Verify Current Amount with value " + UserData.Account.initialDesposit);
		verifyEquals(accountPage.getAccountInfoByText("Current Amount"), UserData.Account.initialDesposit);
		
		UserData.Account.accountID = accountPage.getAccountInfoByText("Account ID");
		
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
