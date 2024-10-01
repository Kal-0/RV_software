Feature: Clients Search

@tag1
Scenario Outline: Searching for a client
    Given that there is a client with the CPF "<cpf>" in the system
   	And the attendant enters the client's CPF "<cpf>" in the system
    When the system searches for the client
    Then the system shows a message stating that the client is already registered
    
    Examples: 
      | cpf           | client_info                       | system_message                                                |
      | 12345678900   | the client's information           | the system shows a message stating that the client is already registered |

