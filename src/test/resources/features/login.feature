@login
Feature: Test login page with email and password

  @Logout
  Scenario Outline: Users should be able to login using valid credentials
    Given I navigate to Login page
    When I login as <User email> with password <Password>
    Then I should login successfully with a <Full Name>

    Examples: Short pass
      | User email             | Password    | Full Name    |
      | pepito.perez@gmail.com | pepitoperez | pepito perez |
