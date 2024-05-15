Feature: Validating UI in Loan Amount Calculator Section of loan calcualtor page 

Scenario: UI Check for Loan Amount Calculator
		Given user navigates to Loan Amount Calculator

		When Various values for EMI slider is passed and checked
		And Various values for EMI textbox is passed and checked

		And Various values for loan interest slider is passed and checked
		And Various values for loan interest textbox is passed and checked

		And Various values for loan tenure slider is passed and checked
		And Various values for loan tenure textbox is passed and checked

		And Various values for loan fees slider is passed and checked
		And Various values for loan fees textbox is passed and checked
		
		Then Validation is done for Loan Amount Calculator UI check