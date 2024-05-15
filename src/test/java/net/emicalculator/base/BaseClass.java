package net.emicalculator.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	private String baseUrl = "https://emicalculator.net";
	
	private void chromeBrowser() {
		driver = new ChromeDriver();
	}
	
	private void edgeBrowser() {
		driver = new EdgeDriver();		
	}
	
	@BeforeTest
	@Parameters({"browser"})
	public void initiateDriver(String browser) {
		
		System.out.println("Opening Browser");
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Selected browser is chrome");
			chromeBrowser();
		}else if (browser.equalsIgnoreCase("edge")) {
			System.out.println("Selected browser is edge");
			edgeBrowser();
		}else {
			throw new SkipException("Do not have matching browser");			
		}
	
		driver.manage().window().maximize();
		setupWait();
		
	}
	
	public static void setupWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public void navigateHome() {
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void teardown() {
		System.out.println("Closing browser");
		driver.quit();
	}
}
