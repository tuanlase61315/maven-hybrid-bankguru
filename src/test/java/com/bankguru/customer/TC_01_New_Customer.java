package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.FakerConfig;

public class TC_01_New_Customer {
	WebDriver driver;
	FakerConfig faker;
	String cusName, cusDateOfBirth, cusAddress, cusCity, cusState, cusPin, cusMobileNumber, cusEmail, cusPassword;

	@BeforeClass
	public void beforeClass() {
		cusName = "tuan";
		cusDateOfBirth = "03/29/1993";
		cusAddress = "172 tay ninh";
		cusCity = "tay ninh";
		cusState = "abc";
		cusPin = "123456";
		cusMobileNumber = "0399992426";
		cusEmail = "leanhtuan1@gmail.com";
		cusPassword = "123123";
	}
	
	@Test
	public void NewCustomer_01_New_Customer_With_Valid_Data() {
		
	}

}
