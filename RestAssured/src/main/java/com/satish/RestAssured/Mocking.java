package com.satish.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.satish.files.MockFiles;

import io.restassured.path.json.JsonPath;

@Test
public class Mocking {
	
	public void complexJson() {
		
		JsonPath js = new JsonPath(MockFiles.MockFile());

		String website = js.getString("dashboard.website");
		System.out.println("Website is: " + website);

		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println("The amount is: " + amount);

		int courses_count = js.getInt("courses.size()");
		System.out.println("Number of courses: " + courses_count);

		String course_Name = js.getString("courses[1].title");
		System.out.println(course_Name);
		System.out.println();

		int totalAmount = 0;
		for (int i = 0; i < courses_count; i++) {
			String courseName = js.getString("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");

			System.out.println("***" + courseName + "***");
			System.out.println("Each course price is: " + price);
			System.out.println("The number of copies sold: " + copies);
			System.out.println("The total price of each course: " + (price * copies));
			System.out.println();

			totalAmount = totalAmount + (price * copies);
		}
		for (int i = 0; i < courses_count; i++) {
			String courseName = js.getString("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");

			if (courseName.equalsIgnoreCase("Cypress")) {
				System.out.println("#######" + courseName + "#######");
				System.out.println("Each course price is: " + price);
				System.out.println("The number of copies sold: " + copies);
				System.out.println("The total price of each course: " + (price * copies));
				break;
			}
		}

		System.out.println();
		System.out.println("The final calculated total amount is: $ " + totalAmount);
		Assert.assertEquals(amount, totalAmount);

	}

}
