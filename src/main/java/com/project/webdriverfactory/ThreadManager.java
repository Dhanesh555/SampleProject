package com.project.webdriverfactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ThreadManager {

	
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	private static Logger logger = LogManager.getLogger(ThreadManager.class);
	private static WebDriver driver = DriverManager.getDriver();
	

	public static void setDriverThreadSafe() {
		
		threadLocalDriver.set(driver);
		logger.info("Executing with thread id {}", Thread.currentThread().getId());
	}
	
	public static WebDriver getDriver() {
		
		return threadLocalDriver.get();
	}
	
	public static void quitDriver() {
		
		driver.quit();
		threadLocalDriver.remove();
	}
}
