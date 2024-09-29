Feature: Create Exam Request

Scenario: Error when trying to register the same test request twice
		Given a "new" client with a registered test request for "tests" tests
		When the attendant tries to submit the same test request again
		Then the system shows an error message indicating that the request already exists
		And the system prevents duplicate submissions