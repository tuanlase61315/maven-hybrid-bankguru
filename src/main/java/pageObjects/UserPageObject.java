package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserPageObject extends BasePage{
	private WebDriver driver;

	public UserPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPageObject acceptAlertInChangePassword() {
		acceptAlert(driver);
		return PageGeneratorManager.getLoginPage(driver);
		
	}
	
}
