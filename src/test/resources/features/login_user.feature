@login
Feature: Test login page with user alias

  @Logout
  Scenario: Users should be able to login with user alias pepito
    Given I navigate to Login page
    When I login as pepito User
    Then I should login successfully with a pepito perez

