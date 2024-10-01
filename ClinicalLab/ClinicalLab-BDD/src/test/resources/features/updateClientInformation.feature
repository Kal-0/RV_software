Feature: Client Information Update

Scenario Outline: Update client email
    Given there is a client with ID <id> and CPF "<cpf>" in the system
    And the attendant searches for the client by CPF "<cpf>"
    When the attendant updates the client's email to "<email>"
    Then the system should display the message "<system_message>"

Examples: 
    | id | cpf         | email               | system_message                        |
    |  1 | 12345678900 | newemail@example.com | The client information is updated.   |
 