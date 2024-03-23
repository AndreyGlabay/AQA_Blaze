Feature: signup
  The user can successfully signup if they enter a username and password.
  The user cannot signup if a username has been registered before, or if the username and/or password are missing.
  The user can discard the signup flow.
  Signup modal has the correct title.

  Background: the user open Sign Up modal
    Given new user on the home page
    When new user click nav bar signup button
    Then signup modal appeared

  Scenario Outline: successful signup
    When new user enter username "<username>"
    And new user enter password "<password>"
    And new user click modal signup button
    Then alert signup successful appear

    Examples:
      | username    | password   |
      | JohnJohnson | Test-12345 |
      | timhce4eNP  | Test-123abc|
      | x81DfDaelO  | Test-abcde |
      | WQ8T1HyUUD  | 123581321  |
      | ESpc8dds9Y  | R0S0T8wZHc |
      | kWFUYNaAlc  | vIq3YCdNxi |