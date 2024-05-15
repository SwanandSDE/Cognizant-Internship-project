package net.emicalculator.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomeLoanEmiCalculator extends BasePageActions {

	WebDriver driver;
	JavascriptExecutor js;
	String[][] excelData;
	int excelDataPointer = 0;

	public HomeLoanEmiCalculator(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(id = "startmonthyear")
	WebElement datePicker;
	
	@FindBy(xpath = "//div[@class = 'datepicker-months']//th[@class = 'prev']")
	WebElement datePrevious;
	
	@FindBy(xpath = "//div[@class = 'datepicker-months']//th[@class = 'next']")
	WebElement dateNext;
	
	@FindBy(xpath = "//div[@class = 'datepicker-months']//th[@class = 'prev']//following-sibling::th")
	WebElement yearElement;
	
	@FindBy(xpath =  "//td[contains(@id, 'year')]")
	List<WebElement> yearDataList;
	
	@FindBy(xpath = "//td[contains(@class, 'paymentmonth')]//parent::tr")
	List<WebElement> totalDataRows;
	
	

	@FindBy(xpath = "//td[contains(@id,'year')]")
	List<WebElement> monthExtenderElements;

	

	

	public void openFullTable() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", monthExtenderElements.get(monthExtenderElements.size() - 1));

		System.out.println("scrolled");

		for (WebElement element : monthExtenderElements) {
			js.executeScript("arguments[0].click();", element);
//			element.click();
			System.out.println("clicked");
		}

	}

	public void writeTableDatailIntoExcel() {
		excelData = new String[totalDataRows.size()][8];

		for (short i = 0; i < totalDataRows.size(); i++) {

			String[] rowData = new String[8];

			String dynamicDataXpath = "(//td[contains(@class, 'paymentmonth')]//parent::tr)[" + (i + 1) + "]//td";

			String dynamicYearXpath = "((//td[contains(@class, 'paymentmonth')]//parent::tr)[" + (i + 1)
					+ "]//ancestor::tr)[1]";

			String year = driver.findElement(By.xpath(dynamicYearXpath)).getAttribute("id").replace("monthyear", "");

			rowData[0] = year;

			List<WebElement> singleRow = driver.findElements(By.xpath(dynamicDataXpath));

			for (byte j = 0; j < singleRow.size(); j++) {
				String data = singleRow.get(j).getText();

				rowData[j + 1] = data;
			}

			excelData[excelDataPointer++] = rowData;
		}

	}
	
	public String[][] getExcelData(){
		return excelData;
	}

	public void sendStartDate(String month, String year) {
		setupWait();
		datePicker.click();
		
		int yr = Integer.parseInt(year);
		
		int currYear = Integer.parseInt(yearElement.getText());
		
		if(yr >= currYear) {
			while(currYear != yr) {
				dateNext.click();
				
				currYear = Integer.parseInt(yearElement.getText());
			}
		}
		else {
			while(currYear != yr) {
				datePrevious.click();
				
				currYear = Integer.parseInt(yearElement.getText());
			}
		}
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@class,'month') and contains(text(), '" + month + "')]")))).click();
	}
}
