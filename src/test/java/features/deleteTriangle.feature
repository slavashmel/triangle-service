Feature: Delete triangle

  Scenario: Create valid triangle
    Given Send POST request to endpoint "/triangle" with parameters from table and check that response code is "200"
      | separator | input | token                                |
      | ;         | 3;4;5 | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    And Check that JSON schema matches "triangleResponse" standard
    And delete all triangles


  Scenario: Delete one triangle
    And delete triangle with id "d14b9cbc-001a-4ea6-b658-633734578eb4"

  Scenario: Delete all triangles
    And delete all triangles
