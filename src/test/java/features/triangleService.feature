Feature: Triangle Service basic tests

  Scenario: Create triangle | POST | /triangle | 200
    Given Create Triangle with parameters from table and check response code "200"
      | separator | input | method | token                                |
      | ;         | 3;4;5 | POST   | 66058bd0-4783-4f97-9509-ec3f7466ffb2 |
    And delete all triangles

  Scenario: Create triangle without token | POST | /triangle | 200
    Given Create Triangle with parameters from table
      | separator | input |
      | ;         | 3;4;5 |
    And delete all triangles

  Scenario: Delete one triangle
    And delete triangle with id "d14b9cbc-001a-4ea6-b658-633734578eb4"

  Scenario: Delete all triangles
    And delete all triangles
