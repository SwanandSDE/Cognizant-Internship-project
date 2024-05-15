package net.emicalculator.pageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class HomePage extends BasePageActions{

	WebDriver driver;
	JavascriptExecutor js;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "loanamount")
	WebElement loanAmount;

	@FindBy(id = "loaninterest")
	WebElement loanInterest;

	@FindBy(id = "loanterm")
	WebElement loanTenure;

	@FindBy(id = "car-loan")
	WebElement carLoanSection;

	@FindBy(id = "home-loan")
	WebElement homeLoanSection;

	@FindBy(id = "personal-loan")
	WebElement personalLoanSection;

	@FindBy(id = "loanyears")
	WebElement emiYearButton;

	@FindBy(xpath = "//div[@id='emiamount']//p")
	WebElement loanEMI;

	@FindBy(xpath = "//div[@id='emitotalinterest']//p")
	WebElement totalInterest;

	@FindBy(xpath = "//div[@id='emitotalamount']//p")
	WebElement totalPayment;

	@FindBy(xpath = "//input[@id = 'loanmonths']//parent::label")
	WebElement emiMonthButton;
	
	@FindBy(xpath = "//td[contains(@id,'year')]")
	List<WebElement> monthExtenderElements;
	
	@FindBy(xpath = "//input[@id='emiadvance']//parent::label")
	WebElement advanceEMI;
	
	@FindBy(xpath = "//input[@id='emiarrears']//parent::label")
	WebElement arreaseEMI;
	
	
	@FindBy(xpath = "//tr[contains(@id,'monthyear')]//td[not (contains(@class,'wrapper'))]")
	List<WebElement> allTableDataMonthwise;
	
	

	public void selectSection(String sectionName) {
		if (sectionName.equalsIgnoreCase("car-loan")) {
			carLoanSection.click();
		} else if (sectionName.equalsIgnoreCase("home-loan")) {
			homeLoanSection.click();
		} else {
			personalLoanSection.click();
		}
	}

	public void setTestData(String amount, String interest, String tenure) {
		loanAmount.clear();
		loanAmount.sendKeys(amount);

		loanInterest.clear();
		loanInterest.sendKeys(Keys.BACK_SPACE, interest);

		loanTenure.clear();
		loanTenure.sendKeys(Keys.BACK_SPACE, tenure);

	}
	
	public void setEmiScheme(String scheme) {
		if(scheme.equalsIgnoreCase("advance")) {
//			js.executeScript("arguments[0].click()", advanceEMI);
			advanceEMI.click();
		}else {
//			js.executeScript("arguments[0].click()", arreaseEMI);
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
	
	
	
	public void getFirstMonthInterest() {
		System.out.print(allTableDataMonthwise.get(0).getText()+" ");
		System.out.print(allTableDataMonthwise.get(1).getText()+" ");
		System.out.print(allTableDataMonthwise.get(2).getText()+" ");
		System.out.print(allTableDataMonthwise.get(3).getText()+" ");
		System.out.print(allTableDataMonthwise.get(4).getText()+" ");
		System.out.print(allTableDataMonthwise.get(5).getText()+" ");
		
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	public void openFullTable() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", monthExtenderElements.get(0));
		
		System.out.println("scrolled");
		
		for(WebElement element : monthExtenderElements) {
			element.click();
			System.out.println("clicked");
		}
		
	}
	
	public void writeTableDatailIntoExcel() {
		//WriteIntoExcel
		 
		for(int i = 0; i< allTableDataMonthwise.size(); i++) {
			if(i%6==0) {
				System.out.println();
			}
			
			System.out.print(allTableDataMonthwise.get(i).getText()+" ");
		}
		
		
	}

}
