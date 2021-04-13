package com.bankguru.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.bankguru.testdata.UserData;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;

public class Common_01_Register extends BaseTest{
	WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void beforeTest(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		log.info("Register - Step 01: Open Login Page");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		UserData.Register.loginPageUrl = loginPage.getCurrentUrl(driver);

		log.info("Register - Step 02: Click here link");
		loginPage.clickHereLink();

		log.info("Register - Step 03: Enter Email Textbox");
		loginPage.inputToTextboxByName(driver, "emailid", UserData.Register.emailRegister);

		log.info("Register - Step 04: Click Submit button");
		loginPage.clickToButtonByValue(driver, "Submit");
		UserData.Login.userID = loginPage.getUserIDFromTable();
		UserData.Login.password = loginPage.getPasswordFromTable();
		
		driver.quit();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
}
