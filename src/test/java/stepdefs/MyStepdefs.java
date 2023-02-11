package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.Steps;

public class MyStepdefs {
    Steps steps = new Steps();

    @Given("User can send request")
    public void userCanSendRequest() {
        steps.setBaseURI();
    }

    @When("User search {string} with {string} endpoint")
    public void userSearchWithEndpoint(String keyword,String endpoint) {
        steps.searchWithEndpoint(endpoint,keyword);
    }

    @Then("User should see the relevant {string} result")
    public void userShouldSeeTheRelevantSearchResult(String countryName) {
        steps.checkSearchWithNameResponse(countryName);
    }

    @Then("User should see {string} inside currency name")
    public void userShouldSeeInsideCurrencyName(String currencyName) {
        steps.checkCurrency(currencyName);
    }
}
