package com.satish.OAuth;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.path.json.JsonPath;

public class OAuth {

	public static void main(String[] args) throws InterruptedException {

		String response = given()
							.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
							.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
							.formParams("grant_type", "client_credentials")
							.formParams("scope", "trust")
							.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);

		ResponsePojo response2 = given().queryParams("access_token", accessToken)
					.when()
					.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
					.then().log().all().extract().as(ResponsePojo.class);
					//System.out.println(response2);
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("*************************");
		System.out.println("P O J O - R E S P O N S E");
		System.out.println("*************************");
		
		System.out.println(response2.getLinkedIn());
		System.out.println(response2.getInstructor());
		System.out.println(response2.getExpertise());
		System.out.println(response2.getServices());
		System.out.println(response2.getUrl());
		
		System.out.println();
		
		System.out.println("*************************");
		System.out.println();
		System.out.println();
		System.out.println("*************************");
		
		System.out.println(response2.getCourses().getWebAutomation().get(1).getPrice());
		System.out.println(response2.getCourses().getWebAutomation().get(1).getCourseTitle());
		System.out.println();
		
		List<WebAutomation> courses = response2.getCourses().getWebAutomation();
		
		System.out.println("These are the courses present in the WebAutomation:-");
		for(int i =0; i<courses.size();i++) {
			
			System.out.println(courses.get(i).getCourseTitle());
        }
		
		System.out.println();
		for(int j =0; j<courses.size();j++) {
			
			if(courses.get(j).getCourseTitle().equalsIgnoreCase("Cypress")) {
				System.out.println(courses.get(j).getCourseTitle());
				System.out.println(courses.get(j).getPrice());
				break;
			}
		}

	}
}
