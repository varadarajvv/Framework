package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

// Constructor : Maintains the WebDriver Instance across all the Classes
	public SearchPage(WebDriver driver) {
		super(driver);
	}

// Web Elements
	@FindBy(name = "q")
	private WebElement txtSearch;
	@FindBy(name = "btnK")
	private WebElement btnSearch;

// Action Methods
	public void enterSearchText(String text) {
		txtSearch.sendKeys(text);
	}

	public void clickSearchButton() {
		btnSearch.click();
	}
}
