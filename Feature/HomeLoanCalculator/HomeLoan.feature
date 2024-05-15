Feature: Home Loan EMI Calculation

  Scenario: Calculation of Home Loan EMI
    Given user is on EMI Calculator site
    And user navigates to Home Loan EMI Calculator
    When user fills the relevant details
    Then extract the data EMI Realated data table to excel
    
  @smoke
  Scenario: Smoke test For Home loan
  	Given user is on EMI Calculator site
    And user navigates to Home Loan EMI Calculator
    Then Title of Home loan Page should be validated
    And All elements should be visible and interactable of home loan calculator page