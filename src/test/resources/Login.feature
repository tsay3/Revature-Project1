@US2 @SR3
Feature: Login User Access
  Users must log in to see their planets, and only logged-in users can see their planets

  Background: Current Users
    Given  "Alice" is a user in the database
    And    "Fan1999" is the password corresponding to "Alice"
    And    "Robert" is not a user in the database
    And    The user is on the login page

  Scenario: Happy Path Login
    When   the user provides existing username "Alice"
    And    the user provides corresponding password "Fan1999"
    And    the user submits the credentials
    Then   the user should be redirected to the home page
    And    the user should see a greeting

  Scenario: Sad Path Wrong Password
    When the user provides existing username "Alice"
    And the user provides corresponding password "Fan1993"
    And the user submits the credentials
    Then the user should get a browser alert saying "Invalid Credentials"

  Scenario: Sad Path Username Not Found
    When the user provides username "Robert"
    And the user provides password "Fanta56"
    And the user submits the credentials
    Then the user should get a browser alert saying "Invalid Credentials"

    @SR1
  Scenario: Hidden Login Password
    When the user provides password "Fan1993"
    Then the password field should be hidden

  Scenario: Happy Path Logout
    Given the user is on the home page
    And   the user is logged in
    When  the user logs out
    Then  the user should be redirected to the login page
    And   the user should be logged out

  Scenario: Redirect Logged Out to Login
    Given the user is not logged in
    When  the user navigates to the home page
    Then  the user should be redirected to the login page