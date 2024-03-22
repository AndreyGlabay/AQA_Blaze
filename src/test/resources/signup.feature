Feature: signup
  The user can successfully signup, if they enter username and password,
  and if not - get notification about unsuccessful signup.

  Background: the user open Sign Up modal
    Given new user on the home page
    When new user click nav bar signup button
    Then sinup modal appeared

    Scenario: successful signup
      When new user enter username
      And new user enter password
      And new user click modal signup button
      Then alert signup successful appear