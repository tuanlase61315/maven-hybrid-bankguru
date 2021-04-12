package pageUIs;

public class BasePageUI {
	public static final String REGISTERED_AND_EDITED_SUCCESS_MESSAGE = "//p[@class='heading3']";
	
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_VAULE = "//input[@value='%s']";
	
	public static final String DYNAMIC_MENU_NAV_BY_TEXT = "//ul[@class='menusubnav']//a[text()='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_VALUE = "//input[@value='%s']";
	
	public static final String DYNAMIC_CUSTOMER_ERROR_MESSAGE = "//input[@name='%s']/following-sibling::label";
}
