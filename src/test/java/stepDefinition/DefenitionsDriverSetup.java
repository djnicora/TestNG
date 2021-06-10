package stepDefinition;

import java.net.MalformedURLException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.driverConfig.DriverType;
import utils.driverConfig.Base;

public class DefenitionsDriverSetup extends Base {

//	@Given("Start Driver")
	@Before
	public void beforeTest() throws MalformedURLException {

		getDesiredDriver(DriverType.CHROME);

	}

//	@Then("Quit Driver")
	@After
	public void quitBrowser() {
		quitDriver();

	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {

		// validate if scenario has failed
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "image");
			quitDriver();
		}
	}

}
