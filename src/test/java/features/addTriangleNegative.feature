@api @addTriangle
Feature: Add triangle

  Background:
    Given delete all triangles

  @all @negative
  Scenario: Create triangle | wrong endpoint
    Given Send POST request to endpoint "/triang" with parameters from table and check that response code is "404"
      | separator | input | token                                |
      | ;         | 3;4;5 | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    And Check that JSON schema of response matches "createTriangleError404NotFound" standard

  @all @negative
  Scenario: Create triangle | wrong token
    Given Send POST request to endpoint "/triangle" with parameters from table and check that response code is "401"
      | separator | input | token                                |
      | ;         | 3;4;5 | 11058bd0-4783-4f97-9509-ec3f7455ffb2 |
    And Check that JSON schema of response matches "createTriangleError401Unauthorized" standard

  @all @negative
  Scenario: Create triangle | missed separator
    Given Send POST request to endpoint "/triangle" with parameters from table and check that response code is "200"
      | input | token                                |
      | 3;4;5 | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    And Check that JSON schema of response matches "createTriangleResponse" standard

  @all @negative
  Scenario: Create triangle | separator doesn't matches separator in input
    Given Send POST request to endpoint "/triangle" with parameters from table and check that response code is "422"
      | separator | input | token                                |
      | ,         | 3:4:5 | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    And Check that JSON schema of response matches "createTriangleError422UnprocessableEntity" standard

  @all @negative
#  @TODO I'd better return code 4** with message that mandatory parameter is missed
  Scenario: Create triangle | missed input
    Given Send POST request to endpoint "/triangle" with parameters from table and check that response code is "500"
      | separator | token                                |
      | ;         | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    And Check that JSON schema of response matches "createTriangleResponse" standard

  @all @negative
  Scenario: Create triangle | missed one triangle side
    Given Send POST request to endpoint "/triangle" with parameters from table and check that response code is "422"
      | separator | input | token                                |
      | ;         | 3;4; | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    And Check that JSON schema of response matches "createTriangleError422UnprocessableEntity" standard