package com.satish.RestAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.satish.files.Library;

import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test(dataProvider = "BookData")
	public void AddBook(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";

		// String isbn = "satish";
		// String aisle = "671412";
		String addBookResponse = given().log().all().header("Content-Type", "application/json")
				.body(Library.AddBookPayload(isbn, aisle))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath js = new JsonPath(addBookResponse);
		String message = js.getString("Msg");
		String book_id = js.getString("ID");
		Assert.assertEquals("successfully added", message);
		Assert.assertEquals(book_id, isbn + aisle);
		// return book_id;

	}

	@Test(dataProvider = "BookData")
	public void DeleteBook(String isbn, String aisle) {

		String book_id = isbn + aisle;
		RestAssured.baseURI = "http://216.10.245.166";

		given().log().all().header("Content-Type", "application/json")
		.body(Library.DeleteBookPayload(book_id))
		.when().post("/Library/DeleteBook.php")
		.then().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));

	}

	@DataProvider(name = "BookData")
	public Object[][] data() {

		Object data[][] = new Object[3][2];
		data[0][0] = "Satish";
		data[0][1] = "123";
		data[1][0] = "Little";
		data[1][1] = "123";
		data[2][0] = "Linu";
		data[2][1] = "123";
		return data;
	}

}
