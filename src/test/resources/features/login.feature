@login
Feature: Login
  Test login page

  Background:
    Given I navigate to Login page

  @SmokeTest @Logout
  Scenario Outline: Users should be able to login using valid credentials
    When I login as "<User email>" with password "<Password>"
    Then I should login successfully with a "<Full Name>"

    Examples: Short pass
      | User email               | Password | Full Name     |
      | denis_cc1729@hotmail.com | qwerty   | Denis Camacho |