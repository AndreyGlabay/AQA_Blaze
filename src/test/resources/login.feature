Feature: login
  The user can login to the application with valid credentials and use the application.

Background: the registered user open Log In modal
  Given registered user on the home page
  When registered user click nav bar login button
  Then login modal appeared

  Scenario: after successful login check all web elements are present
    When registered user enter valid username
    And registered user enter valid password
    And registered user click modal login button
    Then authorized user come to logged in home page

  Scenario: after successful login check correct Username is displayed in Nav Bar
    When registered user enter valid username
    And registered user enter valid password
    And registered user click modal login button
    Then the correct username is displayed in nav bar

  Scenario Outline: after successful login check correct Username is displayed in Nav Bar
    When registered user enter valid username "<username>"
    And registered user enter valid password "<password>"
    And registered user click modal login button
    Then the correct username is displayed in nav bar

    Examples:
      | username      | password     |
      | JohnJohnson   | Test-12345   |
      | timhce4eNP    | Test-123abc  |
      | x81DfDaelO    | Test-abcde   |
      | WQ8T1HyUUD    | 123581321    |
      | ESpc8dds9Y    | R0S0T8wZHc   |
      | kWFUYNaAlc    | vIq3YCdNxi   |

  Scenario: check the title of login modal
    Then check the title of login modal
