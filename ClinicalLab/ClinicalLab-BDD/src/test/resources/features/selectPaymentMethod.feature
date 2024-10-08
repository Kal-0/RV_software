Feature: Payment Method Selected

Scenario: Trying to select a payment method without total price calculated
    Given the system has not calculated the total price
    When the attendant tries to select a "Private Payment"
    Then the system will show an error message stating that the total price must be calculated before choosing a payment method

Scenario: Completing payment and joining the blood draw waiting list
  Given a client with a not empty list of ExamTest
  And the system has calculated the total price
  And the attendant selects "Health Insurance"
  When the attendant completes the payment and the payment is confirmed
  Then the system assigns the exam request status "Waiting for collection"

