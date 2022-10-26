@login
Feature: Verify login with valid an invalid credentials

  @positive
  Scenario Outline: Users should be able to login using valid credentials
    Given I navigate to Login page
    When I login as <User email> with password <Password>
    Then I should login successfully with a <Full Name>

    Examples: Short pass
      | User email             | Password    | Full Name    |
      | pepito.perez@gmail.com | pepitoperez | pepito perez |

  @negative
  Scenario Outline: Users shouldn't able to login using invalid credentials
    Given I navigate to Login page
    When I login as <User email> with password <Password>
    Then The following alert should be display "There is 1 error"
      And I shouldn't login and show the following message "<Message>"
    Examples: Short pass
      | User email              | Password    | Message                    |
      | pepito.perez@gmail.com  |             | Password is required.      |
      |                         | pepitoperez | An email address required. |
      | pepito.perez1@gmail.com | pepitoperez | Authentication failed.     |
