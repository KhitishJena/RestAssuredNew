package com.satish.StepDefinations;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() {
		System.out.println("This will run before the Scenario");
		
		StepDefinations stepDefinations = new StepDefinations();
		if(StepDefinations.placeId == null) {
			try {
				stepDefinations.add_place_payload_with("Satish", "English", "India");
				stepDefinations.user_call_with_http_request("ADDPLACEAPI", "POST");
				stepDefinations.verify_place_id_created_maps_to_using("Satish", "GETPLACEAPI");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
}
