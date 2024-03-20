Feature: login
  The user can login to the application with valid credentials for using application functionality

Scenario: after successful login check all web elements are present
  Given the user on the home page
  When the user click login button
  And enter valid username
  And enter valid password
  And and click login button
  Then the user is on the logged user home page


