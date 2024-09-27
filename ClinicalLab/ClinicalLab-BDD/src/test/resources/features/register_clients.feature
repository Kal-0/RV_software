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

Scenario: Accessing client registration without an active attendance number
		Given the attendant has clicked on "call next number"
		When the attendant tries to access the client registration page
		Then the system blocks access to the registration page
		And the system shows a message stating that an attendance number is required
  	
  Scenario: No available attendance numbers
     Given a "new" client attempting to get an attendance number
     When there are no numbers available
		 Then the system informs that no attendance numbers are available at the moment

Scenario: Calling numbers according to priority and order of arrival
    Given two "priority" clients and one "standard" client in the queue
    When the attendant clicks on "call next number"
    Then the system calls the first "priority" client in order of arrival
    When the attendant clicks on "call next number" again
    Then the system calls the second "priority" client in order of arrival
    When the attendant clicks on "call next number" once more
    Then the system calls the "standard" client in order of arrival
    But if there are not two priority clients in the queue,
		Then the system calls the "standard" client.
  	
Scenario: Canceling an attendance number when the client does not appear
    Given a client has been called for attendance
    When the client does not appear
    And the attendant cancels the attendance number
    Then the system cancels the current number
    And the system calls the next available number
