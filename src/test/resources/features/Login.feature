@US2 @SR3
Feature: Login User Access
  Users must log in to see their planets, and only logged-in users can see their planets

  Background: Current Users
    Given  the user "Frank" is in the database
    And    the user "Frank" has the password "Fan1999"
    And    the user "Robert" is not in the database
    And    the user is not logged in
    And    the user is on the login page

  Scenario: Happy Path Login
    When   the user provides username "Batman"
    And    the user provides password "Iamthenight1939"
    And    the user submits the login credentials
    Then   the user should be redirected to the home page
    And    the user should see the greeting "Welcome to the Home Page Batman"

  Scenario: Sad Path Wrong Password
    When the user provides username "Frank"
    And the user provides password "Fan1993"
    And the user submits the login credentials
    Then the user should get a browser alert saying "Invalid credentials"

  Scenario: Sad Path Username Not Found
    When the user provides username "Robert"
    And the user provides password "Fanta56"
    And the user submits the login credentials
    Then the user should get a browser alert saying "Invalid credentials"

    @SR1
  Scenario: Hidden Login Password
    When the user provides password "Fan1993"
    Then the login password field should be hidden

  Scenario: Happy Path Logout
    Given the user is on the home page
    And   the user is logged in
    When  the user logs out
    Then  the user should be redirected to the login page
    And   the user should be logged out

  Scenario: Redirect Logged Out to Login
    Given the user is not logged in
    When  the user navigates to the home page
    Then  the user should be denied access to the home page