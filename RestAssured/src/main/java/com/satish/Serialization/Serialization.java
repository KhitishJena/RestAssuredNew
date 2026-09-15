package com.satish.Serialization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Serialization {

	public static void main(String[] args) throws JsonProcessingException {
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

		System.out.println("**************************");
		System.out.println("**************************");

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


		System.out.println("**************************");
		System.out.println("D E S E R I A L I Z E");
		System.out.println("**************************");

		ObjectMapper mapper = new ObjectMapper();
		OptResDeserializationPOJO ord = mapper.readValue(response, OptResDeserializationPOJO.class);    //// Deserialize JSON into POJO
		System.out.println(ord.getPlace_id());
		System.out.println(ord.getStatus());

	}

}
