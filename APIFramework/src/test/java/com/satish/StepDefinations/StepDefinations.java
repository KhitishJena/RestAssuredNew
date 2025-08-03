package com.satish.StepDefinations; 

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.satish.PlacePojo.Location;
import com.satish.PlacePojo.PlaceInput;
import com.satish.Resources.APIResources;
import com.satish.Resources.TestDataBuild;
import com.satish.Resources.Utils;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinations extends Utils {
	
	RequestSpecification req_Body ;
	ResponseSpecification res;
	Response addPlaceresponse;
	JsonPath jp ;
	TestDataBuild testData = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		req_Body=  given().log().all().spec(requestSpecification()).body(testData.AddPlacePayload(name,language,address));
	}
	
	@When("user call {string} with {string} http request")
	public void user_call_with_post_http_request(String resource, String httpMethod) throws IOException {
		res = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		// This is to set the base URI for the request based on the API being called
		
		APIResources resourceAPI = APIResources.valueOf(resource);

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
			addPlaceresponse = req_Body
					.when().post(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			addPlaceresponse = req_Body
					.when().get(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			addPlaceresponse = req_Body
					.when().delete(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			addPlaceresponse = req_Body
					.when().put(resourceAPI.getResource());
		}
		
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
	    System.out.println("satish");
		assertEquals(addPlaceresponse.getStatusCode(), statusCode);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_os(String key, String value) {
		System.out.println("Khitish");
		String appPlaceResponseString = addPlaceresponse.asString();
		JsonPath js = new JsonPath(appPlaceResponseString);
		assertEquals(js.get(key).toString(), value);
	}


}
