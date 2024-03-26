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

  Scenario Outline: user can add a product to the cart
    When user navigate to the "<productId>" product page
    And user see the "<productId>" product details
    And user add the product to the cart
    Then alert product added appear
    And the "<productId>" product been added to the cart

    Examples:
      | productId    |
      | PRODUCT_4    |
      | PRODUCT_5    |

  Scenario Outline: user can proceed to checkout from the cart
    When user navigate to the "<productId>" product page
    And user see the "<productId>" product details
    And user add the product to the cart
    And alert product added appear
    And user proceed to checkout
    Then user should be redirected to the checkout page

    Examples:
      | productId    |
      | PRODUCT_4    |
      | PRODUCT_5    |

  Scenario Outline: user can discard checkout
    When user navigate to the "<productId>" product page
    And user see the "<productId>" product details
    And user add the product to the cart
    And alert product added appear
    And user proceed to checkout
    And user discard checkout
    Then user should be redirected to the cart page
    But the "<productId>" product been added to the cart

    Examples:
      | productId    |
      | PRODUCT_4    |
      | PRODUCT_5    |

  Scenario Outline: user can remove a product from the cart
    When user navigate to the "<productId>" product page
    And user see the "<productId>" product details
    And user add the product to the cart
    And alert product added appear
    And user proceed to checkout
    And user discard checkout
    And user should be redirected to the cart page
    And the "<productId>" product been added to the cart
    And user remove the "<productId>" product from the cart
    Then the "<productId>" product should be removed from the cart

    Examples:
      | productId    |
      | PRODUCT_4    |
      | PRODUCT_5    |


     ##############################

  Scenario Outline: the user can add several products to the cart and complete the order
    When user navigate to the "<firstProduct>" first product page
    And user see the "<firstProduct>" first product in the cart
    And user add the product to the cart
    And alert product added appear
    And user back to the home page
    And user navigate to the "<secondProduct>" second product page
    And user see the "<secondProduct>" second product in the cart
    And user add the product to the cart
    And alert product added appear
    And user back to the home page
    And user navigate to the "<thirdProduct>" third product page
    And user see the "<thirdProduct>" third product in the cart
    And user add the product to the cart
    And alert product added appear
    And the all products should be added to the cart
    And user proceed to checkout
    And user should be redirected to the checkout page
    And user fill in checkout form
    And user submit checkout form
    Then checkout should be completed

    Examples:
      | firstProduct | secondProduct | thirdProduct |
      | PRODUCT_1    | PRODUCT_2     | PRODUCT_3    |
      | PRODUCT_6    | PRODUCT_9     | PRODUCT_7    |


