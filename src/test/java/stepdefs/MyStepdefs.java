package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.Steps;

public class MyStepdefs {
    Steps steps = new Steps();

    @When("User search {string} with search by country endpoint")
    public void userSearchWithSearchByCountryEndpoint(String countryName) {
        steps.searchWithName(countryName);
    }

    @Then("User should see the relevant {string} result")
    public void userShouldSeeTheRelevantSearchResult(String countryName) {
        steps.checkSearchWithNameResponse(countryName);
    }
}
