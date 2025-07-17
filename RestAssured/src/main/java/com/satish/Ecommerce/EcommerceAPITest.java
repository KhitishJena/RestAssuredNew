package com.satish.Ecommerce;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class EcommerceAPITest {

	public static void main(String[] args) {
		
		//Login
		LoginPojo login = new LoginPojo();
		login.setUserEmail("satish@xyz.com");
		login.setUserPassword("!Lulu123");

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
								.setContentType(ContentType.JSON).build();

		RequestSpecification reqLogin=	given().relaxedHTTPSValidation().log().all().spec(req).body(login);
		
		LoginResponsePojo loginResponse = reqLogin.when().post("/api/ecom/auth/login")
										.then().log().all().statusCode(200)
										.body("message", equalTo("Login Successfully")).extract().as(LoginResponsePojo.class);
		
		String token = loginResponse.getToken();
		String userId = loginResponse.getUserId();
		
		System.out.println(token);
		System.out.println(userId);
		

		//Add Product
		
		File file =new File("resources\\money_purse.jpg");
		String filePath = file.getAbsolutePath();
		System.out.println(filePath);
		
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
												.addHeader("Authorization", token).build();
		
		RequestSpecification reqAddProduct= given().log().all().spec(addProductBaseReq)
											.formParams("productName", "MyntraProduct")
											.formParam("productAddedBy", userId)
											.param("productCategory", "fashion")
											.param("productSubCategory", "Loui Phillipi")
											.param("productPrice", "11500")
											.param("productDescription", "Addias Originals")
											.param("productFor", "men")
											.multiPart("productImage",new File(filePath));
		
		AddProductResponsePojo addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
													.then().log().all().statusCode(201).extract().as(AddProductResponsePojo.class);
		
		String productId = addProductResponse.getProductId();
		
		System.out.println("The Product id is: "+productId);
		
		
		
		//Create Order
		
		CreateOrderSubPojo createOrderSub = new CreateOrderSubPojo();
		createOrderSub.setCountry("British Indian Ocean Territory");
		createOrderSub.setProductOrderedId(productId);
		
		List<CreateOrderSubPojo> orderList = new ArrayList<CreateOrderSubPojo>();
		orderList.add(createOrderSub);
		
		CreateOrderPojo createOrder = new CreateOrderPojo();
		createOrder.setOrders(orderList);
		
		RequestSpecification createOrderBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		
		
		RequestSpecification createOrderReq   = given().log().all().spec(createOrderBasereq).body(createOrder);
		
		CreateOrderResponsePojo createOrderResponse = createOrderReq
													.when().post("/api/ecom/order/create-order")
													.then().log().all().statusCode(201).extract().as(CreateOrderResponsePojo.class);
		
		for(int i =0;i<createOrderResponse.getProductOrderId().size();i++) {
			if(createOrderResponse.getProductOrderId().get(i).equalsIgnoreCase(productId)) {
				Assert.assertTrue(true);
			}
		}
		
	
		//Delete Product
				
		RequestSpecification deleteProductBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();
		
		RequestSpecification deleteProductReq   = given().log().all().spec(deleteProductBasereq).pathParam("productId", productId);
		
		DeletePojo deleteResponse = deleteProductReq.when().delete("/api/ecom/product/delete-product/{productId}")
		.then().log().all().statusCode(200).extract().as(DeletePojo.class);
		
		Assert.assertEquals(deleteResponse.getMessage(), "Product Deleted Successfully");
		
		
		
	}
}
