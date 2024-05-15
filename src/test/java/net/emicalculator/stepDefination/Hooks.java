package net.emicalculator.stepDefination;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeTest;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import net.emicalculator.base.BaseClass;

public class Hooks extends BaseClass {

	@BeforeStep
	public void setUpWait() {
		// TODO Auto-generated method stub
		setupWait();

	}
	

	@AfterStep
	public void addScreenshot(Scenario scenario) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());

	}

}
