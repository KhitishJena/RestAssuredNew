package com.satish.files;

public class Library {
	
	public static String AddBookPayload(String isbn, String aisle) {
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Satish foe\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String DeleteBookPayload(String id) {
		 return "{\r\n"
		 		+ " \r\n"
		 		+ "\"ID\" : \""+id+"\"\r\n"
		 		+ " \r\n"
		 		+ "} \r\n"
		 		+ "";
	}


}
