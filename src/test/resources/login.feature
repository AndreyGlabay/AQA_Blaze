Feature: login
  The user can login to the application with valid credentials for using application functionality

Scenario: after successful login check all web elements are present
  Given the user on the home page
  When the user click login button
  And enter valid username
  And enter valid password
  And and click login button
  Then the user is on the logged user home page

  Scenario: after successful login check correct Username is displayed
    Given the user on the home page
    When the user click login button
    And enter valid username
    And enter valid password
    And and click login button
    Then the correct username is displayed

  Scenario: check the title in login modal
    Given the user on the home page
    When the user click login button
    Then the correct title is displayed
