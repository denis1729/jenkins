@buy_product
Feature: buy products for a women with categories dresses and tops

  Background: common step for each scenario
    Given I navigate to Login page
      And I login as pepito User
      And I go to Home page

  @acceptance
  Scenario Outline: Buy products with different categories
    When I go to the women's category
      And I select the product <category> with the subcategory of <subcategory>
      And I add the following product to the cart
        | productName | <product>  |
        | price       | <price$>   |
        | quantity    | <quantity> |
      And I proceed to pay the product with <pay method>
    Then I should buy the product successfully and show the following message
      """
      Your order on My Store is complete.
      """
      And The total price should be equals to price by quantity more the shipping 2 US

    Examples: data
      | category | subcategory     | product                     | price$ | quantity | pay method |
      | tops     | t-shirts        | Faded Short Sleeve T-shirts | 16.51  | 4        | bank wire  |
      | tops     | blouses         | Blouse                      | 27.00  | 3        | check      |
      | dresses  | casual dresses  | Printed Dress               | 26.00  | 2        | bank wire  |
      | dresses  | evening dresses | Printed Dress               | 50.99  | 5        | check      |
      | dresses  | summer dresses  | Printed Summer Dress        | 28.98  | 8        | check      |
