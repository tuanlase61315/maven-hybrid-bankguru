package com.bankguru.login;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import utilities.FakerConfig;

public class TC_01_Login_And_Register extends BaseTest {
	WebDriver driver;
	Date day01;
	FakerConfig faker;
	
	@BeforeClass
	public void BeforeClass() {
		day01 = faker.getDate();
	}
	
	@Test
	public void Login_01_Register_By_Email() {

		System.out.println(day01);
		
		
		
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
	
	HomePageObject homePage;
	LoginPageObject loginPage;

}
