Feature: Client Result

Scenario: Viewing a list of all clients who have completed tests
  Given the attendant is on the "Clients" page
  When the attendant views the client list
  Then the system shows all clients who have completed tests

Scenario: Searching for a non-existent client by name or CPF
  Given the attendant is on the "Clients" page
  And the attendant enters a "non-existent" client name or CPF in the search bar
  When the attendant clicks the search button
  Then the system displays a message that the client does not exist

Scenario: Viewing all test records for a specific client
  Given the attendant is on the Clients page
  And the attendant searches for a "specific" client
  When the attendant clicks on the client's name
  Then the system show all test records of the client
  And the system shows whether results were sent by email or provided in a printed result in PDF

Scenario: Exporting a specific exame request for a client
  Given the attendant is viewing a "specific" exame request record of a client
  When the attendant clicks on the "Export" button
  Then the system allows the attendant to download the exame request record as a PDF

Scenario: Resending a specific exame request result to the client by email
  Given the attendant is viewing a "specific" exame request record of a client
  When the attendant clicks on the "Resend by Email" button
  And confirms the action
  Then the system sends the exame request result to the client's registered email
  And the system displays a confirmation message that the email has been sent successfully

Scenario: Attempting to resend a exame request result when no email on file
  Given the attendant is viewing a "specific" exame request record of a client
  And the client has not email on file
  When the attendant clicks on the "Resend by Email" button
  Then the system displays a message that no email is available for this client
  And the system does not send the test result

Scenario: Automatically sending test results by email
  Given the test results are entered into the system
  When the system detects that the test results are complete
  Then the system automatically sends an email to the client
  And the email contains the "Exam Request Number Page"
  And in the "Client Record" page the status from that "Exam Request Number" will change to "Sent Result"
