Feature: Validating Place API's

Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI

	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user call "AddPlaceAPI" with post http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	
	Examples:
	|name|language|address|
	|Lulu|Odia|Barang|
	|Little|Sanskrit|Banglore|
	|Linu|Kannada|Kolkata|
	