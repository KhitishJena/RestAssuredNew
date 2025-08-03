package com.satish.StepDefinations; 

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import com.satish.Resources.APIResources;
import com.satish.Resources.TestDataBuild;
import com.satish.Resources.Utils;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinations extends Utils {
	
	RequestSpecification req_Body ;
	ResponseSpecification res;
	Response response;
	JsonPath jp ;
	TestDataBuild testData = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		req_Body=  given().log().all().spec(requestSpecification()).body(testData.AddPlacePayload(name,language,address));
	}
	
	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String httpMethod) throws IOException {
		res = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		// This is to set the base URI for the request based on the API being called
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println("**********----> "+resourceAPI.getResource());
		
		/*
		if (string.equalsIgnoreCase("AddPlaceAPI")) {
			req_Body.spec(new RequestSpecBuilder().setBaseUri(Utils.getGlobalValues("baseUrl")).build());
		} else if (string.equalsIgnoreCase("GetPlaceAPI")) {
			req_Body.spec(new RequestSpecBuilder().setBaseUri(Utils.getGlobalValues("baseUrl")).build());
		} else if (string.equalsIgnoreCase("DeletePlaceAPI")) {
			req_Body.spec(new RequestSpecBuilder().setBaseUri(Utils.getGlobalValues("baseUrl")).build());
		} else if (string.equalsIgnoreCase("UpdatePlaceAPI")) {
			req_Body.spec(new RequestSpecBuilder().setBaseUri(Utils.getGlobalValues("baseUrl")).build());
		}
		*/
		if(httpMethod.equalsIgnoreCase("POST")) {
			response = req_Body
					.when().post(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			response = req_Body
					.when().get(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			response = req_Body
					.when().delete(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			response = req_Body
					.when().put(resourceAPI.getResource());
		}
		
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
	    System.out.println("satish");
		assertEquals(response.getStatusCode(), statusCode);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_os(String key, String expectedValue) {
		System.out.println("Khitish");
		assertEquals(getJsonPathValue(response, key), expectedValue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    
		String placeId = getJsonPathValue(response, "place_id");		
	    req_Body = given().spec(requestSpecification()).queryParam("place_id", placeId);
	    
	    user_call_with_http_request(resource,"GET");
	    String actualName = getJsonPathValue(response, "name");
	    assertEquals(actualName, expectedName);
	}



}
