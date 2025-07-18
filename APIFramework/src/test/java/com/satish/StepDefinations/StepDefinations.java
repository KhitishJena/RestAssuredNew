package com.satish.StepDefinations; 

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.satish.PlacePojo.Location;
import com.satish.PlacePojo.PlaceInput;
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
	
	@Given("Add Place Payload")
	public void add_place_payload() {

		req_Body=  given().log().all().spec(requestSpecification()).body(testData.AddPlacePayload());
	}
	
	@When("user call {string} with post http request")
	public void user_call_with_post_http_request(String string) {
		res = new ResponseSpecBuilder().expectStatusCode(200).build();

		addPlaceresponse = req_Body
				.when().post("/maps/api/place/add/json")
				.then().log().all().spec(res)
				.extract().response();
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
