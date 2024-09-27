Feature: Request Validation

  Scenario: Requesting blood tests with health insurance
  	Given a "new" client with "health insurance"
		And the attendant selects "tests" for the client
		When the attendant confirms the test request
		Then the system calculates the total cost based on insurance coverage
   	And the system registers a total of "number_of_tests" tests in the request
   	And the system notifies the attendant that the request is registered successfully
  
Scenario: Requesting blood tests with private payment
		Given a "new" client with "private payment"
		And the attendant selects "tests" for the client
		When the attendant confirms the test request
		Then the system calculates the total cost without discounts
		And the system registers the requested tests
		And the system notifies the attendant that the request has been successfully registered
		
Scenario: Modifying the blood test request
		Given a "new" client with an existing request for blood test
		And the client decides to change the "Test_A" to "Test_B" 
		When the attendant updates the request
		Then the system updates the test request to include "updated_tests" 
		And the total cost is recalculated
		And the system notifies the attendant that the request has been successfully updated
		
Scenario: Completing payment and joining the blood draw waiting list
		Given a "new" client with a registered test request for "tests" tests
		And the attendant selects "private payment" and completes the payment
		When the payment is confirmed
		Then the system assigns the client to the blood draw waiting list
		And the client is placed in the correct queue position
		And the request status is updated to "waiting"
		
Scenario: Viewing the blood test requests and confirming  test collection
		Given a "new" client with a completed payment for "tests" tests
		When the attendant accesses the "Client Requests" section
		Then the system shows the details of the "request number", "client name", and "exam date"
		And the attendant can confirm whether the tests were successfully collected
		
Scenario: Error when trying to register the same test request twice
		Given a "new" client with a registered test request for "tests" tests
		When the attendant tries to submit the same test request again
		Then the system shows an error message indicating that the request already exists
		And the system prevents duplicate submissions
