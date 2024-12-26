@MR1 @MR2
Feature: Moon Adding

  Background:
    Given the user is on the home page
    And   the planet "Earth" is in the database

  Scenario Outline: Happy Path Moon Adding
    Given the moon "<moon>" is not in the database
    When  the user provides a moon name "<moon>"
    And   the user provides the planet "Earth" that owns the moon
    And   the user tries to add the moon
    Then  the moon "<moon>" should be added to the database
    And   the user should see the moon "<moon>" in their list of planets and moons
    And   the owner of "<moon>" should be "Earth"

  Examples:
    |moon                           |
    |The Mysterious Moon of Neptune |
    |Ice-Moon 23_b                  |
    |Q                              |

  Scenario Outline: Sad Path Moon Adding
    When  the user provides a moon name "<moon>"
    And   the user provides the planet "Earth" that owns the moon
    And   the user tries to add the moon
    Then  the user should get a browser alert saying "Invalid moon name"

  Examples:
    |moon                             |
    |The Forgettable Moon of Neptune  |
    |Moon #23                         |

  @MR3
  Scenario: Sad Path Existing Moon Adding
    Given   the moon "Luna" exists in the database
    When  the user provides a moon name "Luna"
    And   the user provides the planet "Earth" that owns the moon
    And   the user tries to add the moon
    Then  the user should get a browser alert saying "Invalid moon name"

  @MR6 @MR7 @SR2
  Scenario Outline: Happy Path Moon with Image Adding
    Given the moon "Luna" is not in the database
    When  the user provides a moon name "Luna"
    And   the user provides the planet "Earth" that owns the moon
    And   the user provides a moon image "<filename>"
    And   the user tries to add the moon
    Then  the moon "Luna" should be added to the database
    And   the user should see the moon "Luna" in their list of planets and moons

  Examples:
    |filename             |
    |moon.jpg             |
    |moon.png             |

  @MR7
  Scenario Outline: Sad Path Moon with Bad Image Adding
    Given the moon "Luna" is not in the database
    When  the user provides a moon name "Luna"
    And   the user provides a moon image "<filename>"
    And   the user provides the planet "Earth" that owns the moon
    And   the user tries to add the moon
    Then  the user should get a browser alert saying "Invalid filetype"

  Examples:
    |filename             |
    |moon.bmp             |
    |moon.gif             |
    |moon.webm            |
    |moon.txt             |
    |moon.mp4             |
    |moon.tif             |
    |moon.tga             |