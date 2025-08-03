Feature: Validating Place API's

@AddPlace @End2End
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI

	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user call "ADDPLACEAPI" with "post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GETPLACEAPI"
	
	Examples:
	|name|language|address|
	|Lulu|Odia|Barang|
	#|Little|Sanskrit  |Banglore|
	#|Linu  |Kannada   |Kolkata |

@End2End @DeletePlace	
Scenario: Verify if Delete Place functionality is working
	
	Given Delete Place Payload
    When user call "DELETEPLACEAPI" with "delete" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
	


	