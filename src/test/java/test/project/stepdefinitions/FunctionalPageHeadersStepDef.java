package test.project.stepdefinitions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.project.pageobjects.appmodules.HomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class FunctionalPageHeadersStepDef {
	
	private Logger logger = LogManager.getLogger(FunctionalPageHeadersStepDef.class);
	private HomePage homePage = new HomePage();

	@Given("user is on the {string} login page")
	public void userIsOnTheMakeMyTripLoginPage(String expectedTitle) {
		
		String actualTitle = homePage.getTitle();
		logger.debug("Titles: expected - {} and actual - {}", expectedTitle, actualTitle);
		Assert.assertTrue("The titles are not equal", actualTitle.contains(expectedTitle));
		logger.info("The titles are equal");
	}
	
	@Then("user is able to see the make my trip logo icon")
	public void userIsAbleToSeeTheMakeMyTripLogoIcon() {
	 
		Assert.assertTrue("The log icon is not present", homePage.getLogo());
		logger.info("The logo is present in the home page");
	}
	
	@And("the following page headers are visible")
	public void theFollowingPageHeadersAreVisible(DataTable dataTable){
		
		List<String> missingHeadersList = homePage.verifyFunctionalHeaders(dataTable.asList());
		Assert.assertEquals("The following headers are not present in the home page: "+missingHeadersList, 0, missingHeadersList.size());
	}
}
