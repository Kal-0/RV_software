Feature: Clients Register

  Scenario: Registering a client with all the data
    Given a new client is being registered in the system
    When the client suply valid information for his register
    Then the client is registered in the system successfully

  Scenario: Registering a client with incomplete data
		Given a new client is being registered in the system
		When the client does not provide all the required data
		Then the system should return an error message informing the missing data