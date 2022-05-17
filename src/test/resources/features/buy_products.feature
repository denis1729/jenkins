@buy_product
Feature: buy products for a women with categories dresses and tops

  Background: paso comun para cada scenario
    Given I navigate to Login page
      And I login as "pepito" User
      And I go to Home page

  @Logout
  Scenario Outline: Buy products with different categories
    When I go to the women's category
      And I select the product "<category>" with the subcategory of "<subcategory>"
      And I add one product to the cart
      And I proceed to pay the product with "<pay method>"
    Then I should buy the product successfully and show the "<message>"

    Examples:
      | category | subcategory     | pay method | message                             |
      | tops     | t-shirt         | bank wire  | Your order on My Store is complete. |
      | tops     | blouses         | check      | Your order on My Store is complete. |
      | dresses  | casual dresses  | bank wire  | Your order on My Store is complete. |
      | dresses  | evening dresses | check      | Your order on My Store is complete. |
      | dresses  | summer dresses  | check      | Your order on My Store is complete. |
