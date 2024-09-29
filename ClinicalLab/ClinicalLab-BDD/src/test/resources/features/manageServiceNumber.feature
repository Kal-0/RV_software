Feature: Manage Service Number

Scenario: Calling numbers when both "priority" and "standard" service numbers are in the queue
    Given two "priority" service numbers and one "standard" service number in the queue
    When the attendant requests the next number
    Then the system calls the first "priority" service number in order of arrival
    When the attendant requests the next number again
    Then the system calls the second "priority" service number in order of arrival
    When the attendant requests the next number once more
    Then the system calls the "standard" service number in order of arrival
    
    Scenario: Calling numbers when only "priority" service numbers are in the queue
    Given two "priority" service numbers in the queue
    When the attendant requests the next number
    Then the system calls the first "priority" service number in order of arrival
    When the attendant requests the next number again
    Then the system calls the second "priority" service number in order of arrival
    
    Scenario: Calling numbers when only "standard" service numbers are in the queue
    Given two "standard" service numbers in the queue
    When the attendant requests the next number
    Then the system calls the first "standard" service number in order of arrival
    When the attendant requests the next number again
    Then the system calls the second "standard" service number in order of arrival
    
   Scenario: Calling a number when the queue is empty
    Given the queue is empty
    When the attendant requests the next number
    Then the system returns a message indicating that there are no service numbers in the queue
   
   Scenario: Calling numbers with one "priority" service number, several "standard" numbers, and a new "priority" number created during the process
    Given one "priority" service number and three "standard" service numbers in the queue
    When the attendant requests the next number
    Then the system calls the "priority" service number in order of arrival
    When the attendant requests the next number again
    Then the system calls the first "standard" service number in order of arrival
    When the attendant requests the next number once more
    And a new "priority" service number is created
    Then the system calls the newly created "priority" service number
    When the attendant requests the next number again
    Then the system calls the second "standard" service number in order of arrival
   