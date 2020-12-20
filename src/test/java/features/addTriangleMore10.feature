@api @addTriangle
Feature: Add triangle

  Background:
    Given delete all triangles

  @all @negative @smoke
  #@TODO. BUG. should be not more than 10 triangles
  Scenario: Create triangle | 11 triangles
    Given Create 10 triangles with parameters from table and check that response code is "200"
      | input | token                                |
      | 3;4;5 | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    Given Create 1 triangle with parameters from table and check that response code is "422"
      | input | token                                |
      | 3;4;5 | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |