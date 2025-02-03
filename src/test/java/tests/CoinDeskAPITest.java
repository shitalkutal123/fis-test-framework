/**
 * 
 */
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * 
 */
public class CoinDeskAPITest {
	
	@Test
    public void validateBPI() {
        // Send GET request to the API endpoint
        Response response = RestAssured.get("https://api.coindesk.com/v1/bpi/currentprice.json");

        // Verify the response status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // Parse the response body as JSON
        String responseBody = response.getBody().asString();

        // Verify that the response contains 3 BPIs: USD, GBP, and EUR
        Assert.assertTrue(responseBody.contains("\"USD\""), "Response should contain USD");
        Assert.assertTrue(responseBody.contains("\"GBP\""), "Response should contain GBP");
        Assert.assertTrue(responseBody.contains("\"EUR\""), "Response should contain EUR");

        // Verify that the GBP description equals 'British Pound Sterling'
        String gbpDescription = response.jsonPath().getString("bpi.GBP.description");
        Assert.assertEquals(gbpDescription, "British Pound Sterling", "GBP description should be 'British Pound Sterling'");
    }

}
