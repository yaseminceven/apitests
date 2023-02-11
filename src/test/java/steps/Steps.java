package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import java.util.List;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Steps {
    private static final String BASE_URL = "https://restcountries.com/v3.1/";
    private static final String fieldsFilter = "?fields=name,capital,currencies";
    private static ValidatableResponse response;

    public void setBaseURI() {
        RestAssured.baseURI = BASE_URL;
    }

    public void searchWithEndpoint(String endpointName, String keyword) {
        logIfGetRequestFailed(BASE_URL+endpointName+"/"+keyword);
        response = given().when().get(endpointName+"/"+keyword+fieldsFilter).then().contentType(ContentType.JSON);
    }

    public void checkSearchWithNameResponse(String countryName) {
        boolean statusCheck = checkResponseStatus(200);
        boolean bodyCheck = checkBodyOfResponse("name.common",countryName);
        Assert.assertTrue(statusCheck&&bodyCheck);
    }

    private boolean checkResponseStatus(int status){
        return response.extract().statusCode() == status;
    }

    private void logIfGetRequestFailed(String endpoint){
        when().get(endpoint).then().log();
    }

    private boolean checkBodyOfResponse(String path,String name){
        boolean result = true;
        List<String> responseList = response.extract().body().jsonPath().getList(path);
        for (String item: responseList) {
            result = item.toLowerCase(Locale.ROOT).contains(name);
        }
        return result;
    }

    public void checkCurrency(String currencyName) {
        boolean statusCheck = checkResponseStatus(200);
        boolean bodyCheck = checkBodyOfResponse("currencies.COP.name",currencyName);
        Assert.assertTrue(statusCheck&&bodyCheck);
    }
}
