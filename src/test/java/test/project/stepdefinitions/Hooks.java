package test.project.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.utilities.DataProvider;
import com.project.utilities.SupportFactory;
import com.project.webdriverfactory.DriverManager;
import com.project.webdriverfactory.ThreadManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private Logger logger = LogManager.getLogger(Hooks.class);
	private SupportFactory support = new SupportFactory();
	private String isThreadSafe = DataProvider.getProperty("driver.thread.safe");

	@Before(order = 0)
	public void initialiseBrowser() {

		if (Boolean.parseBoolean(isThreadSafe)) {

			ThreadManager.setDriverThreadSafe();
			ThreadManager.getDriver();
		} else

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
			logger.debug("executed scenario {}, {}, status: {}", scenario.getName(), scenario.getUri(),
					scenario.getStatus());
	}

	@After(order = 0)
	public void closeBrowser() {

		if (Boolean.parseBoolean(isThreadSafe))
			ThreadManager.quitDriver();
		else
			DriverManager.quitDriver();
	}
}
