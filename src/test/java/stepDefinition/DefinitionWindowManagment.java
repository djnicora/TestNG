package stepDefinition;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.driverConfig.Base;

public class DefinitionWindowManagment extends Base{
	
	String mainwindow = null;

	protected Scenario scenario;
	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	@Given("I am on browser-windows page")
	public void i_am_on_browser_windows_page() throws ClassNotFoundException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/browser-windows");
	}

	@And("Open new child window within the main window")
	public void open_new_child_window_within_the_main_window() {
		// Open new child window within the main window
        driver.findElement(By.id("windowButton")).click();
	}
	
	@And("Get handles of the windows")
	public void get_handles_of_the_windows() {

        //Get handles of the windows
        mainwindow = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
     // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
                if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                System.out.println("Heading of child window is " + text.getText());
                mainwindow=null;
            }
        }
      
	}
	

	
	@Given("Opening all the child window")
	public void opening_all_the_child_window() {
		// Opening all the child window
	    driver.findElement(By.id("windowButton")).click();
	    driver.findElement(By.id("messageWindowButton")).click();
	}

	@Then("To handle all new opened window")
	public void to_handle_all_new_opened_window() {
		mainwindow = driver.getWindowHandle();
	    System.out.println("Main window handle is " + mainwindow);
	 
	    // To handle all new opened window
	    Set<String> s1 = driver.getWindowHandles();
	    System.out.println("Child window handle is" + s1);
	    Iterator<String> i1 = s1.iterator();
	 
	    // Here we will check if child window has other child windows and when child window
	    //is the main window it will come out of loop.
	      while (i1.hasNext()) {
	          String ChildWindow = i1.next();
	          if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
	              driver.switchTo().window(ChildWindow);
	              driver.close();
	              System.out.println("Child window closed");  
	           } 
	       }
	      mainwindow=null;
	}

	@And("Closed Child window")
	public void closed_child_window() {
		
        mainwindow = driver.getWindowHandle();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        
        while (i1.hasNext()) {
            String ChildWindow = i1.next();
                if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                System.out.println("Heading of child window is " + text.getText());
                driver.close();
                System.out.println("Child window closed");
            }
        }    
	}

	@When("Switch back to the main window which is the parent window")
	public void switch_back_to_the_main_window_which_is_the_parent_window() {
		driver.switchTo().window(mainwindow);
		
        driver.quit();
        mainwindow=null;
	}


}
