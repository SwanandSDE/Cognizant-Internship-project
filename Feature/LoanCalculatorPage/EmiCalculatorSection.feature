Feature: Validating UI in EMI Calculator Section of loan calcualtor page

  Scenario: Validate the UI features of EMI Calculator
 	 	Given user navigates to EMI Calculator		
# 	 	
		When Various values for loan amount slider is passed and checked
		And Various values for loan amount textbox is passed and checked
#
		And Various values for loan interest slider is passed and checked
		And Various values for loan interest textbox is passed and checked
#
		And Various values for loan tenure slider is passed and checked
		And Various values for loan tenure textbox is passed and checked
#
		And Various values for loan fees slider is passed and checked
		And Various values for loan fees textbox is passed and checked
#
		Then Validation is done for EMI calculator UI check
		
		