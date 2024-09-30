Feature: Clients Search

@tag1
Scenario Outline: Searching for a client
    Given the attendant enters the client's CPF "<cpf>" in the system
    When the system searches for the client
    Then the system returns <client_info>
    And <system_message>

    Examples: 
      | cpf           | client_info                       | system_message                                                |
      | 12345678900   | the client's information           | the system shows a message stating that the client is already registered |
      | 99999999999   | a message stating that no client was found | the system prompts the attendant to register a new client |
