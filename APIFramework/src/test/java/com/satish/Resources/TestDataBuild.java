package com.satish.Resources;

import java.util.ArrayList;
import java.util.List;

import com.satish.PlacePojo.Location;
import com.satish.PlacePojo.PlaceInput;

public class TestDataBuild {

	public PlaceInput AddPlacePayload(String name, String language, String address) {
		PlaceInput pi = new PlaceInput();
		pi.setAccuracy(30);
		pi.setAddress(address);
		pi.setLanguage(language);
		pi.setName(name);
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
		
		return pi;

	}
	
	public String DeletePlacePayload(String placeId) {
		String dp = "{\r\n"
				+ "\"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "";
		return dp;
	}
}
