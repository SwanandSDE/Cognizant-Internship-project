package net.emicalculator.utils;

import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Screenshot {
	//SoftAssert sa = new SoftAssert();
	//sa.assertTrue(false,"File Inpute Output exeption");
	
	public static boolean screenShot(WebDriver driver,String fileName) {
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);       
		File targetFile = new File(".\\Screenshots\\"+fileName+".png");
		try {
			
			FileUtils.copyFile(src, targetFile );
		} catch (Exception e) {
			return false;
		}
        return true;
	  }
}
