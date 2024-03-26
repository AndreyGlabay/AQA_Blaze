Feature: Cart Functionality
  User can add a product to the cart.
  User can remove a product from the cart.
  User can  proceed to checkout from the cart.
  User can  discard checkout.
  User can add multiple products to the cart.
  User can  proceed to checkout from the cart.
  User can successfully complete checkout.

  Background: user on the home page
    Given user on the home page
    Then product properties loaded

  Scenario Outline: user can add a product to the cart
    When user navigate to the "<productId>" product page
    And user see the "<productId>" product details
    And user add the product to the cart
    Then alert product added appear
    And the "<productId>" product been added to the cart

    Examples:
      | productId |
      | idp_4    |

  Scenario Outline: user can  proceed to checkout from the cart
    When user have "<product>" in the cart
    And user proceed to checkout
    Then user should be redirected to the checkout page

    Examples:
      | product        |
      | xxxxxxx        |
      | yyyyyyy        |

  Scenario: user can discard checkout
    When user in checkout modal
    And user discard checkout
    Then user should be redirected to the cart page

  Scenario Outline: user can remove a product from the cart
    When user have "<product>" in the cart
    And user remove the "<product>" from the cart
    Then the "<product>" should be removed from the cart

    Examples:
      | product       |
      | xxxxxxx       |
      | yyyyyyy       |

  Scenario Outline: user can add multiple products to the cart
    When user navigate to the "<product1>" product page
    And user add the "<product1>" to the cart
    And user back to the home page
    And user navigate to the "<product2>" product page
    And user add the "<product2>" to the cart
    And user back to the home page
    And user navigate to the "<product3>" product page
    And user add the "<product3>" to the cart
    Then the all products should be added to the cart

    Examples:
      | product1        | product2         | product3         |
      | phone11111      | laptop222222     | monitor11111     |
      | laptop11111     | monitor22222     | phone4444444     |

  Scenario: user can  proceed to checkout from the cart
    When user have all products in the cart
    And user proceed to checkout
    Then user should be redirected to the checkout page

  Scenario: user can successfully complete checkout
    When user fill in checkout form
    And user submit checkout form
    Then checkout should be completed
