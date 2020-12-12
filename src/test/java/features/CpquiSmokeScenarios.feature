Feature: CPQ UI Build Validation Scenarios
  Smoke Tests for CPQ UI (API + UI)

  Scenario: E2E Create Quote for Reorder scenario
    Given Send Create Quote request with body parameters
      | scenario    | distributionChannel | category | billingAccountCode |
      | Acquisition | Tele Sale           | B2B      | 1.16867402         |
    And Validate JSON schema for "Create Quote" response
        # ----- Start of UI steps -----
    And Login to ROE with
      | username | password   |
      | vime1116 | netcracker |
    And Add Subscription from Favorites with name "@Work Subscription"
    And Raise Quantity
    And Add Remark "AT Remark"
    And Add device "Apple iPhone 11 Pro Max 512GB (T-Mobile)"
    And Select color "Zwart"
#    And Select SIM card with name "Mobile Postpaid SIM for Acquisition"
    And Select Work Addon "@Work Unlimited"
    And Change Pricing Date
    And Change Market
    And Duplicate the package
    And Validate Scenario name
#    And Validate billingAccountCode
        # ----- End of UI steps -----
    And Send Search Quote request
    And Validate JSON schema for "Search Quote" response
    And Send Complete Quote request
    And Send Fetch Quote request
    And Validate JSON schema for "Fetch Quote" response
    And Verify Fetch Quote response values
    And Send Accept Quote request with API version "v3"
    And Validate JSON schema for "Accept Quote" response
    And Send Clone Quote request