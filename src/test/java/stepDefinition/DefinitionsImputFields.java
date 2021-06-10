package stepDefinition;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.driverConfig.Base;

public class DefinitionsImputFields extends Base{

	@Given("I am on Input Fields page")
	public void i_am_on_input_fields_page() throws MalformedURLException {
	   System.out.println("hiii");
	   driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
	   
	   Add_Log.debug("Debug Message Logged !!!");
	   Add_Log.info("Info Message Logged !!!");
        
	}
	
	
	WebElement imputField;
	
	
	
	@And("I am entering field")
	public void i_am_entering_field()  {

		WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Close']")));
		closePopup.click();
		imputField = driver.findElement(By.id("user-message"));
		imputField.sendKeys("ABC");
		
		
	}
	
	WebElement button;
	WebElement message;

	@When("I click on show message button")
	public void i_click_on_show_message_button() {
		button = driver.findElement(withTagName("button").below(imputField));
		button.click();
		message = driver.findElement(withTagName("span").below(button));
		
		
		
	}

	@Then("I validate the message")
	public void i_validate_the_message() {
		takesScreenshot(message);
		Assert.assertEquals(message.getText(),"ABCd","The message is not equal!!!");
		Add_Log.error("Error Message Logged !!!");
		
	}

	@And("I am entering the two fields")
	public void i_am_entering_the_two_fields() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I click on Get Total button")
	public void i_click_on_get_total_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I validate the total")
	public void i_validate_the_total() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
