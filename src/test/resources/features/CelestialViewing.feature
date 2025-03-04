@US3 @SR2
Feature: Celestial Body Viewing
  Users should see the celestial bodies they own, and only the bodies they own

  Background:
    Given the user is logged in
    Given the user owns the planet "Earth"

  Scenario: Happy Path Viewing
    When  the user is on the home page
    Then  the user should see the planet "Earth" on the home page
    And   the planet "Earth" should have an id of 1
    And   the moon "Luna" should have an id of 1

  Scenario: Sad Path Planet Viewing
    Given the user owns no planets
    And   the user is on the home page
    Then  the user should not see any planets on the home page

  Scenario: Happy Path Moon Viewing
    Given the planet "Earth" owns the moon "Luna"
    And   the user owns the planet "Mars"
    And   the planet "Mars" owns the moon "Titan"
    And   the user is on the home page
    Then  the user should see the moon "Luna" on the home page
    And   the user should see the moon "Titan" on the home page
    And   the planet "Earth" should own 1 moon
    And   the planet "Mars" should own 1 moon

  Scenario: Sad Path Moon Viewing
    Given the planet "Earth" does not own any moons
    And   the user is on the home page
    Then  the user should not see any moons for "Earth"
