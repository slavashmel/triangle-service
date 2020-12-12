Feature: CPQ UI API-Only Smoke Scenarios
  Smoke Tests for CPQ UI API using Fill Quote request

  Scenario: API E2E Create Quote for Reorder scenario with Fill quote
    Given Send Create Quote request with body parameters
      | scenario    | distributionChannel | category | billingAccountCode |
      | Acquisition | Tele Sale           | B2B      | 1.16867402         |
    And Validate JSON schema for "Create Quote" response
    And Send Fill Quote request with data for "6330" server
    And Send Search Quote request
    And Validate JSON schema for "Search Quote" response
    And Send Complete Quote request
    And Send Fetch Quote request
    And Validate JSON schema for "Fetch Quote" response
    And Verify Fetch Quote response values
    And Send Accept Quote request with API version "v3"
    And Validate JSON schema for "Accept Quote" response
    And Send Clone Quote request