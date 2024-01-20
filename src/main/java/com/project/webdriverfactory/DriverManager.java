package com.project.webdriverfactory;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.project.browserfactory.Browser;
import com.project.utilities.DataProvider;

public class DriverManager {

	private static WebDriver driver;
	private static DriverManager driverManager;
	Browser browser = Browser.valueOf(DataProvider.getProperty("browser.name"));
	private static Logger logger = LogManager.getLogger(DriverManager.class);
	

	private DriverManager() {

		driver = launchBrowser();
	}

	public static WebDriver getDriver() {

		if (driverManager == null) {
			
			logger.info("Instantiating driver manager");
			driverManager = new DriverManager();
		}
		return driver;
	}

	private WebDriver launchBrowser() {

		if (driver == null) {
			
			logger.info("driver is null and creating new driver instance");
			switch (browser) {
			case CHROME:
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-infobars");
				options.addArguments("--start-maximized");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--disable-popup-blocking");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new ChromeDriver();
				logger.info("Launched chrome browser successfully");
				driver.manage().window().maximize();
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				logger.info("Launched firefox browser successfully");
				break;
			default:
				logger.error("Invalid browser name: ", browser);
				throw new WebDriverException("Invalid browser name"+ browser);
			}
		}
		return driver;
	}

	public static void quitDriver() {

		if (driver != null) {
			logger.info("Driver is not null, quitting the driver instances");
			driver.quit();	
		}
		else
			logger.warn("Driver instance is already null, not able to quit the browser");
	}

	public static void closeDriver() {

		if (driver != null)
			driver.quit();
		else
			logger.warn("Driver instance is already null, not able to close the browser");
	}
	
	public static void launchURL(String url) {
		
		getDriver().get(url);
		logger.info("Successfully navigated to the url {}", url);
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}
}