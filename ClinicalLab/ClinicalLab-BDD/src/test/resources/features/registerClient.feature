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

Scenario: Registering a client that already exists
     Given a "returning" client with "correct" data
     When an attendant registers the client
     Then the system returns an error message stating the client is already registered