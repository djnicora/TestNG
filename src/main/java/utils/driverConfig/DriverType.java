package utils.driverConfig;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static utils.driverConfig.DriverFactory.Add_Log;;

public enum DriverType implements DriverSetup {

	CHROME {

		@Override
		public WebDriver getDesiredDriver() {
			Add_Log.info("Options & Chrome driver !!!!");
			ChromeOptions options = new ChromeOptions();
			/* uncomment to use the proxy */
//	            com.ingonline.utils.mobProxy.Proxy.startProxy();
//	            org.openqa.selenium.Proxy seleniumProxy = ClientUtil.createSeleniumProxy(Proxy.proxy);
//	            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

			String downloadFilepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "Downloads" + File.separator;
			
			Add_Log.info("Files will be downloaded in: " + downloadFilepath);
			System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
					System.getProperty("user.dir") + "//reports/chromedriver.log");
			options.addArguments("test-type", "disable-gpu", "disable-extensions", "disable-software-rasterizer");
			options.addArguments("--enable-logging --v=1");
			options.addArguments("--wm-window-animations-disabled");
			options.addArguments("--enable-automation");
			options.addArguments("--disable-boot-animation ");
			options.addArguments("--window-size=1280,1020");
			options.addArguments("--window-position=0,0");
			options.addArguments("--no-sandbox");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--ignore-urlfetcher-cert-requests");
//				options.addArguments("--headless");
//	            options.addArguments("--proxy=localhost:"+ Proxy.proxy.getPort());
			options.addArguments("--ignore-ssl-errors=yes");

//				if(Login.getSystemUserName().equalsIgnoreCase("root")){
//					options.addArguments("--headless");
//				}
//				options.addArguments("--headless");
//				options.addArguments("--remote-debugging-port=9222");

			if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			}else {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
			}
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			options.setCapability("goog:loggingPrefs", logPrefs);

			logPrefs.enable(LogType.BROWSER, Level.SEVERE);
			logPrefs.enable(LogType.DRIVER, Level.ALL);
			logPrefs.enable(LogType.CLIENT, Level.SEVERE);
			logPrefs.enable(LogType.SERVER, Level.SEVERE);

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			options.setExperimentalOption("prefs", chromePrefs);

			options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			options.setCapability(ChromeOptions.CAPABILITY, chromePrefs);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(ChromeOptions.CAPABILITY, options);
//				capabilities.setCapability("pageLoadStrategy", "eager");
			return new ChromeDriver(options);
		}

	},
	FIREFOX {
		@Override
		public WebDriver getDesiredDriver() {
			Add_Log.info("Firefox Options Loaded");

			FirefoxBinary firefoxBinary = new FirefoxBinary();
			String downloadFilepath = System.getProperty("user.dir") + "src/test/resources/downloads";
			downloadFilepath = downloadFilepath.replace("\\", "\\\\");
			downloadFilepath = downloadFilepath.replace("/", "\\\\");
//			firefoxBinary.addCommandLineOptions("--headless");
			if (System.getProperty("os.name").toLowerCase().contains("linux")) {
				
			}else {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver.exe");
			}
			FirefoxOptions options = new FirefoxOptions();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.useDownloadDir", true);
			profile.setPreference("browser.download.dir", downloadFilepath);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xml");
			profile.setPreference("dom.disable_window_move_resize", true);

			options.setProfile(profile);
			options.setBinary(firefoxBinary);
			options.setCapability("marionette", true);
			options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			return new FirefoxDriver(options);
		}
	},
	EDGE {
		@Override
		public WebDriver getDesiredDriver() {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--start-maximized");
			return new EdgeDriver(options);
		}
	},
	RWebDriver {

		@Override
		public WebDriver getDesiredDriver() throws MalformedURLException {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
					new DesiredCapabilities("firefox", "", Platform.WIN10));
		}

	};

}
