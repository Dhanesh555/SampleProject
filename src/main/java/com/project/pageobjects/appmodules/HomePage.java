package com.project.pageobjects.appmodules;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.project.exceptionhandler.DataMismatchException;
import com.project.pageobjects.HomePageObjects;
import com.project.webdriverfactory.DriverManager;

public class HomePage {

	HomePageObjects homePageObjects = new HomePageObjects();
	private WebDriver driver = DriverManager.getDriver();
	private Logger logger = LogManager.getLogger(HomePage.class);
	
	public String getTitle() {
		
		return driver.getTitle();
	}
	
	public boolean getLogo() {
		
		return homePageObjects.logoElement.isDisplayed();
	}
	
	public List<String> verifyFunctionalHeaders(List<String> expectedHeaders) {
		
	    List<String> missingHeaders = new ArrayList<>();
	    if (expectedHeaders.size() != homePageObjects.headerElements.size()) {
	    	
	    	logger.error("The actual and expected header counts are not matching");
	    	throw new DataMismatchException("The actual and expected header counts are not matching");
	    } else {
	    	
	    	for (int i = 0; i < expectedHeaders.size(); i++){
		    	
		    	String headerText = homePageObjects.headerElements.get(i).getText().trim();
		    	if (! (headerText.equals(expectedHeaders.get(i)))) {
		    		
		    		logger.error("The actual and expected headers are not equal actual {}, expected {}", headerText,expectedHeaders.get(i));
		    		missingHeaders.add(headerText);
		    	} else {
		    		
		    		logger.info("The header value {} is present in the homepage", headerText);
		    	}
		    }
	    }
	    return missingHeaders;
	}
}
