package utils.driverConfig;

import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {
	public static WebDriver driver;
	
	public static Logger Add_Log = LogManager.getLogger();
	public static WebDriverWait wait = null;
	private static final long MEGABYTE = 1024L * 1024L;

	public static long bytesToMeg(long bytes) {
		return bytes / MEGABYTE;
	}

//		private static final String browser = System.getProperty ("browser", defaultDriverType.toString()).toUpperCase();
//		private final String operatingSystem = System.getProperty("os.name").toUpperCase();
//		private final String systemArchitecture = System.getProperty("os.arch");

	public static WebDriver getDesiredDriver(DriverType type) throws MalformedURLException {

		Add_Log.info("Total number of processors or cores available to the JVM: "
				+ Runtime.getRuntime().availableProcessors());

		Runtime runtime = Runtime.getRuntime();
		long totalMemory = runtime.totalMemory(); // current heap allocated to the VM process
		long freeMemory = runtime.freeMemory(); // out of the current heap, how much is free
		long maxMemory = runtime.maxMemory(); // Max heap VM can use e.g. Xmx setting
		long usedMemory = totalMemory - freeMemory; // how much of the current heap the VM is using
		long availableMemory = maxMemory - usedMemory; // available memory i.e. Maximum heap size minus the current
														// amount used
		Add_Log.info("Available Free Memory: " + bytesToMeg(availableMemory) + "MB");

		// logging system information
		/*
		 * Returns the maximum amount of memory available to the Java Virtual Machine
		 * set by the '-mx' or '-Xmx' flags.
		 */
		Add_Log.info("Maximum amount of memory available to the Java Virtual Machine: "
				+ bytesToMeg(Runtime.getRuntime().maxMemory()) + "MB");

		/*
		 * Returns the total memory allocated from the system (which can at most reach
		 * the maximum memory value returned by the previous function).
		 */
		Add_Log.info("Total memory allocated: " + bytesToMeg(Runtime.getRuntime().totalMemory()) + "MB");

		switch (type.toString()) {

		case ("CHROME"):
			driver = DriverType.CHROME.getDesiredDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			break;

		case ("FIREFOX"):
			driver = DriverType.FIREFOX.getDesiredDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			break;

		case ("EDGE"):
			driver = DriverType.EDGE.getDesiredDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			break;

		}

		Add_Log.info(type.toString());
		return driver;
	}

	public void quitDriver() {
		if (null != driver) {
//			driver.close();
			driver.quit();
		}
	}
	
	

}
