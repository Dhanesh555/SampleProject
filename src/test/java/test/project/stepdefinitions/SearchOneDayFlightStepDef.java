package test.project.stepdefinitions;

import com.project.pageobjects.appmodules.HomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchOneDayFlightStepDef {
	
	HomePage homePage = new HomePage();
	
    @Given("the home page has the following radio buttons")
    public void theHomePageHasTheFollowingRadioButtons(DataTable datatable) {
    	
    	homePage.verifyTripOptions(datatable.asList());
    }

    @When("the user click on the {string} radio button")
    public void theUserClickOnTheOneWayRadioButton(String option) {
    	
    	homePage.selectTripOption(option);
    }
    
    @And("Search for the flights with {string} {string} {int} {int} {int} {string} details and departure date current date")
    public void andSearchForFlights(String from, String to, int adult, int children, int infant, String travelClass) {
        
    	homePage.enterFromToCity("from", from);
    	homePage.enterFromToCity("to", to);
    	homePage.selectTommorowDate();
    	homePage.selectTravellersAndClass(adult, children, infant, travelClass);
    }

    @Then("the user should be able to get the result flights from {string} to {string}")
    public void thenTheUserShouldGetResultFlights(String from, String to) {
        
    }
}
