Feature: Create Exam Request

  Scenario: Successfully creating a new exam request
    Given a client without a registered exam requests
    When the attendant completes a new exam request
    And change the exam request status to "Waiting for collection"
    Then the system saves the exam request

  Scenario: Trying to register the same exam request twice
    Given a client with an existing exam request
    When the attendant tries to register the same exam request again
    Then the system shows an error message indicating that the request already exists
    And the system prevents duplicate submissions

