Feature: signup
  The user can successfully signup, if they enter username and password.
  The user cannot signup, if a username has been registered before, as well as username and/or password missing.
  The user can discard discard signup flow.
  Signup modal has correct title.

Background: the user open Sign Up modal
  Given new user on the home page
  When new user click nav bar signup button
  Then signup modal appeared

  Scenario: successful signup
    When new user enter username
    And new user enter password
    And new user click modal signup button
    Then alert signup successful appear

  Scenario: unsuccessful signup - username already registered
    When new user enter already registered username
    And new user enter password
    And new user click modal signup button
    Then alert user already exist appear

  Scenario: unsuccessful signup - skip username input
    When new user enter password
    And new user click modal signup button
    Then alert fill out Username and Password appear

  Scenario: unsuccessful signup - skip password input
    When new user enter newest username
    And new user click modal signup button
    Then alert fill out Username and Password appear

  Scenario: unsuccessful signup - skip username and password inputs
    When new user click modal signup button
    Then alert fill out Username and Password appear

  Scenario: discard signup by close button
    When new user enter username
    And new user enter password
    And new user click modal close button
    Then signup modal is not displaying

  Scenario: discard signup by cross button
    When new user enter username
    And new user enter password
    And new user click modal cross button
    Then signup modal is not displaying

  Scenario: check the title of signup modal
    Then check the title of signup modal
