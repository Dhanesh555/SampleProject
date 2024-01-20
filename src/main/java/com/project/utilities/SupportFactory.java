package com.project.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.project.exceptionhandler.TestFrameworkException;
import com.project.webdriverfactory.DriverManager;

public class SupportFactory{
	
	private WebDriver driver = DriverManager.getDriver();
	private String screenShotPath = System.getProperty("user.dir") + DataProvider.getProperty("screenshot.dir.path");
    private Logger logger = LogManager.getLogger(SupportFactory.class);
	
	public void takescreenshot(String scenarioName) {
		
		Date date = new Date();
		String fileName = date.toString().replace(":", "-").replace(" ", "-");
		fileName = scenarioName + "_" + fileName + ".png";
		screenShotPath = screenShotPath + fileName;
		File file = new File(screenShotPath);
		File screenShotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShotfile, file);
			logger.info("Screenshot captured and saved successfully");
		} catch (IOException e) {
			
			logger.error("Unable to save the file {}", e);
			throw new TestFrameworkException("Error occured while saving the screenshot");
		}
	}
}
