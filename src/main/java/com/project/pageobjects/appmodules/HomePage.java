package com.project.pageobjects.appmodules;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.project.exceptionhandler.DataMismatchException;
import com.project.pageobjects.HomePageObjects;
import com.project.utilities.SupportFactory;
import com.project.utilities.WaitFactory;
import com.project.webdriverfactory.DriverManager;
import org.openqa.selenium.WebElement;

public class HomePage {

	HomePageObjects homePageObjects = new HomePageObjects();
	private WebDriver driver = DriverManager.getDriver();
	private Logger logger = LogManager.getLogger(HomePage.class);
	private WaitFactory waitFactory = new WaitFactory();
	private SupportFactory supportFactory = new SupportFactory();

	public String getTitle() {

		return driver.getTitle();
	}

	public boolean getLogo() {

		return homePageObjects.logoElement.isDisplayed();
	}

	public void clickFlightSelectOption(String option) {

		waitFactory.WaitForElementUntilClickable(homePageObjects.getFlightSearchOptionWebElement(option), 5).click();
		logger.info("Successfully clicked on the flight search option {}", option);
	}
	
	public void selectTravellAndClassOption(String option, String number) {

		waitFactory.WaitForElementUntilClickable(homePageObjects.getTravellAndClassWebElement(option, number), 5).click();
		logger.info("Successfully clicked on the travell and class option {}", option);
	}
	
	public void selectTravellClass(String option) {
		
		homePageObjects.travellClassWebElement.forEach((element) -> {
			
			if (element.getText().trim().equals(option)) 
				element.click();
		});
		logger.info("Successfully selected the travell option {}", option);
	}

	public List<String> verifyFunctionalHeaders(List<String> expectedHeaders) {

		List<String> missingHeaders = new ArrayList<>();
		if (expectedHeaders.size() != homePageObjects.headerElements.size()) {

			logger.error("The actual and expected header counts are not matching");
			throw new DataMismatchException("The actual and expected header counts are not matching");
		} else {

			for (int i = 0; i < expectedHeaders.size(); i++) {

				String headerText = homePageObjects.headerElements.get(i).getText().trim();
				if (!(headerText.equals(expectedHeaders.get(i)))) {

					logger.error("The actual and expected headers are not equal actual {}, expected {}", headerText,
							expectedHeaders.get(i));
					missingHeaders.add(headerText);
				} else {

					logger.info("The header value {} is present in the homepage", headerText);
				}
			}
		}
		return missingHeaders;
	}

	public List<String> verifyTripOptions(List<String> expectedList) {

		List<String> missingOptions = new ArrayList<>();
		if (expectedList.size() != homePageObjects.tripElements.size()) {

			logger.error("The actual and expected option counts are not matching, actual: {}, ex {}");
			throw new DataMismatchException("The actual and expected option counts are not matching");
		} else {

			for (int i = 0; i < expectedList.size(); i++) {

				String headerText = homePageObjects.tripElements.get(i).getText().trim();
				if (!(headerText.equals(expectedList.get(i)))) {

					logger.error("The actual and expected options are not equal actual {}, expected {}", headerText,
							expectedList.get(i));
					missingOptions.add(headerText);
				} else {

					logger.info("The option value {} is present in the homepage", headerText);
				}
			}
		}
		return missingOptions;
	}

	public void selectTripOption(String option) {

		boolean flag = false;
		for (WebElement optionElement : homePageObjects.tripElements) {

			String actualOption = optionElement.getText().trim();
			logger.debug("The option is: {}", actualOption);
			if (actualOption.equals(option)) {

				optionElement.click();
				flag = true;
				break;
			}
		}
		if (!flag) {

			throw new DataMismatchException("The option " + option + "is not present in the UI");
		} else {
			logger.info("Clicked on the option {}", option);
		}
	}

	public void enterFromToCity(String option, String cityName) {

		if (option.equalsIgnoreCase("From")) {

			clickFlightSelectOption("From");
			waitFactory.WaitForElementUntilClickable(homePageObjects.fromInputElement, 5).sendKeys(cityName);
			boolean isPresent = waitFactory.isTextPresent(homePageObjects.firstRoleOptionElement, cityName, 5);
			if (isPresent) {
				String firstRoleOption = homePageObjects.firstRoleOptionElement.getText();
				logger.debug("The first role option is {}", firstRoleOption);
				homePageObjects.firstRoleOptionElement.click();
				logger.info("Selected the from city {}", firstRoleOption);
			} else

				Assert.fail("The from city name is not the first one in the list: " + cityName);

		} else if (option.equalsIgnoreCase("To")) {
			clickFlightSelectOption("To");
			waitFactory.WaitForElementUntilClickable(homePageObjects.toInputElement, 5).sendKeys(cityName);
			boolean isPresent = waitFactory.isTextPresent(homePageObjects.firstRoleOptionElement, cityName, 5);
			if (isPresent) {
				String firstRoleOption = homePageObjects.firstRoleOptionElement.getText();
				logger.debug("The first role option is {}", firstRoleOption);
				homePageObjects.firstRoleOptionElement.click();
				logger.info("Selected the to city {}", firstRoleOption);
			} else
				Assert.fail("The to city name is not the first one in the list: " + cityName);

		} else
			throw new DataMismatchException("Invalid option: " + option + " Expected From or To");
	}

	public void selectTommorowDate() {

		String month = supportFactory.getTommorowMonth();
		logger.debug("The month to select is {}", month);
		String year = String.valueOf(supportFactory.getTommorowYear());
		logger.debug("The year to select is {}", year);
		String day = String.valueOf(supportFactory.getTommorowDay());
		logger.debug("The day to select is {}", day);
		clickFlightSelectOption("Departure");
		waitFactory.WaitForElementUntilClickable(homePageObjects.getDatePickerWebElement(month, year, day), 5);
		logger.info("Successfully selected tommorows date");
	}

	public void selectTravellersAndClass(int adult, int children, int infant, String travelClass) {
		
		clickFlightSelectOption("Travellers & Class");
		selectTravellAndClassOption("ADULT", String.valueOf(adult));
		selectTravellAndClassOption("CHILDREN", String.valueOf(children));
		selectTravellAndClassOption("INFANT", String.valueOf(infant));
		selectTravellClass(travelClass);
	}
}
