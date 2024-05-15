package net.emicalculator.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoanCalculator extends BasePageActions{
	WebDriver driver;
	JavascriptExecutor js;
	
	Actions actions ;
	
	public LoanCalculator(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor)driver;
	}
	
	
//------------------------------------------WebElements in Loan Calculator Page---------------------------------------
	
	
	@FindBy(xpath = "//a[@title='Calculators']")
	WebElement calculatorDropdown;
	
	@FindBy(xpath = "//a[@title='Loan Calculator']")
	WebElement loanCalculatorAnchor;

	@FindBy(id = "loanamount")
	WebElement loanAmount;
	
		//Loan Amount Slider
		@FindBy(xpath = "//div[@id = 'loanamountslider']/span")
		WebElement loanAmountSlider;

	@FindBy(id = "loaninterest")
	WebElement loanInterest;
	
		//Interest Rate Slider
		@FindBy(xpath = "//div[@id = 'loaninterestslider']/span")
		WebElement IntRateSlider;

	@FindBy(id = "loanterm")
	WebElement loanTenure;
	
		//Loan Tenure Slider
		@FindBy(xpath = "//div[@id = 'loantermslider']/span")
		WebElement loanTenureSlider;
	
	@FindBy(id = "loanfees")
	WebElement loanFees;
	
		//Fees & Charges Slider
		@FindBy(xpath = "//div[@id = 'loanfeesslider']/span")
		WebElement loanFeesSlider;
	
	@FindBy(id = "loanemi")
	WebElement loanEMI;
	
		//EMI Slider
		@FindBy(xpath = "//div[@id = 'loanemislider']/span")
		WebElement emiSlider;
	
	@FindBy(id = "loanyears")
	WebElement emiYearButton;
	
	@FindBy(id = "loanmonths")
	WebElement emiMonthButton;
	
	@FindBy(xpath = "//div[@id='emitotalinterest']//p")
	WebElement totalInterest;

	@FindBy(xpath = "//div[@id='emitotalamount']//p")
	WebElement totalPayment;
	
	@FindBy(xpath = "//input[@id='emiadvance']//parent::label")
	WebElement advanceEMI;
	
	@FindBy(xpath = "//input[@id='emiarrears']//parent::label")
	WebElement arreaseEMI;
	
	@FindBy(xpath = "//li[@id='emi-calc']")
	WebElement emiCalculatorSection;
	
	@FindBy(xpath = "//li[@id='loan-amount-calc']")
	WebElement loanAmountCalculatorSection;
	
	@FindBy(xpath = "//li[@id='loan-tenure-calc']")
	WebElement loanTenureCalculatorSection;
	
	@FindBy(xpath = "//li[@id='interest-rate-calc']")
	WebElement interestRateCalculatorSection;
	
//--------------------------------------------Actions in Loan Calculator Page----------------------------------------------
	
	
	public void navigateToEMISection() {
		navigateHome();
		calculatorDropdown.click();
		loanCalculatorAnchor.click();
		
	}
	
	public void selectCalculator(String calulatorName) {
		if(calulatorName.equalsIgnoreCase("emiCalculatorSection")) {
			emiCalculatorSection.click();
		}else if(calulatorName.equalsIgnoreCase("loanAmountCalculatorSection")) {
			loanAmountCalculatorSection.click();
		}else if(calulatorName.equalsIgnoreCase("loanTenureCalculatorSection")) {
			loanTenureCalculatorSection.click();
		}else if(calulatorName.equalsIgnoreCase("interestRateCalculatorSection")) {
			interestRateCalculatorSection.click();
		}
	}
			
	public void selectSectionOfcalculation(String sectionName) {
		if (sectionName.equalsIgnoreCase("emiCalulator")) {
			emiCalculatorSection.click();
		} else if (sectionName.equalsIgnoreCase("loanAmountCalculator")) {
			loanAmountCalculatorSection.click();
		} else if(sectionName.equalsIgnoreCase("loanTenureCalculator")) {
			loanTenureCalculatorSection.click();
		}else if(sectionName.equalsIgnoreCase("interestRateCalulator")) {
			interestRateCalculatorSection.click();
		}
	}
	
	public void setEmiScheme(String scheme) {
		
		if(scheme.equalsIgnoreCase("advance")) {
			advanceEMI.click();
		}else {
			arreaseEMI.click();
		}
	}

	public void selectInterestType(String periodType) {
		if (periodType.equalsIgnoreCase("months")) {
			emiMonthButton.click();
		} else if (periodType.equalsIgnoreCase("years")) {
			emiYearButton.click();
		}
	}

	public String getEmiInterest() {
		System.out.println(loanEMI.getText());
		return loanEMI.getText();
	}
	
	public String getTotalInterest() {
		System.out.println(totalInterest.getText());
		return totalInterest.getText();
	}
	
	public String getTotalPayment() {
		System.out.println(totalPayment.getText());
		return totalPayment.getText();
	}


	public void getPageTite() {
		System.out.println(driver.getTitle());
		
	}


	
	
	
}
