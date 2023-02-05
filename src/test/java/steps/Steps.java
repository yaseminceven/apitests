package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Steps {
    private static final String BASE_URL = "https://restcountries.com/v3.1/";
    private static ValidatableResponse response;

    public void searchWithName(String countryName) {
        RestAssured.baseURI = BASE_URL;
        logIfGetRequestFailed(BASE_URL+"name/"+countryName);
        response = given().when().request("GET",BASE_URL+"name/"+countryName).then().contentType(ContentType.JSON);
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
        when().get(BASE_URL+endpoint).then().log();
    }

    private boolean checkBodyOfResponse(String path,String name){
        return response.extract().body().jsonPath().getString(path).toLowerCase(Locale.ROOT).contains(name);
    }
}
