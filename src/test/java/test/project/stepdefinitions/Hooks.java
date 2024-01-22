package test.project.stepdefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.utilities.DataProvider;
import com.project.utilities.SupportFactory;
import com.project.webdriverfactory.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private Logger logger = LogManager.getLogger(Hooks.class);
	private SupportFactory support = new SupportFactory(); 
	
	@Before(order = 0)
	public void initialiseBrowser() {
		
		DriverManager.getDriver();
	}
	
	@Before(order = 1)
	public void launchURL() {
		
		DriverManager.launchURL(DataProvider.getProperty("login.url"));
		support.checkAndCloseAddPopUp();
	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		
		if (scenario.isFailed())
			support.takescreenshot(scenario.getName());
		else
			logger.debug("executed scenario {}, {}, status: {}", scenario.getName(), scenario.getUri(), scenario.getStatus());
	}
	
	@After(order = 0)
	public void closeBrowser() {
		
		DriverManager.quitDriver();
	}
}
