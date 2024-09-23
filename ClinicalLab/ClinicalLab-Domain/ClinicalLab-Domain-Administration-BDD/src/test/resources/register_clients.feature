Feature: Clients Register

  Scenario: Registering a client with correct data
    Given a "new" client with "correct" data
    When an attendant registers the client
    Then the system registers the client successfully
    And the system notify the register of the client

  Scenario: Registering a client with incomplete data
		Given a "new" client with "incorrect" data
		When an attendant registers the client
		Then the system returns an error message informing the incorrect data
		
	Scenario: Registering a client that already exists
		Given a "old" client with "correct" data
		When an attendant registers the client
		Then the system returns an error message informing that the client is already registered
		
	Scenario: Attempting to access the client registration page without exist attendance number
  	Given the attendant has clicked on "call next number"
  	When the attendant tries to access the client registration page
  	Then the system does not allow access to the registration page
  	And the system displays a message stating that an attendance number is required
  	
  Scenario: No available attendance numbers
  	Given a "new" client trying to get an attendance number
  	When there are no attendance numbers available
  	Then the system informs that there are no available attendance numbers at the moment
  	
	Scenario: Calling numbers according to priority and order of arrival
		Given two "priority" clients and one "standard" client in the queue
  	When the staff clicks on "call next number"
  	Then the system calls the first "priority" client by order of arrival
 		When the staff clicks on "call next number" again
  	Then the system calls the second "priority" client by order of arrival
  	When the staff clicks on "call next number" once more
  	Then the system calls the "standard" client by order of arrival
  	
	Scenario: Canceling an attendance number when the client does not appear
  	Given a client has been called for attendance
  	When the client does not appear
  	And the staff cancels the attendance number
  	Then the system cancels the current number
  	And the system calls the next available number
  