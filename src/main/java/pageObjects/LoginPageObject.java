package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickHereLink() {
		waitForElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		
	}

	public String getUserIDFromTable() {
		return getTextElement(driver, LoginPageUI.USER_ID_TEXT);
		
	}

	public String getPasswordFromTable() {
		return getTextElement(driver, LoginPageUI.PASSWORD_TEXT);
		
	}
	
	
}
