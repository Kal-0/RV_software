Feature: Create Exam Request

  Scenario: Successfully creating a new exam request
    Given a client without a registered exam requests
    When the attendant submits a new exam request for "blood test"
    Then the system saves the exam request
    And the system shows a confirmation message

  Scenario: Trying to register the same exam request twice
    Given a client with an existing exam request
    When the attendant tries to register the same exam request again
    Then the system shows an error message indicating that the request already exists
    And the system prevents duplicate submissions

