package com.satish.Serialization; 

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PlaceInputPOJO pi = new PlaceInputPOJO();
		pi.setAccuracy(30);
		pi.setAddress("AE 302");
		pi.setLanguage("Hindi");
		pi.setName("Satish");
		pi.setPhone_number("1234567890");
		pi.setWebsite("www.satish.com");

		List<String> myTypesList = new ArrayList<String>();
		myTypesList.add("paper-Plate");
		myTypesList.add("oil");
		myTypesList.add("grocery");
		pi.setTypes(myTypesList);

		LocationPOJO loc = new LocationPOJO();
		loc.setLat(45.4564);
		loc.setLng(-67.12356);
		pi.setLocation(loc);

		/*
		// ✅ Using RequestSpecBuilder
		// RequestSpecBuilder is a builder class that allows you to configure
			a RequestSpecification step by step (base URI, query params, headers, etc.).
		// IMPORTANT: You must call .build() to convert the builder into a usable RequestSpecification object.
		// Without .build(), you only have the builder, not the actual specification object.
		*/
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
									.addQueryParam("key", "qaclick123")
									.setContentType(ContentType.JSON).build();
		/*
		// ✅ Using given()
		// 'given()' directly returns a RequestSpecification object.
		// Since it already provides a usable RequestSpecification object, you do NOT need to call .build().
		// Here we enrich the specification by attaching logging and request body.
		*/
		RequestSpecification req_Body = given().log().all().spec(req).body(pi);

		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();

		//Add
		Response response = req_Body
				.when().post("/maps/api/place/add/json")
				.then().log().all().spec(res)
				.extract().response();

		String responseString = response.asString();
		JsonPath jp = new JsonPath(responseString);
		String placeID = jp.getString("place_id");

		System.out.println(placeID);

	}
}
