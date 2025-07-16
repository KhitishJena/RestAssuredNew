package com.satish.Serialization;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

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

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
									.addQueryParam("key", "qaclick123")
									.setContentType(ContentType.JSON).build();

		RequestSpecification req_Body = given().log().all().spec(req).body(pi);

		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();

		//Add
		String response = req_Body
				.when().post("/maps/api/place/add/json")
				.then().log().all().spec(res)
				.extract().response().asString();

		JsonPath jp = new JsonPath(response);
		String placeID = jp.getString("place_id");

		System.out.println(placeID);

	}
}
