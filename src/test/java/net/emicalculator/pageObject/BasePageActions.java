package net.emicalculator.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.emicalculator.base.BaseClass;

public class BasePageActions extends BaseClass{

	WebDriver driver = BaseClass.getDriver();
	Actions actions;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public BasePageActions() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@title='Calculators']")
	WebElement calculatorDropdown;

	@FindBy(xpath = "//a[@title='Home Loan EMI Calculator']")
	WebElement homeLoanAnchor;
	
	public void navigateToHomeLoanEmiCalculator() {
		navigateHome();
		calculatorDropdown.click();
		homeLoanAnchor.click();
	}
	
	public boolean getElementStatus(String elementId) {
		WebElement element =(WebElement) js.executeScript("return document.getElementById('" + elementId + "')");
		return element.isDisplayed();
	}

	public void setDataOfElement(String elementId, String value) {
		WebElement element = (WebElement) js.executeScript("return document.getElementById('" + elementId + "')");
		element.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
		element.sendKeys(value);
		element.sendKeys(Keys.ENTER);
	}

	public float getInputBoxValue(String elementId) {

		WebElement element = (WebElement) js.executeScript("return document.getElementById('" + elementId + "');");
		String str = element.getAttribute("value");
		str = ( str.contains(","))?
				 str.replaceAll(",", "") : str;
		
		return Math.round(Float.parseFloat(str));
					 
		
	}

	private int getOffset(float value, float min, float max, float sliderWidth) {
		float unit = sliderWidth / (max - min);
		return Math.round(unit * value);
	}

	public float getCurrentSliderValue(String sliderId, float min, float max, float stepUnit) {

		WebElement sliderElement = (WebElement) js
				.executeScript("return document.querySelector('#" + sliderId + "')");

		float sliderLength = sliderElement.getSize().width;

		float unit = ( max-min) / sliderLength;
		
		System.out.println( min + "  " +  max +" " + " " +stepUnit+ " " + unit);
		
		WebElement sliderOffElement = (WebElement) js
				.executeScript("return document.querySelector('#" + sliderId + ">div')");
		
		float value = sliderOffElement.getSize().width * unit;
		
		System.out.println("value " + value);
		
		float roundOff = (float) Math.round(value * 100) / 100;
		
		roundOff = Math.round(roundOff);
		
		roundOff*=stepUnit;
		
		System.out.println(roundOff);
		return roundOff;

	}

	public void setSliderValue(String sliderId, String inputBoxId, float value, float minSliderStep, float maxSliderStep,
			float stepUnit) {
		actions = new Actions(driver);

		WebElement sliderElement = (WebElement) js
				.executeScript("return document.querySelector('#" + sliderId + "')");

		float sliderWidth = sliderElement.getSize().width;
		
		WebElement sliderMoveElement = (WebElement) js
				.executeScript("return document.querySelector('#" + sliderId + ">span')");

		int offset = getOffset(value, minSliderStep, maxSliderStep, sliderWidth);

		float current = (getInputBoxValue(inputBoxId)) / stepUnit;

		int offsetWhileLoading = getOffset(current, minSliderStep, maxSliderStep, sliderWidth);

		offset = offset - offsetWhileLoading;
		System.out.println("OFf Set " + offset);
		actions.dragAndDropBy(sliderMoveElement, offset, 0).build().perform();

	}
}
