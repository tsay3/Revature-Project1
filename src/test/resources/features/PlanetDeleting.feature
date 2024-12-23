@SR2
Feature: Planet Deleting
  Users should be able to delete their planets

  Background:
    Given the user is logged in
    And   the user is on the home page
    And   the user owns the planet "Earth"
    And   the planet "Mars" is in the database
    And   the planet "Venus" is not in the database

  Scenario: Happy Path Moon Deleting
    When  the user has entered "Earth"
    And   the user tries to delete the planet
    Then  "Earth" should be removed from the database
    And   the user should see their list of planets and moons
    And   the planet "Earth" is not in their list of planets and moons

  Scenario: Sad Path Planet Not Found Deleting
    When  the user has entered "Mars"
    And   the user tries to delete the planet
    Then  the user should see a browser alert saying "Planet not found"

  Scenario: Sad Path Planet Not Owned Deleting
    Given the user is not the owner of "Mars"
    When  the user has entered "Mars"
    And   the user tries to delete the planet
    Then  the user should see a browser alert saying "Planet not found"
