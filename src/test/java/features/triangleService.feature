Feature: Triangle Service basic tests

  Scenario: Create triangle | POST | /triangle | 200
    Given Send Create Triangle request with parameters from table
      | separator | input |
      | ;         | 3;4;5 |