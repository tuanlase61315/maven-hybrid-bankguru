package com.bankguru.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;

public class Common_01_Register extends BaseTest{
	WebDriver driver;
	String userID, password, email, loginPageUrl;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void beforeTest(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		log.info("Register - Step 01: Open Login Page");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPageUrl = loginPage.getCurrentUrl(driver);

		log.info("Register - Step 02: Click here link");
		loginPage.clickHereLink();

		log.info("Register - Step 03: Enter Email Textbox");
		email = loginPage.generateEmail();
		loginPage.inputToTextboxByName(driver, "emailid", email);

		log.info("Register - Step 04: Click Submit button");
		loginPage.clickToButtonByValue(driver, "Submit");

		log.info("Register - Step 05: Open Login Page");
		userID = loginPage.getUserIDFromTable();
		password = loginPage.getPasswordFromTable();
		loginPage.openPageUrl(driver, loginPageUrl);

		log.info("Register - Step 06: Enter UserID in Textbox with value: " + userID);
		loginPage.inputToTextboxByName(driver, "uid", userID);

		log.info("Register - Step 07: Enter Password in Textbox with value: " + password);
		loginPage.inputToTextboxByName(driver, "password", password);

		log.info("Register - Step 08: Click Login button");
		loginPage.clickToButtonByValue(driver, "LOGIN");
		homePage = PageGeneratorManager.getHomePageObject(driver);

		log.info("Register - Step 09: Verify homepage message after login successfull");
		verifyTrue(homePage.isHomePageMessageDisplay());
		
		
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
}
