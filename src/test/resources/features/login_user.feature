@login
Feature: Test login page with user alias

  Background: paso comun para cada scenario
    Given I navigate to Login page

  @Logout
  Scenario: Users should be able to login with user alias pepito
    When I login as "pepito" User
    Then I should login successfully with a "pepito perez"

