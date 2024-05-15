package net.emicalculator.testRunner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
			tags =  "@smoke",
			features = {
					".//Feature/"
//					".//Feature/HomePage/carLoan.feature",
//					".//Feature/HomeLoanCalculator/HomeLoan.feature",
//					".//Feature/LoanCalculatorPage/LoanAmountCalculatorSection.feature",
//					".//Feature/LoanCalculatorPage/EmiCalculatorSection.feature",
//					
						},
			glue = "net.emicalculator.stepDefination",
			plugin = {"pretty", "html:target/CucumberReport/cucumber-html-report.html",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					"rerun:target/rerun.txt"
					},

			dryRun = false,
			monochrome = true,
			publish = true
		)
public class SmokeTestRunner extends AbstractTestNGCucumberTests {

}
