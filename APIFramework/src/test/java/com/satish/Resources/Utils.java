package com.satish.Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {
		
		if (req == null) {
  		PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();  
		return req;
		} else {
			return req;
		}
	}
	
      public static String getGlobalValues(String key) throws IOException {
     	  Properties prop = new Properties();
     	  FileInputStream fis = new FileInputStream("C:\\Users\\K H I T I S H\\git\\repository\\APIFramework\\src\\test\\java\\com\\satish\\Resources\\global.properties");
    	  prop.load(fis);
    	  String value = prop.getProperty(key);
    	  return value;
      }

      public String getJsonPathValue(Response response, String key) {
    	  
    	  String appPlaceResponseString = response.asString();
  		  JsonPath js = new JsonPath(appPlaceResponseString);
		  String value = js.get(key).toString();
		  System.out.println("Value for the key " + key + " is: " + value);
		  return value;
		  
	  }
}
