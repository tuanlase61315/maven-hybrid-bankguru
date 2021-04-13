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

public class Account_03_Delete_Account extends BaseTest{
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
	public void Delete_Account_01_Verify_Account_Number() {
		log.info("Verify Account Number - Step 01: Click to Edit Account in Navigation Menu Bar");
		accountPage = (AccountPageObject) homePage.clickToMenuNavByName(driver, "Delete Account");
		
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
	public void Delete_Account_02_Delete_Account() {
		log.info("Delete Account - Step 01: Enter Acount No textbox with value: " + UserData.Account.accountID);
		accountPage.inputToTextboxByName(driver, "accountno", UserData.Account.accountID);
		
		log.info("Delete Account - Step 02: Click to Submit button");
		accountPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Delete Customer - Step 03: Verify Alert Message 'Do you really want to delete this Account?' is displayed");
		verifyEquals(accountPage.getTextAlert(driver), "Do you really want to delete this Account?");
		
		log.info("Delete Customer - Step 04: Click to Ok button in Alert");
		accountPage.acceptAlert(driver);
		
		log.info("Delete Customer - Step 05: Verify Alert Message 'Account Deleted Sucessfully' is displayed");
		verifyEquals(accountPage.getTextAlert(driver), "Account Deleted Sucessfully");
		
		log.info("Delete Customer - Step 06: Click to Ok button in Alert");
		homePage = accountPage.acceptDeleteAccountAlert();
			
		log.info("Delete Customer - Step 07: Click to Edit Customer in Navigation Menu bar");
		accountPage = (AccountPageObject) homePage.clickToMenuNavByName(driver, "Edit Account");
		
		log.info("Delete Customer - Step 08: Enter Customer ID textbox with value " + UserData.Account.accountID);
		accountPage.inputToTextboxByName(driver, "accountno", UserData.Account.accountID);
		
		log.info("Delete Customer - Step 09: Click to Submit button in Edit Customer Form");
		accountPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Delete Customer - Step 10: Verify Alert Message 'Account does not exist' is displayed");
		verifyEquals(accountPage.getTextAlert(driver), "Account does not exist");
		
		log.info("Delete Customer - Step 11: Click to Ok button in Alert");
		accountPage.acceptAlert(driver);
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
