package StepDefinitions;

import static org.junit.Assert.fail;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PageObjects.SearchPage;
import TestBase.BaseClass;
import io.cucumber.java.en.*;
import java.util.HashMap;
import java.util.List;
import Utilities.DataReader;
import org.junit.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ResourceBundle;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;


public class Steps{

	WebDriver driver;
	SearchPage searchPage;
	List<HashMap<String, String>> dataMap; // Data Driven

	public Logger logger; // For Logging
	public ResourceBundle resourceBundle; // For Reading Properties File
	public String browser; // To Store Browser Name

	@Before // Junit Hook - Executes Once Before Starting
	public void setUp() {
	logger = LogManager.getLogger(this.getClass()); // For Logging
	resourceBundle = ResourceBundle.getBundle("config"); // Reading config.properties (For Browser)
	browser = resourceBundle.getString("browser");
	}

	@Given("User Launch Browser")
	public void user_launch_browser() {
	if (browser.equals("chrome")) {
	driver = new ChromeDriver();
	} else if (browser.equals("firefox")) {
	driver = new FirefoxDriver();
	} else if (browser.equals("edge")) {
	driver = new EdgeDriver();
	}
	logger.info("Browser Opened: " + browser);
	}


	@And("Opens URL {string}")
	public void opens_url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@When("User Enters Text {string} into the Search Field")
	public void user_enters_text_into_the_search_field(String text) {
		searchPage = new SearchPage(driver);
		searchPage.enterSearchText(text);
	}

	@When("Click on Search Button")
	public void click_on_search_button() {
		searchPage.clickSearchButton();
	}

	@Then("Verify the Title {string}")
	public void verify_the_title(String title) throws InterruptedException {
		driver.getTitle();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			fail("Title Incorrect");
		}
		driver.quit();
	}

	// Data Driven Testing using Excel
	@When("User Performs Search by Entering Text {string} and Verify Title")
	public void user_performs_search_by_entering_text_and_verify_title(String rows) throws InterruptedException {
		dataMap = DataReader.data(System.getProperty("user.dir") + "\\TestData\\Google.xlsx", "Search");
		int index = Integer.parseInt(rows) - 1;
		try {
			String searchText = dataMap.get(index).get("SearchText");
			String expectedTitle = dataMap.get(index).get("ExpectedTitle");
			searchPage = new SearchPage(driver);
			searchPage.enterSearchText(searchText);
			searchPage.clickSearchButton();
			Thread.sleep(5000);
			Assert.assertEquals(searchText, expectedTitle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@After
	public void tearDown(Scenario scenario) {
	System.out.println("Scenario status ======>" + scenario.getStatus());
	if (scenario.isFailed()) {
	byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	scenario.attach(screenshot, "image/png", scenario.getName());
	}
	driver.quit();
	}


}
