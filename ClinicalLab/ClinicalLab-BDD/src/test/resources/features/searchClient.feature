Feature: Clients Search

Scenario: Searching for a client that already exists
    Given the attendant enters the client's CPF and the client already exists
    When the system searches for the client
    Then the system returns the client's information
    And the system shows a message stating that the client is already registered
    
Scenario: Searching for a client that does not exist
    Given the attendant enters the client's CPF and the client does not exist
    When the system searches for the client
    Then the system returns a message stating that no client was found
    And the system prompts the attendant to register a new client
    