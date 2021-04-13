package com.bankguru.testdata;

import commons.BasePage;

public class UserData extends BasePage {
	public static class Login {
		public static String userID = "";
		public static String password = "";
		public static String newPassword = "test123!@#";
	}

	public static class Register {
		public static String emailRegister = generateEmail();
		public static String loginPageUrl = "";
	}

	public static class Customer {
		public static String cusName = "tuan";
		public static String cusDateOfBirth = "10/01/1993";
		public static String cusAddress = "172 tay ninh";
		public static String cusCity = "tay ninh";
		public static String cusState = "abc";
		public static String cusPin = "123456";
		public static String cusMobileNumber = "0399992426";
		public static String cusEmail = generateEmail();
		public static String cusPassword = "123123";
		public static String cusDateOfBirthNew = "";
		public static String cusID = "";
		
		public static String editCusAddress = "cmt";
		public static String editCusCity = "hanoi";
		public static String editCusState = "alo";
		public static String editCusPIN = "222333";
		public static String editCusMobilePhone = "333222444";
		public static String editCusEmail = generateEmail();
	}

	public static class Account {
		public static String initialDesposit = "50000";
		public static String accountType = "Savings";
		public static String accountID = "";
	}
	
	public static class VerifyData {
		public static String numeric = "1234";
		public static String numericAndBlank = "123 12";
		public static String numericAndCharacter = "123abc";
		public static String numericAndSpecial = "123!@#";
		
		public static String special = "!@#";
		public static String specialAndNumeric = "!@#123";
		public static String specialAndCharacter = "!@#abc";
		
		public static String character = "abc";
		public static String characterAndNumberic = "abc123";
			
		public static String emailNotDotCom = "guru99@gmail";
		public static String emailOnlyName = "guru99";
		public static String emailNotGmailDotCom = "guru99@";
		public static String emailNotCom = "guru99@gmail.";
		public static String emailNotFormat = "guru99gmail.com";
		
		public static String digitLessThanSix = "123";
		public static String firstCharacterIsSpace = " ";
		public static String passwordString = "guru99!@#Password";
	}
	
	

}
