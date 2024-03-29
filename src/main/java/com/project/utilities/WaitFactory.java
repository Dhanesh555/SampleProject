package com.project.utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.exceptionhandler.TestFrameworkException;
import com.project.webdriverfactory.DriverManager;

public class WaitFactory {

	WebDriver driver = DriverManager.getDriver();
	Logger logger = LogManager.getLogger(WaitFactory.class);

	public boolean IsElementVisisbleAndClickable(WebElement element, int waitTimeInSeconds) {

		boolean flag = true;
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			logger.info("Waited until the element {} is clickable", element);
		} catch (TimeoutException e) {

			flag = false;
			logger.info("The element {} is not visible or clickable: {}", element, e);
		}
		return flag;
	}

	public boolean switchToIframeIfPresent(WebElement iframeElement, int waitTimeInSeconds) {

		boolean isPresent = true;
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeElement));
			logger.info("Waited until the frame is present in the UI {}", iframeElement);
		} catch (TimeoutException e) {

			isPresent = false;
			logger.info("No frame with the provided element {} is avaiable in the DOM: {}", iframeElement, e);
		}
		return isPresent;
	}

	public WebElement WaitForElementUntilClickable(WebElement element, int waitTimeInSeconds) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
			WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			logger.info("Waited until the element {} is clickable", clickableElement);
			return clickableElement;
		} catch (TimeoutException e) {

			logger.info("The element {} is not visible or clickable: {}", element, e);
			throw new TestFrameworkException("The element "+element+ "is not clickable");
		}
	}
	
	public boolean isTextPresent(WebElement element, String expectedText, int waitTimeInSeconds) {
		
		boolean flag = false;
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
			flag = wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
			logger.info("Waited until the text {} is present", expectedText);
		} catch (TimeoutException e) {
			
			logger.info("The text {} is not present in the element {} {}", expectedText, element, e);
		}
		return flag;
	}
	
	public boolean waitUntilTheTitleIsExpected(String expectedTitle, int waitTimeInSeconds) {
		
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
			flag = wait.until(ExpectedConditions.titleContains(expectedTitle));
			logger.info("Waited until the title {} is present", driver.getTitle());
		} catch (TimeoutException e) {
			
			logger.info("The title {} is not present", expectedTitle);
		}
		return flag;
	}
}
