package com.project.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.project.exceptionhandler.TestFrameworkException;
import com.project.pageobjects.CommonObjects;
import com.project.webdriverfactory.DriverManager;

public class SupportFactory extends WaitFactory {

	private WebDriver driver = DriverManager.getDriver();
	private String screenShotPath = System.getProperty("user.dir") + DataProvider.getProperty("screenshot.dir.path");
	private Logger logger = LogManager.getLogger(SupportFactory.class);
	CommonObjects commonObjects = new CommonObjects();

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

	public void checkAndCloseAddPopUp() {

		switchToIframeIfPresent(commonObjects.notificationIframeElement, 3);
		if (IsElementVisisbleAndClickable(commonObjects.addCloseElement, 1)) {

			commonObjects.addCloseElement.click();
			logger.info("Successfully clicked on the add close button");
			driver.switchTo().defaultContent();
		} else {

			logger.info("The add window is not present in the UI");
		}
	}

	public String getTommorowMonth() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat format = new SimpleDateFormat("MMMM");
		return format.format(calendar.MONTH);
	}
	
	public int getTommorowDay() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return calendar.get(calendar.DAY_OF_MONTH);
	}
	
	public int getTommorowYear() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return calendar.get(calendar.YEAR);
	}
}
