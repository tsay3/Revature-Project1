@SR2
Feature: Planet Deleting
  Users should be able to delete their planets

  Background:
    Given the user is logged in
    And   the user is on the home page
    And   the user owns the planet "Earth"
    And   the planet "Mars" is in the database
    And   the planet "Venus" is not in the database
    And   the user selects "Planet" from the options

  Scenario: Happy Path Moon Deleting
    When  the user has entered the planet "Earth" to delete
    And   the user tries to delete the planet
    Then  the planet "Earth" should not be in the database
    And   the planet "Earth" is not in their list of planets and moons

  Scenario: Sad Path Planet Not Found Deleting
    When  the user has entered the planet "Mars" to delete
    And   the user tries to delete the planet
    Then  the user should get a browser alert saying "Planet not found"

  Scenario: Sad Path Planet Not Owned Deleting
    Given the user is not the owner of the planet "Mars"
    When  the user has entered the planet "Mars" to delete
    And   the user tries to delete the planet
    Then  the user should get a browser alert saying "Planet not found"
