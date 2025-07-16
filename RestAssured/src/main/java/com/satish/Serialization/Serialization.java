package com.satish.Serialization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Serialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PlaceInput pi = new PlaceInput();
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
		
		Location loc = new Location();
		loc.setLat(45.4564);
		loc.setLng(-67.12356);
		pi.setLocation(loc);

		RestAssured.baseURI= "https://rahulshettyacademy.com";
		//Add
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(pi)
				.when().post("/maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.extract().response().asString();

		JsonPath jp = new JsonPath(response);
		String placeID = jp.getString("place_id");

		System.out.println(placeID);

	}

}
