#  Since the feature is being tagged, these tags cascade to the Scenario and the Scenario Outline
@US1 @SR1
Feature: User Registration
  As a new user I want to open an account with the Planetarium so I can save my celestial findings

  Background: Pre-Registration
    Given   the user is on the login page
    And     the user clicks on the register link
    And     the user "Frank" is not in the database

  Scenario: Users can register a new account with valid credentials
    When    the user provides username "Frank"
    And     the user provides password "Fan19"
    And     the user submits the credentials
    Then    the user should get a browser alert saying "Account created successfully"
    And     the user should be redirected to the "login" page

# these tags will apply to each run of the Scenario Outline determined by the Examples table
  @UR1 @UR2 @UR3 @UR4 @UR5 @UR6
  Scenario Outline: Users can not register an account with invalid credentials
    Given   the user is on the register page
    When    the user provides username "<username>"
    And     the user provides password "<password>"
    And     the user submits the credentials
    Then    the user should get a browser alert saying "<alert>"
    And     the user should be redirected to the "<page>" page

    Examples:
      |username                             |password           |alert                          |page|
      |Frank                                |Azad-Zaire_109     |Account created successfully   |login|
      |Abe	                                |Azad-Zaire_109	    |Invalid username         |registration|
      |John_Jacob_Jingleheimer_Schmidt	    |Azad-Zaire_109	    |Invalid username         |registration|
      |21_jump_street	                    |Azad-Zaire_109	    |Invalid username         |registration|
      |Dr. Watson	                        |Azad-Zaire_109	    |Invalid username         |registration|
      |Azad-Zaire_109	                    |Cat2	            |Invalid password         |registration|
      |Azad-Zaire_109	|Jenny_dont_change_your_number_8675309	|Invalid password         |registration|
      |Azad-Zaire_109	                    |foobar	            |Invalid password         |registration|
      |Azad-Zaire_109	                    |a123456	        |Invalid password         |registration|
      |Azad-Zaire_109	                    |123_Fake_st	    |Invalid password         |registration|
      |John_Jacob_Jingleheimer_Schmidt	    |foobar	            |Invalid username         |registration|
      |Frank 	                            |Fan19              |Invalid username         |registration|

  @UR1
  Scenario: Users can not register an account that already exists
    Given the username "Frank" with the password "Password1" is in the database
    When the user provides username "Frank"
    And the user provides password "Fan19"
    And the user submits the credentials
    Then the user should get a browser alert saying "Invalid credentials"

    @SR1
  Scenario: Hidden Registration Password
    When the user provides password "Fan1993"
    Then the password field should be hidden
