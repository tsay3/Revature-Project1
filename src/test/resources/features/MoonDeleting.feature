Feature: Moon Deleting

  Background:
    Given the user is on the home page
    And the planet "Earth" exists in the database
    And the moon "Luna" is owned by the planet "Earth"
    And the moon "Io" is not owned by the user
    And the moon "Death Star" does not exist in the database

  Scenario:Happy Path Moon Deleting
    When the user has entered the moon "Luna"
    And the user tries to delete the moon
    Then the user should not see "Luna" in their list of planets and moons

  Scenario: Sad Path Moon Not Found Deleting
    When the user has entered the moon "Death Star"
    And the user tries to delete the moon
    Then the user should see a browser alert saying "Moon not found"

  @MR5
  Scenario: Happy Path Planet with Moon Deleting
    When the user has entered the planet "Earth"
    And the user tries to delete the planet
    Then all moons that were owned by "Earth" should be removed from the database
    And "Earth" should be removed from the database
    And the user should see not see "Earth" in their list of planets and moons
