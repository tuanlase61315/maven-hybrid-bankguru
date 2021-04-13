package pageObjects;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
	
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static AccountPageObject getAccountPageObject(WebDriver driver) {
		return new AccountPageObject(driver);
	}
	
	public static CustomerPageObject getcCustomerPageObject(WebDriver driver) {
		return new CustomerPageObject(driver);
	}
	
	public static PaymentPageObject getPaymentPageObject(WebDriver driver) {
		return new PaymentPageObject(driver);
	}
	
	public static UserPageObject getUserPageObject(WebDriver driver) {
		return new UserPageObject(driver);
	}
}
