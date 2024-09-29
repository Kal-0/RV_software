Feature: Payment Method Selected

Scenario: Trying to select a payment method without total price calculated
Given the system has not calculated the total price
When the attendant try to choose a payment method
Then the system will not display the payment methods
And the system will show an error message stating that the total price must be calculated before choosing a payment method

Scenario: Trying to select a payment method for a new exam request
   Given the system has a request exam with a status of 'new'
   When the attendant receives a payment
   Then the system processes the payment
   And the system shows a confirmation message

Scenario: Completing payment and joining the blood draw waiting list
    Given a "new" client with all selected exams
    And the system has calculated the total price
    And the attendant selects "private payment"
    When the attendant completes the payment and the payment is confirmed
    Then the system assigns the client to the blood draw waiting list
    And the client is placed in the correct queue position
    And the exam status is updated to "waiting"
