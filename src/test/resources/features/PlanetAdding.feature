@US4
Feature: Planet Adding

  Background:
    Given the user is on the home page
    And   the user owns the planet "Earth"

    @PR1 @PR2
  Scenario Outline: Happy Path Planet Adding
    Given the planet "<planet>" does not exist in the database
    When  the user provides a planet name "<planet>"
    And   the user tries to add the planet
    Then  the planet "<planet>" should be added to the database
    And   the user should see an updated list of planets and moons

  Examples:
    |planet                         |
    |Neptune                        |
    |Torcularis Septentrionalis-2_b |
    |Gliese-4a                      |
    |X                              |

  Scenario Outline: Sad Path Invalid Planet Name
    When  the user provides a planet name "<planet>"
    And   the user tries to add the planet
    Then  the user should see a browser alert saying "Invalid planet name"

  Examples:
    |planet                          |
    |Torcularis Septentrionalis-10_b |
    |Planet *                        |

  Scenario: Sad Path Planet Already Exists
    When  the user provides planet name "Earth"
    And   the user tries to add the planet
    Then  the user should see a browser alert saying "Invalid planet name"

  Scenario: Happy Path Planet with Image Adding
    Given the planet "Pluto" does not exist in the database
    When  the user provides a planet name "Pluto"
    And   the user provides an image "planet.png"
    And   the user tries to add the planet
    Then  "Pluto" should be added to the database
    And   the planet’s image is set to "pluto.png"
    And   the user should see "Pluto" in their list of planets and moons

  Scenario Outline: Sad Path Planet with Bad Image Adding
    When  the user provides a planet name "Pluto"
    And   the user provides an image "<filename>"
    And   the user tries to add the planet
    Then  the user should see a browser alert saying "Invalid file type"

  Examples:
    |filename               |
    |planet.bmp             |
    |planet.gif             |
    |planet.webp            |
    |planet.txt             |
    |planet.mp4             |
    |planet.tif             |
    |planet.tga             |