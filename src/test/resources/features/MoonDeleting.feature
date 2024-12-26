Feature: Moon Deleting

  Background:
    Given the user is logged in
    And   the user owns the planet "Earth"
    And   the moon "Luna" is owned by the planet "Earth"
    And   the user does not own the planet "Mars"
    And   the moon "Io" is owned by the planet "Mars"
    And   the moon "Death Star" does not exist in the database
    And   the user is on the home page

  Scenario:Happy Path Moon Deleting
    When  the user has entered the moon "Luna" to delete
    And   the user tries to delete the moon
    Then  the user should not see the moon "Luna" in their list of planets and moons

  Scenario: Sad Path Moon Not Found Deleting
    When  the user has entered the moon "Death Star" to delete
    And   the user tries to delete the moon
    Then  the user should get a browser alert saying "Invalid moon name"

  @MR5
  Scenario: Happy Path Planet with Moon Deleting
    When  the user has entered the planet "Earth" to delete
    And   the user tries to delete the planet
    Then  all moons that were owned by "Earth" should be removed from the database
    And   the planet "Earth" should not be in the database
    And   the planet "Earth" should not be in their list of planets and moons
