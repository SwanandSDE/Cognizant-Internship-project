package net.emicalculator.stepDefination;

import javax.swing.plaf.SliderUI;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.emicalculator.base.BaseClass;
import net.emicalculator.pageObject.LoanCalculator;
import net.emicalculator.utils.ExcelUtils;

public class LoanCalculatorEmiSectionValidation extends BaseClass {

	LoanCalculator loanCalculator;
	SoftAssert sa = new SoftAssert();
	ExcelUtils excelUtils;
	String data[][];

	private void SliderToInpute(int min, int max) {

		for (int i = min; i < max; i++) {
			loanCalculator.setSliderValue((data[i][0] + "slider"), data[i][0], Float.parseFloat(data[i][1]),
					Float.parseFloat(data[i][2]), Float.parseFloat(data[i][3]), Float.parseFloat(data[i][4]));

			float val = loanCalculator.getInputBoxValue(data[i][0]);

			sa.assertEquals(val, Float.parseFloat(data[i][5]));

		}

	}

	private void InputeToSlider(int min, int max) {
		for (int i = min; i < max; i++) {
			float value = Float.parseFloat(data[i][1]) * Float.parseFloat(data[i][4]);

			loanCalculator.setDataOfElement(data[i][0], Float.toString(value));

			float val = loanCalculator.getCurrentSliderValue((data[i][0] + "slider"), Float.parseFloat(data[i][2]),
					Float.parseFloat(data[i][3]), Float.parseFloat(data[i][4]));

			System.out.println("val " + val);
			sa.assertEquals(val, Float.parseFloat(data[i][5]));
		}
	}

	private void InputeToSlider(int min, int max, int customMaxStep, int customStepUnit) {
		for (int i = min; i < max; i++) {
			float value = Float.parseFloat(data[i][1]) * Float.parseFloat(data[i][4]);

			loanCalculator.setDataOfElement(data[i][0], Float.toString(value));

			float val = loanCalculator.getCurrentSliderValue((data[i][0] + "slider"), Float.parseFloat(data[i][2]),
					customMaxStep, customStepUnit);

			System.out.println("val " + val);
			sa.assertEquals(val, Float.parseFloat(data[i][5]));
		}

	}

	@Given("user navigates to EMI Calculator")
	public void user_navigates_to_emi_calculator() {
		loanCalculator = new LoanCalculator(driver);

		loanCalculator.navigateToEMISection();

		loanCalculator.selectCalculator("emiCalculatorSection");

		excelUtils = new ExcelUtils();

		data = excelUtils.readData("UICheckData", "EMICalculator");

		loanCalculator.getPageTite();

	}

	@Given("user navigates to Loan Amount Calculator")
	public void user_navigates_to_loan_amount_calculator() {
		loanCalculator = new LoanCalculator(driver);

		loanCalculator.navigateToEMISection();

		loanCalculator.selectCalculator("loanAmountCalculatorSection");

		excelUtils = new ExcelUtils();

		data = excelUtils.readData("UICheckData", "LoanAmountCalculator");

		loanCalculator.getPageTite();

	}

	@When("Various values for loan amount slider is passed and checked")
	public void various_values_for_loan_amount_slider_is_passed_and_checked() {

		SliderToInpute(0, 5);

	}

	@When("Various values for loan amount textbox is passed and checked")
	public void various_values_for_loan_amount_textbox_is_passed_and_checked() {

		InputeToSlider(0, 5);

	}

	@When("Various values for loan interest slider is passed and checked")
	public void various_values_for_loan_interest_slider_is_passed_and_checked() {

		SliderToInpute(5, 10);
	}

	@When("Various values for loan interest textbox is passed and checked")
	public void various_values_for_loan_interest_textbox_is_passed_and_checked() {
		InputeToSlider(5, 10);
	}

//
	@When("Various values for loan tenure slider is passed and checked")
	public void various_values_for_loan_tenure_slider_is_passed_and_checked() {

		SliderToInpute(10, 15);
	}

	@When("Various values for loan tenure textbox is passed and checked")
	public void various_values_for_loan_tenure_textbox_is_passed_and_checked() {
		InputeToSlider(10, 15);
	}

//
	@When("Various values for loan fees slider is passed and checked")
	public void various_values_for_loan_fees_slider_is_passed_and_checked() {

		SliderToInpute(15, 20);
	}

	@When("Various values for loan fees textbox is passed and checked")
	public void various_values_for_loan_fees_textbox_is_passed_and_checked() {
		InputeToSlider(15, 20, 100, 1000);
	}

//

	@When("Various values for EMI slider is passed and checked")
	public void various_values_for_emi_slider_is_passed_and_checked() {
		SliderToInpute(0, 5);
	}

	@When("Various values for EMI textbox is passed and checked")
	public void various_values_for_emi_textbox_is_passed_and_checked() {
		InputeToSlider(15, 20, 100, 1000);
	}

	@Then("Validation is done for Loan Amount Calculator UI check")
	public void validation_is_done_for_loan_amount_calculator_ui_check() {
		sa.assertAll();
	}

	@Then("Validation is done for EMI calculator UI check")
	public void validation_is_done_for_emi_calculator_ui_check() {
		sa.assertAll();

	}
	
	
//------------------------------------------SMOKE FOR LOAN CALCULATOR-------------------------------------	

	@Given("user navigates to Loan Calculator page")
	public void user_navigates_to_loan_calculator_page() {
		loanCalculator = new LoanCalculator(driver);

		loanCalculator.navigateToEMISection();
	}

	@Then("Title of Loan Calculator page should be validated")
	public void title_of_loan_calculator_page_should_be_validated() {
		if (driver.getTitle().equals("Loan Calculator — Calculate EMI, Affordability, Tenure & Interest Rate")) {
			System.out.println("----------" + driver.getTitle());
			Assert.assertTrue(true);
		} else {
			Assert.fail("Title not matching");

		}
	}

	@Then("All elements should be visible and interactable of Loan Calculator page")
	public void all_elements_should_be_visible_and_interactable_of_loan_calculator_page() {
		System.out.println("goning in loan calculator page");
    	sa.assertTrue(loanCalculator.getElementStatus("emi-calc"),"EMI calculator section  is not present");
    	sa.assertTrue(loanCalculator.getElementStatus("loan-amount-calc"),"Loan amount calculator section is not present");
    	sa.assertTrue(loanCalculator.getElementStatus("interest-rate-calc"),"Interest Rate section is not present");
    	
    	sa.assertAll();
	}

}
