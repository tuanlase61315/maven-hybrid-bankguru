package com.bankguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.testdata.UserData;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.UserPageObject;

public class User_01_Change_Password extends BaseTest {
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
	public void Change_Password_01_Verify_Old_Password() {
		log.info("Verify Old Password - Step 01: Click Change Password in Navigation Menu Bar");
		userPage = (UserPageObject) homePage.clickToMenuNavByName(driver, "Change Password");

		log.info("Verify Old Password - Step 02: Click to Old Password textbox");
		userPage.clickToTextboxByName(driver, "oldpassword");

		log.info("Verify Old Password - Step 03: Press Tab key in Old Password textbox");
		userPage.sendTabKeyToTextboxByName(driver, "oldpassword");

		log.info(
				"Verify Old Password - Step 04: Verify an error message 'Old Password must not be blank' is displayed");
		verifyEquals(userPage.getItemErrorMessageByTextboxName(driver, "oldpassword"),
				"Old Password must not be blank");
	}

	@Test
	public void Change_Password_02_Verify_New_Password() {
		log.info("Verify New Password - Step 01: Click to New Password textbox");
		userPage.clickToTextboxByName(driver, "newpassword");

		log.info("Verify New Password - Step 02: Press Tab key in New Password textbox");
		userPage.sendTabKeyToTextboxByName(driver, "newpassword");

		log.info(
				"Verify New Password - Step 03: Verify an error message 'New Password must not be blank' is displayed");
		verifyEquals(userPage.getItemErrorMessageByTextboxName(driver, "newpassword"),
				"New Password must not be blank");

		log.info("Verify New Password - Step 04: Enter to New Password textbox with value: "
				+ UserData.VerifyData.specialAndCharacter);
		userPage.inputToTextboxByName(driver, "newpassword", UserData.VerifyData.specialAndCharacter);

		log.info("Verify New Password - Step 04: Verify Error Message 'Enter at-least one numeric value' is displayed");
		verifyEquals(userPage.getItemErrorMessageByTextboxName(driver, "newpassword"),
				"Enter at-least one numeric value");

		log.info("Verify New Password - Step 05: Enter to New Password textbox with value: "
				+ UserData.VerifyData.characterAndNumberic);
		userPage.inputToTextboxByName(driver, "newpassword", UserData.VerifyData.characterAndNumberic);

		log.info(
				"Verify New Password - Step 06: Verify Error Message 'Enter at-least one special character' is displayed");
		verifyEquals(userPage.getItemErrorMessageByTextboxName(driver, "newpassword"),
				"Enter at-least one special character");

		log.info("Verify New Password - Step 07: Enter to New Password textbox with value: "
				+ UserData.VerifyData.passwordString);
		userPage.inputToTextboxByName(driver, "newpassword", UserData.VerifyData.passwordString);

		log.info("Verify New Password - Step 08: Verify Error Message 'Choose a difficult Password' is displayed");
		verifyEquals(userPage.getItemErrorMessageByTextboxName(driver, "newpassword"), "Choose a difficult Password");
	}

	@Test
	public void Change_Password_03_Verify_Confirm_Password() {
		log.info("Verify Confirm Password - Step 01: Click to Confirm Password textbox");
		userPage.clickToTextboxByName(driver, "confirmpassword");

		log.info("Verify Confirm Password - Step 02: Press Tab key in Confirm Password textbox");
		userPage.sendTabKeyToTextboxByName(driver, "confirmpassword");

		log.info(
				"Verify Confirm Password - Step 03: Verify an error message 'Confirm Password must not be blank' is displayed");
		verifyEquals(userPage.getItemErrorMessageByTextboxName(driver, "confirmpassword"),
				"Confirm Password must not be blank");
	}

	@Test
	public void Change_Password_04_Change_Password_With_Valid_Data() {
		log.info("Change Password - Step 01: Enter Old Password with value" + UserData.Login.password);
		userPage.inputToTextboxByName(driver, "oldpassword", UserData.Login.password);

		log.info("Change Password - Step 02: Enter New Password with value" + UserData.Login.newPassword);
		userPage.inputToTextboxByName(driver, "newpassword", UserData.Login.newPassword);

		log.info("Change Password - Step 03: Enter Confirm Password with value" + UserData.Login.newPassword);
		userPage.inputToTextboxByName(driver, "confirmpassword", UserData.Login.newPassword);

		log.info("Change Password - Step 04: Click Submit button");
		userPage.clickToButtonByValue(driver, "Submit");

		log.info("Change Password - Step 05: Verify Alert Message 'Password is Changed' is displayed");
		verifyEquals(userPage.getTextAlert(driver), "Password is Changed");

		log.info("Change Password - Step 06: Click to Ok button in Alert");
		loginPage = userPage.acceptAlertInChangePassword();

		log.info("Change Password - Step 07: Enter UserID in Textbox with value: " + UserData.Login.userID);
		loginPage.inputToTextboxByName(driver, "uid", UserData.Login.userID);

		log.info("Change Password - Step 08: Enter Password in Textbox with value: " + UserData.Login.newPassword);
		loginPage.inputToTextboxByName(driver, "password", UserData.Login.newPassword);

		log.info("Change Password - Step 09: Click Login button");
		loginPage.clickToButtonByValue(driver, "LOGIN");
		homePage = PageGeneratorManager.getHomePageObject(driver);

		log.info("Change Password - Step 10: Verify homepage message after login successfull");
		verifyTrue(homePage.isHomePageMessageDisplay());

	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	UserPageObject userPage;

}
