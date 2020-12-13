@api @addTriangle
Feature: Add triangle

  Background:
    Given delete all triangles

  @all @positive @smoke
  Scenario: Create a valid triangle
    Given Send POST request to endpoint "/triangle" with parameters from table and check that response code is "200"
      | separator | input | token                                |
      | ;         | 3;4;5 | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    And Check that JSON schema of response matches "createTriangleResponse" standard