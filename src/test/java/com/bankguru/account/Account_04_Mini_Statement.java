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

public class Account_04_Mini_Statement extends BaseTest{
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
	public void Mini_Statement_01_Verify_Account_No() {
		log.info("Verify Account Number - Step 01: Click to Edit Account in Navigation Menu Bar");
		accountPage = (AccountPageObject) homePage.clickToMenuNavByName(driver, "Mini Statement");
		
		log.info("Verify Account Number - Step 02: Click to Account No textbox");
		accountPage.clickToTextboxByName(driver, "accountno");
		
		log.info("Verify Account Number - Step 03: Press Tab keyboard");
		accountPage.sendTabKeyToTextboxByName(driver, "accountno");
		
		log.info("Verify Account Number - Step 04: Verify Error Message 'Account Number must not be blank' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "accountno"), "Account Number must not be blank");
		
		log.info("Verify Account Number - Step 05: Enter to Account No textbox with value " + UserData.VerifyData.numericAndCharacter);
		accountPage.inputToTextboxByName(driver, "accountno", UserData.VerifyData.numericAndCharacter);
		
		log.info("Verify Account Number - Step 06: Verify Error Message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "accountno"), "Characters are not allowed");
		
		log.info("Verify Account Number - Step 07: Enter to Account No textbox with value " + UserData.VerifyData.special);
		accountPage.inputToTextboxByName(driver, "accountno", UserData.VerifyData.special);
		
		log.info("Verify Account Number - Step 08: Verify Error Message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "accountno"), "Special characters are not allowed");
		
		log.info("Verify Account Number - Step 09: Enter to Account No textbox with value " + UserData.VerifyData.specialAndNumeric);
		accountPage.inputToTextboxByName(driver, "accountno", UserData.VerifyData.specialAndNumeric);
		
		log.info("Verify Account Number - Step 10: Verify Error Message 'Special characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "accountno"), "Special characters are not allowed");
		
		log.info("Verify Account Number - Step 11: Enter to Account No textbox with value " + UserData.VerifyData.numericAndBlank);
		accountPage.inputToTextboxByName(driver, "accountno", UserData.VerifyData.numericAndBlank);
		
		log.info("Verify Account Number - Step 12: Verify Error Message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "accountno"), "Characters are not allowed");
		
		log.info("Verify Account Number - Step 13: Enter to Account No textbox with value " + UserData.VerifyData.firstCharacterIsSpace);
		accountPage.inputToTextboxByName(driver, "accountno", UserData.VerifyData.firstCharacterIsSpace);
		
		log.info("Verify Account Number - Step 14: Verify Error Message 'Characters are not allowed' is displayed");
		verifyEquals(accountPage.getItemErrorMessageByTextboxName(driver, "accountno"), "Characters are not allowed");
	}
	
	@Test
	public void Mini_Statement_02_Verify_Data_Table() {
		log.info("Verify Data Table - Step 01: Enter to Account No textbox with value: " + UserData.Account.accountID);
		accountPage.inputToTextboxByName(driver, "accountno", UserData.Account.accountID);
		
		log.info("Verify Data Table - Step 02: Click to Submit button in Mini Statement Form");
		accountPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Verify Data Table - Step 03: Verify heading message 'Last Five Transaction Details for Account No: " + UserData.Account.accountID + "'is displayed");		
		verifyEquals(accountPage.getHeadingMessage(driver), "Last Five Transaction Details for Account No: " + UserData.Account.accountID + "'");
		
		log.info("Verify Data Table - Step 04: Verify Data Table");
		verifyTrue(accountPage.isInformationDisplayedAtColumnNameAndRowNumber(driver, "ministmt", "Amount", "2", UserData.Account.initialDesposit));	
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
