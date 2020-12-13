@api @getTriangle
Feature: Get triangle

  Background:
    Given delete all triangles

  @all @positive @smoke
  Scenario: Create triangle and Get it
    Given Send POST request to endpoint "/triangle" with parameters from table and check that response code is "200"
      | separator | input | token                                |
      | ;         | 3;4;5 | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    When Send GET request to endpoint "/triangle/" and check that response code is "200"
    Then Check that JSON schema of response matches "createTriangleResponse" standard
