package utils.driverConfig;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public interface DriverSetup {
	
	
	WebDriver getDesiredDriver() throws MalformedURLException;
	
	
}
