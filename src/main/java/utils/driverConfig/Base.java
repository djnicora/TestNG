package utils.driverConfig;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;


public class Base extends DriverFactory{

	public void takesScreenshot(WebElement element)  {
		File scrFile = ((TakesScreenshot)element).getScreenshotAs(OutputType.FILE);
		File dstFile = new File("./reports/manualyCaptured/element.png");
		try {
			FileUtils.copyFile(scrFile, dstFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}