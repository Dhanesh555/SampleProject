package test.project.stepdefinitions;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchOneDayFlightStepDef {
    @Given("the home page has the following radio buttons")
    public void theHomePageHasTheFollowingRadioButtons(DataTable datatable) {

    }

    @When("the user click on the one way radio button")
    public void theUserClickOnTheOneWayRadioButton() {
    }
    
    @And("Search for the flights with {string} {string} {int} {int} {int} {string} details and departure date current date")
    public void andSearchForFlights(String from, String to, int adult, int children, int infant, String travelClass) {
        
    }

    @Then("the user should be able to get the result flights from {string} to {string}")
    public void thenTheUserShouldGetResultFlights(String from, String to) {
        
    }
}
