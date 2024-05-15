Feature: Running tests on emicalculator site

  Scenario: Validation of Car loan section in home page
    Given user is on EMI Calculator site
    When "Advance" EMI and "Car-Loan" section are selected
    And car price "1500000", interest "9.5%", tenure "1" year are set
    Then correct EMI, monthly interest, and principal amounts are displayed

	@smoke
  Scenario: Smoke test for Home page
    Given user is on EMI Calculator site
    And user navigates to Home page
    Then Title of Home Page should be validated
    And All elements should be visible and interactable of home page

    
    
    
    