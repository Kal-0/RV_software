Feature: Clients Search

Scenario: Searching for an existing client
    Given the attendant enters the client's CPF in the system
    When the system searches for the client
    And the client is registered 
    Then the system shows a message stating that the client is already registered
    

Scenario: Searching for CPF 
    Given the attendant enters a CPF in the system
    When the system searches for the CPF
    And the CPF isn`t from a regsitered client
    Then the system returns a message stating that no client was found
    And the system prompts the attendant to register a new client
