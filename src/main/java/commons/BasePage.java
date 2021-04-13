package commons;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.PageGeneratorManager;
import pageUIs.BasePageUI;
import pageUIs.CustomerPageUI;

import org.apache.commons.logging.Log;

public class BasePage {
	private WebDriver driver;
	private Actions action;
	private Select select;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private Alert alert;
	private long longTimeout = 10;
	private Log log;
	final String OLD_FORMAT = "MM/dd/yyyy";
	final String NEW_FORMAT = "yyyy-MM-dd";

	public String newFormatDate(String oldDateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = null;
		try {
			d = sdf.parse(oldDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdf.applyPattern(NEW_FORMAT);
		return sdf.format(d);
	}

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sleepInMiliSecond(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String generateEmail() {
		Random rand = new Random();
		return "test" + rand.nextInt(99999) + "@gmail.com";
	}

	public String getDirectorySlash(String folderName) {
		String separator = File.separator;
		return separator + "src" + separator + "main" + separator + "resources" + separator + folderName + separator;
	}

	public String getDynamicLocator(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}

	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		driver.switchTo().alert();
		return alert.getText();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		alert = waitAlertPresence(driver);
		driver.switchTo().alert();
		alert.sendKeys(value);
	}

	public void switchWindowByID(WebDriver dirver, String parentID) {
		Set<String> allWindowID = driver.getWindowHandles();

		for (String windowID : allWindowID) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowID = driver.getWindowHandles();

		for (String windowID : allWindowID) {
			driver.switchTo().window(windowID);

			String actualTilte = driver.getTitle();
			if (actualTilte.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowID = driver.getWindowHandles();

		for (String windowID : allWindowID) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				driver.close();
			}
			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window(parentID);
				break;
			}
		}
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public WebElement getElement(WebDriver driver, String locator, String... values) {
		return driver.findElement(getByXpath(getDynamicLocator(locator, values)));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		getElement(driver, getDynamicLocator(locator, values)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
		getElement(driver, getDynamicLocator(locator, values)).clear();
		getElement(driver, getDynamicLocator(locator, values)).sendKeys(value);
	}

	public void selectItemInDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	public String getSelectItemInDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemInCustomDropbox(WebDriver driver, String parentLocator, String childLocator,
			String expectedItem) {
		getElement(driver, parentLocator).click();

		explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItem = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

		for (WebElement item : allItem) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}

	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}

	public String getAttributeValue(WebDriver driver, String locator) {
		return getElement(driver, locator).getAttribute("value");
	}

	public String getTextElement(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getTextElement(WebDriver driver, String locator, String... values) {
		return getElement(driver, getDynamicLocator(locator, values)).getText();
	}

	public int getElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... values) {
		return getElement(driver, getDynamicLocator(locator, values)).isSelected();
	}

	public boolean isElementDisplay(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isElementEnable(WebDriver driver, String locator, String... values) {
		return getElement(driver, getDynamicLocator(locator, values)).isEnabled();
	}

	public void checkTheCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void UncheckTheCheckbox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public WebDriver switchToFrame(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	public WebDriver switchToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void rightClick(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, getDynamicLocator(locator, values)), key).perform();
	}

	public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {

		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, getDynamicLocator(locator, values)));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));

		} catch (Exception e) {
			log.debug("Wait for element visible with error: " + e.getMessage());
		}
	}

	public void waitForAllElementVisible(WebDriver driver, String locator, String... values) {
		try {
			explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocator(locator, values))));

		} catch (Exception e) {
			log.debug("Wait for element visible with error: " + e.getMessage());
		}
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, values))));
	}

	public void waitForElementInvisble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}

//	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
//		String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
//
//		String fullFileName = "";
//		for (String file : fileNames) {
//			fullFileName = fullFileName + filePath + file + "\n";
//		}
//		fullFileName = fullFileName.trim();
//		getElement(driver, HomePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
////		sendkeyToElement(driver, HomePageUI.UPLOAD_FILE_TYPE, fullFileName);
//
//	}

	/* Check data table */
//	public boolean isInformationDisplayedAtColumnNameAndRowNumber(WebDriver driver, String tableID, String columnName, String rowIndex, String expectedValue) {
//		int columnNameIndex = getElementSize(driver, OrangeHRMAbstractPageUI.DYNAMIC_TABLE_COLUMN_NAME_SIBLING, tableID, columnName) + 1;
//		String actualValue = getElementText(driver, OrangeHRMAbstractPageUI.TEXTBOX_AT_COLUMN_AND_ROW_INDEX, rowIndex, String.valueOf(columnNameIndex));
//		return actualValue.equals(expectedValue);
//	}
//
//	public boolean isNoRecordFoundDisplayedAtTableName(WebDriver driver, String tableID) {
//		waitForElementVisible(driver, OrangeHRMAbstractPageUI.NO_RECORD_FOUND_TEXT_AT_TABLE_NAME, tableID);
//		return isElementDisplayed(driver, OrangeHRMAbstractPageUI.NO_RECORD_FOUND_TEXT_AT_TABLE_NAME, tableID);
//	}

	/* Sort String Ascending */

	public boolean isDataStringSortedAscending(WebDriver driver, String locator) {
		// khai bao 1 array list
		ArrayList<String> arrayList = new ArrayList<>();

		// tim tat ca cac element matching vs dk (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		// lay text cua tung element add vao array list
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println("------Du lieu tren UI: -------------");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// copy qua 1 array list moi de SORT trong code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}

		// thuc hien sort asc
		Collections.sort(sortedList);

		System.out.println("-------Du lieu da Sort ASC trong code ---------");
		for (String name : sortedList) {
			System.out.println(name);
		}

		// verify 2 array bang nhau - new du lieu sort tren UI ko chinh xac thi ket qua
		// tra ve sai
		return sortedList.equals(arrayList);
	}

	/* Sort String Descending */
	public boolean isDataStringDSortedDescending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<>();

		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println("----Du lieu tren UI-----");
		for (String name : arrayList) {
			System.out.println(name);
		}

		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}

		Collections.sort(sortedList);

		System.out.println("---Du lieu da Sort ASC trong code-----");
		for (String name : sortedList) {
			System.out.println(name);
		}

		Collections.reverse(sortedList);

		System.out.println("---Du lieu da Sort DSC trong code ---");
		for (String name : sortedList) {
			System.out.println(name);
		}

		return sortedList.equals(arrayList);
	}

	/* Sort Float Ascending */
	public boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();

		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println("---Du lieu tren UI---");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		ArrayList<Float> sortedList = new ArrayList<Float>();

		for (Float child : arrayList) {
			sortedList.add(child);
		}

		Collections.sort(sortedList);

		System.out.println("---Du lieu da Sort ASC trong code:---");
		for (Float name : sortedList) {
			System.out.println(name);
		}

		return sortedList.equals(arrayList);
	}

	/* Sort Float Descending */
	public boolean isDataFloatSortedDescending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();

		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println("---Du lieu tren UI---");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		ArrayList<Float> sortedList = new ArrayList<Float>();

		for (Float child : arrayList) {
			sortedList.add(child);
		}

		Collections.sort(sortedList);

		System.out.println("---Du lieu da Sort ASC trong code:---");
		for (Float name : sortedList) {
			System.out.println(name);
		}

		Collections.reverse(sortedList);

		System.out.println("---Du lieu da Sort DSC trong code ---");
		for (Float name : sortedList) {
			System.out.println(name);
		}

		return sortedList.equals(arrayList);
	}

	/* Sort Date Ascending */
	public boolean isDataDateSortedAscending(WebDriver driver, String locator) {
		ArrayList<Date> arrayList = new ArrayList<Date>();

		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		for (WebElement element : elementList) {
			arrayList.add(convertStringToDate(element.getText()));
		}

		System.out.println("---Du lieu tren UI:");
		for (Date name : arrayList) {
			System.out.println(name);
		}

		ArrayList<Date> sortedList = new ArrayList<Date>();
		for (Date child : arrayList) {
			sortedList.add(child);
		}

		Collections.sort(sortedList);

		System.out.println("---Du lieu da sort asc trong code:");
		for (Date name : sortedList) {
			System.out.println(name);
		}

		return sortedList.equals(arrayList);
	}

	/* Sort Date Descending */
	public boolean isDataDateSortedDescending(WebDriver driver, String locator) {
		ArrayList<Date> arrayList = new ArrayList<Date>();

		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		for (WebElement element : elementList) {
			arrayList.add(convertStringToDate(element.getText()));
		}

		System.out.println("---Du lieu tren UI:");
		for (Date name : arrayList) {
			System.out.println(name);
		}

		ArrayList<Date> sortedList = new ArrayList<Date>();
		for (Date child : arrayList) {
			sortedList.add(child);
		}

		Collections.sort(sortedList);

		System.out.println("---Du lieu da sort asc trong code:");
		for (Date name : sortedList) {
			System.out.println(name);
		}

		Collections.reverse(sortedList);

		System.out.println("---Du lieu da Sort DSC trong code ---");
		for (Date name : sortedList) {
			System.out.println(name);
		}

		return sortedList.equals(arrayList);
	}

	/* format date data */
	public Date convertStringToDate(String dateInString) {
		dateInString = dateInString.replace(",", "");
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}

	public static long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}

	// DYNAMIC LOCATOR

	public BasePage clickToMenuNavByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MENU_NAV_BY_TEXT, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_MENU_NAV_BY_TEXT, pageName);

		if (pageName.equals("Manager")) {
			return PageGeneratorManager.getHomePageObject(driver);
		} else if (pageName.equals("New Customer") || pageName.equals("Edit Customer")
				|| pageName.equals("Delete Customer")) {
			return PageGeneratorManager.getcCustomerPageObject(driver);
		} else if (pageName.equals("Deposit") || pageName.equals("Withdrawal") || pageName.equals("Fund Transfer")) {
			return PageGeneratorManager.getPaymentPageObject(driver);
		} else if (pageName.equals("New Account") || pageName.equals("Edit Account")
				|| pageName.equals("Delete Account")) {
			return PageGeneratorManager.getAccountPageObject(driver);
		} else if (pageName.equals("Change Password")) {
			return PageGeneratorManager.getUserPageObject(driver);
		} else {
			throw new RuntimeException("Please input the correct Page Name");
		}
	}

	public void inputToTextboxByName(WebDriver driver, String textboxName, String inputValue) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
		getElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, inputValue, textboxName);
	}

	public void clickToTextboxByName(WebDriver driver, String textboxName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
		clickToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
	}

	public void sendTabKeyToTextboxByName(WebDriver driver, String textboxName) {
		getElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName).clear();
		;
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
		sendKeyboardToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, textboxName);
	}

	public void clickToButtonByValue(WebDriver driver, String buttonValue) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_VAULE, buttonValue);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_VAULE, buttonValue);
	}

	public void clickToRadioButtonByValue(WebDriver driver, String raidoValue) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_VALUE, raidoValue);
		if (!isElementSelected(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_VALUE, raidoValue)) {
			clickToElement(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_VALUE, raidoValue);
		}
	}

	public String getItemErrorMessageByTextboxName(WebDriver driver, String textboxName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_CUSTOMER_ERROR_MESSAGE, textboxName);
		return getTextElement(driver, BasePageUI.DYNAMIC_CUSTOMER_ERROR_MESSAGE, textboxName);
	}

	public String getHeadingMessage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.HEADING_MESSAGE);
		return getTextElement(driver, BasePageUI.HEADING_MESSAGE);
	}

}
