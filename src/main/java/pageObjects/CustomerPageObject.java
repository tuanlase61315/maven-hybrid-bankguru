package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage {
	private WebDriver driver;

	public CustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}


	public void inputToAddressTextarea(String addressValue) {
		waitForAllElementVisible(driver, CustomerPageUI.CUSTOMER_ADDRESS_TEXTAREA);
		sendkeyToElement(driver, CustomerPageUI.CUSTOMER_ADDRESS_TEXTAREA, addressValue);
	}

	public void clickToAddressTextarea() {
		waitForElementClickable(driver, CustomerPageUI.CUSTOMER_ADDRESS_TEXTAREA);
		clickToElement(driver, CustomerPageUI.CUSTOMER_ADDRESS_TEXTAREA);
		getElement(driver, CustomerPageUI.CUSTOMER_ADDRESS_TEXTAREA).clear();
	}

	public void sendTabKeyToAddressTextarea() {
		waitForElementVisible(driver, CustomerPageUI.CUSTOMER_ADDRESS_TEXTAREA);
		sendKeyboardToElement(driver, CustomerPageUI.CUSTOMER_ADDRESS_TEXTAREA, Keys.TAB);
		
	}

	public String getCustomerErrorMessageByTextarea() {
		waitForElementVisible(driver, CustomerPageUI.CUSTOMER_ADDRESS_ERROR_MESSAGE);
		return getTextElement(driver, CustomerPageUI.CUSTOMER_ADDRESS_ERROR_MESSAGE);
	}

	public String getTextSubmitAlert() {
		driver.switchTo().alert();
		return getTextAlert(driver);
	}

	public String getCustomerInfoByText(String itemText) {
		waitForAllElementVisible(driver, CustomerPageUI.DYNAMIC_CUSTOMER_INFO_BY_TEXT, itemText);
		return getElementText(driver, CustomerPageUI.DYNAMIC_CUSTOMER_INFO_BY_TEXT, itemText);
	}

	public HomePageObject acceptDeleteCustomerAlert() {
		acceptAlert(driver);
		return PageGeneratorManager.getHomePageObject(driver);
	}

	


	



}
