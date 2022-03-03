/**
 * 
 */
package com.jba.ci.ct.bdd.stepdefs;

import java.io.FileReader;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jba.ci.ct.bean.GretelTestData;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;

/**
 * @author judebarnabasantony
 *
 */

@Slf4j
@CucumberContextConfiguration
public class SeleniumStepDef {

	private static final String CONSOLE = "console";
	private static final String VIDEO = "video";
	private static final String VISUAL = "visual";
	private static final String NETWORK = "network";
	private static final String NAME = "name";
	private static final String BUILD = "build";
	private static final String RESOLUTION = "resolution";
	private static final String VERSION = "version";
	private static final String BROWSER_NAME = "browserName";
	private static final String ACCESS_KEY = "accessKey";
	private static final String USER = "user";
	private static final String PLATFORM = "platform";
	private static final String _1024X768 = "1024x768";
	private static final String _92_0 = "92.0";
	private static final String CHROME = "Chrome";
	private static final String WINDOWS_10 = "Windows 10";
	private static final String CT_TEST = "CT Test";
	private static final String GOOGLE_SEARCH_TEST = "Google Search Test";
	private static final String Q = "q";
	private static final String LT_ACCESS_KEY = "LT_ACCESS_KEY";
	private static final String LT_EMAIL = "LT_EMAIL";
	private static final String GRETEL_TEST_OUT_FILE = "GRETEL_TEST_OUT_FILE";
	private static final String HTTPS = "https://";
	private static final String HUB_CLOUD_LAMADATEST_COM_WD_HUB = "@hub.lambdatest.com/wd/hub";
	private static final String COL = ":";
	private static final String GOOGLE_URL = "https://www.google.com/";
	public static final String LAMADATEST_AUTOMATE_USERNAME = System.getenv(LT_EMAIL);
	public static final String LAMADATEST_AUTOMATE_ACCESS_KEY = System.getenv(LT_ACCESS_KEY);
	public static final String GRETEL_TEST_OUT_FILE_PATH = System.getenv(GRETEL_TEST_OUT_FILE);
	private List<GretelTestData> listGretelTestData;
	private String testData;
	private WebDriver driver;

	@Before()
	public void before_getdiver() {
		getDesiredCapabilities();
		getTestData();
	}

	private void getTestData() {
		try {
			try (CSVReader reader = new CSVReaderBuilder(new FileReader(GRETEL_TEST_OUT_FILE_PATH)).withSkipLines(1)
					.build()) {
				ColumnPositionMappingStrategy<GretelTestData> strategy = new ColumnPositionMappingStrategy<>();
				strategy.setType(GretelTestData.class);
				CsvToBean<GretelTestData> csvToBean = new CsvToBeanBuilder<GretelTestData>(reader)
						.withMappingStrategy(strategy).withIgnoreLeadingWhiteSpace(true).build();
				listGretelTestData = csvToBean.parse();
				testData = listGretelTestData.get(0).getCompany();
			}
		} catch (Exception e) {
			log.error("{}", e);
		}
	}

	private void getDesiredCapabilities() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(PLATFORM, WINDOWS_10);
		caps.setCapability(USER, LAMADATEST_AUTOMATE_USERNAME);
		caps.setCapability(ACCESS_KEY, LAMADATEST_AUTOMATE_ACCESS_KEY);
		caps.setCapability(BROWSER_NAME, CHROME);
		caps.setCapability(VERSION, _92_0);
		caps.setCapability(RESOLUTION, _1024X768);
		caps.setCapability(BUILD, CT_TEST);
		caps.setCapability(NAME, GOOGLE_SEARCH_TEST);
		caps.setCapability(NETWORK, true);
		caps.setCapability(VISUAL, true);
		caps.setCapability(VIDEO, true);
		caps.setCapability(CONSOLE, true);
		try {
			driver = new RemoteWebDriver(
					new URL(new StringBuilder().append(HTTPS).append(LAMADATEST_AUTOMATE_USERNAME).append(COL)
							.append(LAMADATEST_AUTOMATE_ACCESS_KEY).append(HUB_CLOUD_LAMADATEST_COM_WD_HUB).toString()),
					caps);
		} catch (Exception e) {
			log.error("{}", e);
		}
	}

	@Given("I am on the Google search page")
	public void I_am_on_the_Google_search_page() {
		driver.get(GOOGLE_URL);
	}

	@When("I search for test data from gredel")
	public void search_for() {
		WebElement element = driver.findElement(By.name(Q));
		element.sendKeys(testData);
		element.submit();
	}

	@Then("the page title should start with same test data")
	public void checkTitle() {
		new WebDriverWait(driver, 40L).until(d -> {
			log.info("Gretel Test Data :: {}",testData);
			log.info("Browser Title Data :: {}",d.getTitle());
			return d.getTitle().startsWith(testData);});
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}
}
