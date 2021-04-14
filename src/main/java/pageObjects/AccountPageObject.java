package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.AccountPageUI;
import pageUIs.CustomerPageUI;

public class AccountPageObject extends BasePage{
	private WebDriver driver;

	public AccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInAccountTypeDropbox(String itemText) {
		waitForElementClickable(driver, AccountPageUI.ACCOUNT_TYPE_DROPBOX);
		selectItemInDropdownByText(driver, AccountPageUI.ACCOUNT_TYPE_DROPBOX, itemText);		
	}
	
	public String getAccountInfoByText(String itemText) {
		waitForAllElementVisible(driver, AccountPageUI.DYNAMIC_ACCOUNT_INFO_BY_TEXT, itemText);
		return getElementText(driver, AccountPageUI.DYNAMIC_ACCOUNT_INFO_BY_TEXT, itemText);
	}

	public AccountPageObject acceptAlertInEditAccount(WebDriver driver2) {
		acceptAlert(driver);
		return PageGeneratorManager.getAccountPageObject(driver);
		
	}

	public HomePageObject acceptDeleteAccountAlert() {
		acceptAlert(driver);
		return PageGeneratorManager.getHomePageObject(driver);
	}
	
	
}
