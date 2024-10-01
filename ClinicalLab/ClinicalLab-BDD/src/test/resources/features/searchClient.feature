Feature: Clients Search

Scenario: Searching for an existing client
    Given a registered client with CPF "12345678900" exists in the system
    And the attendant enters the client's CPF "12345678900" in the system
    When the system searches for the client
    Then the system shows a message stating that the client is already registered

Scenario: Searching for a CPF that is not registered
    Given the attendant enters a CPF "98765432100" in the system
    When the system searches for the CPF
    Then the system returns a message stating that no client was found
    And the system prompts the attendant to register a new client
