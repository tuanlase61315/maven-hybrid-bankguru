package pageUIs;

public class CustomerPageUI {
	public static final String CUSTOMER_REGISTERED_AND_EDITED_SUCCESS_MESSAGE = "//p[@class='heading3']";
	public static final String CUSTOMER_ADDRESS_TEXTAREA = "//textarea[@name='addr']";
	public static final String CUSTOMER_ADDRESS_ERROR_MESSAGE = "//textarea[@name='addr']/following-sibling::label";;
	
	public static final String DYNAMIC_CUSTOMER_INFO_BY_TEXT = "//table[@id='customer']//tr/td[text()='%s']/following-sibling::td";
}
