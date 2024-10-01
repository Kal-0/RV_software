Feature: Clients Search

@tag1
Scenario Outline: Searching for a client
    Given that there is a client with the id "<id>", CPF "<cpf>" in the system
   	And the attendant enters the client's CPF "<cpf>" in the system
    When the system searches for the client
    Then the system shows "<system_message>"
    
    Examples: 
      | id| cpf             | system_message 											|                                      
      | 1 | 12345678900     | client found												|
			| 2 | 99999999999     | No client found with the given CPF. |
