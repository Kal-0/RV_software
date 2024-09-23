Feature: Request Validation

  Scenario: Requesting blood tests with health insurance
  	Given a "new" client with "health insurance"
		And the attendant selects "T3", "Cholesterol", and "Glucose" tests for the client
		When the attendant confirms the test request
		Then the system calculates the total cost based on insurance coverage
   	And the system registers a total of "3" tests in the request
   	And the system notifies the attendant that the request is registered successfully
  
Scenario: Requesting blood tests with private payment
		Given a "new" client with "private payment"
		And the attendant selects "Glucose", "T3", "T4", "Triglycerides", "Blood count", "C-reactive protein" tests for the client
		When the attendant confirms the test request
		Then the system calculates the total cost without discounts
		And the system registers a total of "6" tests in the request
		And the system notifies the attendant that the request is registered successfully
		
Scenario: Modifying the blood test request
		Given a "new" client with an existing request for "CBC", "Cholesterol", and "Glucose" tests
		And the client decides to change the "Glucose" test to "Thyroid" test
		When the attendant updates the request
		Then the system updates the test request to include "CBC", "Cholesterol", and "Thyroid" tests
		And the total cost is recalculated accordingly
		And the system registers a total of "3" tests in the updated request
		And the system notifies the attendant that the request has been updated successfully
		
Scenario: Completing payment and joining the blood draw waiting list
		Given a "new" client with a registered test request for "T3", "Blood count", and "C-reactive protein" tests
		And the attendant selects "private payment" and completes the payment for the client
		When the payment is confirmed
		Then the system assigns the client to the blood draw waiting list
		And the client is placed in the correct position in the queue
		And the request status is updated to "waiting" with a unique "request number"
		
Scenario: Viewing the blood test request and confirming that the tests were collected
		Given a "new" client with a completed payment for "Triglycerides", "Cholesterol", and "Glucose" tests
		When the attendant accesses the "Client Requests" section
		Then the system displays the receipt with the "exam request number", "client name", and "exam date", "total of blood tests"
		And the attendant can confirm that the tests were collected successfully
		
Scenario: Error when trying to register the same test request twice
		Given a "new" client with a registered test request for "Covid-19", "Cholesterol", and "Glucose" tests
		When the attendant tries to register the same test request again for the client
		Then the system displays an error message indicating that the request already exists
		And the system prevents the duplicate request from being submitted
