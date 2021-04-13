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

public class Customer_03_Delete_Customer extends BaseTest{
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
	public void Delete_Customer_01_Verify_Customer_ID() {
		log.info("Verify CustomerID - Step 01: Click to Delete Customer in Navigation Menu bar");
		customerPage = (CustomerPageObject) homePage.clickToMenuNavByName(driver, "Delete Customer");
		
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

		log.info("Verify CustomerID - Step 09: Enter Customer ID textbox with value '123!@#'" + UserData.VerifyData.numericAndSpecial);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.numericAndSpecial);

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

		log.info("Verify CustomerID - Step 13: Enter Customer ID textbox with value 'xyz'" + UserData.VerifyData.character);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.VerifyData.character);

		log.info("Verify CustomerID - Step 14: Verify an error message 'Characters are not allowed' is displayed");
		verifyEquals(customerPage.getItemErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");
	}
	
	@Test
	public void Delete_Customer_02_Delete_Customer_With_Valid_Value() {
		log.info("Delete Customer - Step 01: Enter Customer ID textbox with value " + UserData.Customer.cusID);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.Customer.cusID);
		
		log.info("Delete Customer - Step 02: Click to Submit button in Delete Customer Form");
		customerPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Delete Customer - Step 03: Verify Alert Message 'Do you really want to delete this Customer?' is displayed");
		verifyEquals(customerPage.getTextAlert(driver), "Do you really want to delete this Customer?");
		
		log.info("Delete Customer - Step 04: Click to Ok button in Alert");
		customerPage.acceptAlert(driver);
		
		log.info("Delete Customer - Step 05: Verify Alert Message 'Customer deleted Successfully' is displayed");
		verifyEquals(customerPage.getTextAlert(driver), "Customer deleted Successfully");
		
		log.info("Delete Customer - Step 06: Click to Ok button in Alert");
		homePage = customerPage.acceptDeleteCustomerAlert();
			
		log.info("Delete Customer - Step 07: Click to Edit Customer in Navigation Menu bar");
		customerPage = (CustomerPageObject) homePage.clickToMenuNavByName(driver, "Edit Customer");
		
		log.info("Delete Customer - Step 08: Enter Customer ID textbox with value " + UserData.Customer.cusID);
		customerPage.inputToTextboxByName(driver, "cusid", UserData.Customer.cusID);
		
		log.info("Delete Customer - Step 09: Click to Submit button in Edit Customer Form");
		customerPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Delete Customer - Step 10: Verify Alert Message 'Customer does not exist!!' is displayed");
		verifyEquals(customerPage.getTextAlert(driver), "Customer does not exist!!");
		
		log.info("Delete Customer - Step 11: Click to Ok button in Alert");
		homePage = customerPage.acceptDeleteCustomerAlert();
		
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	CustomerPageObject customerPage;


}
