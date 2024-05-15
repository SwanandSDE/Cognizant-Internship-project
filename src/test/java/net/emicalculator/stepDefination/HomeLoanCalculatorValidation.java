package net.emicalculator.stepDefination;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.emicalculator.base.BaseClass;
import net.emicalculator.pageObject.HomeLoanEmiCalculator;
import net.emicalculator.utils.ExcelUtils;

public class HomeLoanCalculatorValidation extends BaseClass {
	HomeLoanEmiCalculator homeLoan;
	ExcelUtils excelUtils;
	SoftAssert sa;

	@Test
	public void homeLoanCalculatorValidation() {
		homeLoan = new HomeLoanEmiCalculator(driver);
		homeLoan.navigateToHomeLoanEmiCalculator();

		homeLoan.openFullTable();

		homeLoan.writeTableDatailIntoExcel();
	}

	@Given("user navigates to Home Loan EMI Calculator")
	public void user_navigates_to_home_loan_emi_calculator() {
		homeLoan = new HomeLoanEmiCalculator(driver);
		setupWait();
		homeLoan.navigateToHomeLoanEmiCalculator();

	}

	@When("user fills the relevant details")
	public void user_fills_the_relevant_details() {
		homeLoan.setDataOfElement("homeprice", "75,00,000");
		homeLoan.setDataOfElement("downpayment", "10");
		homeLoan.setDataOfElement("homeloaninsuranceamount", "20,000");
		homeLoan.setDataOfElement("homeloaninterest", "12");
		homeLoan.setDataOfElement("homeloanterm", "25");
		homeLoan.setDataOfElement("loanfees", "0.67");
		homeLoan.setDataOfElement("onetimeexpenses", "12");
		homeLoan.setDataOfElement("propertytaxes", "0.30");
		homeLoan.setDataOfElement("homeinsurance", "0.10");
		homeLoan.setDataOfElement("maintenanceexpenses", "3000");
		homeLoan.sendStartDate("Apr", "2003");
		
	}

	@Then("extract the data EMI Realated data table to excel")
	public void extract_the_data_emi_realated_data_table_to_excel() {
		homeLoan.openFullTable();

		homeLoan.writeTableDatailIntoExcel();
		
		excelUtils = new ExcelUtils();
		
		excelUtils.writeExcelData(homeLoan.getExcelData(), "HomeLoan");
	}
	
	@Then("Title of Home loan Page should be validated")
	public void title_of_home_loan_page_should_be_validated() {
		System.out.println("-------------"+driver.getTitle());
		if (driver.getTitle()
				.equals("Home Loan EMI Calculator with Prepayments, Taxes & Insurance")) 
		{
			Assert.assertTrue(true);

		}else {
			Assert.fail("Title not matching");

		}
	}
	
	@Then("All elements should be visible and interactable of home loan calculator page")
	public void all_elements_should_be_visible_and_interactable_of_home_loan_calculator_page() {
		sa = new SoftAssert();
		System.out.println("goning in HOme loan calculator page");
    	sa.assertTrue(homeLoan.getElementStatus("homeloanamount"),"Loan amount is not present");
    	sa.assertTrue(homeLoan.getElementStatus("homeloaninterest"),"Loan interest is not present");
    	sa.assertTrue(homeLoan.getElementStatus("homeloanterm"),"Loan tenure is not present");
    	
    	sa.assertAll();
	}

}
