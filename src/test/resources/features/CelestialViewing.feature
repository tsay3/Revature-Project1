@US3 @SR2
Feature: Celestial Body Viewing
  Users should see the celestial bodies they own, and only the bodies they own

  Background:
    Given the user is logged in

  Scenario: Happy Path Viewing
    Given the user owns the planet "Earth"
    Then  the user should see the planet "Earth" on the home page

  Scenario: Happy Path Moon Viewing
    Given the user owns the planet "Earth"
    And   the planet "Earth" owns the moon "Luna"
    And   the user is on the home page
    Then  the user should see the moon "Luna" on the home page

  Scenario: Sad Path Planet Viewing
    Given the user owns no planets
    And   the user is on the home page
    Then  the user should not see any planets on the home page

  Scenario: Sad Path Moon Viewing
    Given the user owns the planet "Earth"
    And   the planet "Earth" does not own any moons
    And   the user is on the home page
    Then  the user should not see any moons for "Earth"
