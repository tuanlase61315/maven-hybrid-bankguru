package utilities;

import java.util.Date;
import java.util.Locale;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

public class FakerConfig {
	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);


	public static FakerConfig getData() {
		return new FakerConfig();
	}
	
	public String getCustomerName() {
		return faker.name().name();
	}
	
	public Date getDate() {
		return faker.date().birthday(15, 30);
	}
		
	public String getAddress() {
		return faker.address().streetAddress();
	}
	
	public String getEmail() {
		return faker.internet().emailAddress();
	}
	
	public String getPhone() {
		return faker.phoneNumber().phoneNumber();
	}
	
	public String getCityName() {
		return faker.address().cityName();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}
}
