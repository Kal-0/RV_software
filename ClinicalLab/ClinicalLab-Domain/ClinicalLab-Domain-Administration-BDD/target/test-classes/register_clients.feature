Feature: Client Result

Scenario: Viewing the list of all clients who took exams at the laboratory
  Given the attendant is on the "Clients" page
  When the attendant views the client list
  Then the system displays the list of all clients who have taken exams at the laboratory

Scenario: Searching for a client by name or CPF that does not exist
  Given the attendant is on the "Clients" page
  And the attendant enters a "non-existent" client name or CPF in the search bar
  When the attendant clicks the search button
  Then the system displays a message stating that the client does not exist

Scenario: Viewing all test records for a specific client
  Given the attendant is on the "Clients" page
  And the attendant searches for a "specific" client
  When the attendant clicks on the client's name
  Then the system displays all test records of the client
  And the system shows whether the results were received by email or got the printed result in PDF at the laboratory

Scenario: Exporting a specific test record for a client
  Given the attendant is viewing a "specific" test record of a client
  When the attendant clicks on the "Export" button
  Then the system allows the attendant to download the test record as a PDF

Scenario: Resending a specific test result to the client by email
  Given the attendant is viewing a "specific" test record of a client
  When the attendant clicks on the "Resend by Email" button
  And confirms the action
  Then the system sends the test result to the client's registered email address
  And the system displays a confirmation message that the email has been sent successfully

Scenario: Attempting to resend a test result when no email is on file
  Given the attendant is viewing a "specific" test record of a client
  And the client does not have an email address on file
  When the attendant clicks on the "Resend by Email" button
  Then the system displays a message stating that no email address is available for this client
  And the system does not send the test result

Scenario: Automatically sending test results by email when results are entered into the database
  Given the test results for a client are entered into the database
  When the system detects that the test results are complete
  Then the system automatically sends an email to the client
  And the email contains the "Exam Request Number Page"
  And in the "Client Record" page the status from that "Exam Request Number" will change to "Sent Result"
  

