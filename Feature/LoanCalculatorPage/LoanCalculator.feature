Feature: Base loan calculator feture

@smoke
Scenario: Smoke test for Loan Calculator page
		Given user is on EMI Calculator site
    And user navigates to Loan Calculator page
    Then Title of Loan Calculator page should be validated
    And All elements should be visible and interactable of Loan Calculator page