package net.emicalculator.stepDefination;

import java.nio.channels.NonWritableChannelException;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.emicalculator.base.BaseClass;
import net.emicalculator.pageObject.HomePage;

public class CarLoanSectionvalidation extends BaseClass {

	HomePage homePage;
	SoftAssert sa;

	@Given("user is on EMI Calculator site")
	public void user_is_on_emi_calculator_site() {
		homePage = new HomePage(driver);
		homePage.navigateHome();
	}

	@When("{string} EMI and {string} section are selected")
	public void emi_and_section_are_selected(String emiScheme, String section) {
		homePage.selectSection(section);
		homePage.setEmiScheme(emiScheme);
	}

	@When("car price {string}, interest {string}, tenure {string} year are set")
	public void car_price_interest_tenure_year_are_set(String carPrice, String interest, String tenure) {
		homePage.setTestData(carPrice, interest, tenure);
		homePage.selectInterestType("Months");
	}

	@Then("correct EMI, monthly interest, and principal amounts are displayed")
	public void correct_emi_monthly_interest_and_principal_amounts_are_displayed() {
		Assert.assertEquals(homePage.getEmiInterest(), "₹1,30,492");
		Assert.assertEquals(homePage.getTotalInterest(), "₹65,906");
		Assert.assertEquals(homePage.getTotalPayment(), "₹15,65,906");
	}

	@Given("user navigates to Home page")
	public void user_navigates_to_home_page() {
		homePage = new HomePage(driver);
		homePage.navigateHome();
	}
	
	@Then("Title of Home Page should be validated")
	public void title_of_home_page_should_be_validated() {
		
		
		if (driver.getTitle()
				.equals("EMI Calculator for Home Loan, Car Loan & Personal Loan in India")) 
		{
			Assert.assertTrue(true);

		}else {
			Assert.fail("Title not matching for home page");

		}
	}
	
	@Then("All elements should be visible and interactable of home page")
	public void all_elements_should_be_visible_and_interactable_of_home_page() {
	    	sa = new SoftAssert();
	    	System.out.println("goning in home page");
	    	
	    	sa.assertTrue(homePage.getElementStatus("loanamount"),"Loan amount is not present");
	    	sa.assertTrue(homePage.getElementStatus("loaninterest"),"Loan interest is not present");
	    	sa.assertTrue(homePage.getElementStatus("loanterm"),"Loan tenure is not present");
	    	sa.assertTrue(homePage.getElementStatus("emipaymentdetails"),"EMI payment details section is not present");
	    
	    	
	    	
	    
	    sa.assertAll();
	}

}