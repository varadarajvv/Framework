package TestBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {

	public WebDriver driver;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Created for Generating Random String for Unique Email
	public static String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(15);
		return (generatedString);		
	}

}
