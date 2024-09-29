Feature: Clients Register

 Scenario: Registering a client with correct data
    Given a "new" client with "correct" data
    When an attendant registers the client
    Then the system registers the client successfully
    And the system notifies that the client is registered

  Scenario: Registering a client with incomplete data
     Given a "new" client with "incorrect" data
     When an attendant registers the client
     Then the system returns an error message informing the incorrect data

  Scenario: Try client registration without an active service number
    Given the attendant is registering a client without an active service number
    When the attendant tries to complete the registration
    Then the system returns an error message stating that there is no active service number
    And the system shows a message stating that a service number is required
     