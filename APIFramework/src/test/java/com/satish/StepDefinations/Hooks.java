package com.satish.StepDefinations;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import java.io.FileWriter;
import java.io.IOException;

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

	@BeforeAll
	public static void loggingCleanUp(){
		String logFile = "logging.txt";
		try (FileWriter fw = new FileWriter(logFile, false)) ///if 'true'--> append mode, 'false'-->overwrite mode
		{
			// Opening with 'append = false' clears the file
			fw.write(""); // optional, ensures file is empty
			System.out.println("Log file cleared successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
