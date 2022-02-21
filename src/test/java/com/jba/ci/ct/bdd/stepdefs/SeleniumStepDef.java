/**
 * 
 */
package com.jba.ci.ct.bdd.stepdefs;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import src.test.java.com.jba.ci.bdd.stepdefs.String;

/**
 * @author judebarnabasantony
 *
 */

@Slf4j
@CucumberContextConfiguration
public class SeleniumStepDef {

	private static final String _1024X768 = "1024x768";
	private static final String _92_0 = "92.0";
	private static final String CHROME = "Chrome";
	private static final String WINDOWS_10 = "Windows 10";
	private static final String CT_TEST = "CT Test";
	private static final String GOOGLE_SEARCH_TEST = "Google Search Test";
	private static final String Q = "q";
	private static final String LT_ACCESS_KEY = "LT_ACCESS_KEY";
	private static final String LT_EMAIL = "LT_EMAIL";
	private static final String HTTPS = "https://";
	private static final String HUB_CLOUD_LAMADATEST_COM_WD_HUB = "@hub.lambdatest.com/wd/hub";
	private static final String COLLUMN = ":";
	private static final String GOOGLE_URL = "https://www.google.com/";
	public static final String LAMADATEST_AUTOMATE_USERNAME = System.getenv("LT_EMAIL");
	public static final String LAMADATEST_AUTOMATE_ACCESS_KEY = System.getenv("LT_ACCESS_KEY");

	private WebDriver driver;
	
	@Before()
	public  void before_getdiver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platform", WINDOWS_10);
		caps.setCapability("user", LAMADATEST_AUTOMATE_USERNAME);
		caps.setCapability("accessKey", LAMADATEST_AUTOMATE_ACCESS_KEY);
		caps.setCapability("browserName", CHROME);
		caps.setCapability("version", _92_0);
		caps.setCapability("resolution", _1024X768);
		caps.setCapability("build", CT_TEST);
		caps.setCapability("name", GOOGLE_SEARCH_TEST);
		caps.setCapability("network", true);
		caps.setCapability("visual", true);
		caps.setCapability("video", true);
		caps.setCapability("console", true);
		try {
			driver = new RemoteWebDriver(new URL(new StringBuilder().append(HTTPS).append(LAMADATEST_AUTOMATE_USERNAME)
					.append(COLLUMN).append(LAMADATEST_AUTOMATE_ACCESS_KEY).append(HUB_CLOUD_LAMADATEST_COM_WD_HUB).toString()), caps);
		} catch (Exception e) {
			log.error("{}", e);
		}
	}

	@Given("I am on the Google search page")
	public void I_am_on_the_Google_search_page() {
		driver.get(GOOGLE_URL);
	}

	@When("I search for {string}")
	public void search_for(String query) {
		WebElement element = driver.findElement(By.name(Q));
		element.sendKeys(query);
		element.submit();
	}

	@Then("the page title should start with {string}")
	public void checkTitle(String titleStartsWith) {
		new WebDriverWait(driver, 10L).until(d -> d.getTitle().toLowerCase().startsWith(titleStartsWith));
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}
}
