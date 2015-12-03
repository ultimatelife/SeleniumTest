package grid;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class SeGridTest{

  WebDriver driver = null;
  private StringBuffer verificationErrors = new StringBuffer();
  
  
  @Parameters({ "platform","browser","version", "url" })
  @BeforeTest(alwaysRun=true)
  public void setup(String platform, String browser, String version, String url) throws MalformedURLException
  {
	  DesiredCapabilities caps = new DesiredCapabilities();
	  
	  //Platforms
	  if(platform.equalsIgnoreCase("Windows"))
		 caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
	  
	  if(platform.equalsIgnoreCase("MAC"))
	  	 caps.setPlatform(org.openqa.selenium.Platform.MAC);
	
	  if(platform.equalsIgnoreCase("Andorid"))
			 caps.setPlatform(org.openqa.selenium.Platform.ANDROID);
	  
	  //Browsers
	  if(browser.equalsIgnoreCase("Internet Explorer"))
		  caps = DesiredCapabilities.internetExplorer();
	  	 
	  if(browser.equalsIgnoreCase("Firefox"))
		  caps = DesiredCapabilities.firefox();
	  
	  if(browser.equalsIgnoreCase("iPad"))
		  caps = DesiredCapabilities.ipad();
	  
	  if(browser.equalsIgnoreCase("Android"))
		  caps = DesiredCapabilities.android();
	  
	  //Version
	  caps.setVersion(version);
	  	   
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
	  
	  // Open the BMI Calculator Application
	  driver.get(url);
	  
  }
  
  @Test(description="Test Bmi Calculator")
  public void testBmiCalculator() throws InterruptedException {

		WebElement height = driver.findElement(By.name("heightCMS"));
		height.sendKeys("181");
		
		WebElement weight = driver.findElement(By.name("weightKg"));
		weight.sendKeys("80");

		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();
		
		try {
			
			WebElement bmi = driver.findElement(By.name("bmi"));
			AssertJUnit.assertEquals(bmi.getAttribute("value"),"24.4");
			
			WebElement bmi_category = driver.findElement(By.name("bmi_category"));
			AssertJUnit.assertEquals(bmi_category.getAttribute("value"),"Normal");
			
		} catch (Error e) {
			
			//Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
		}
  }
	@AfterTest
	public void afterTest() {
		//Close the browser
		driver.quit();
				
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	  
  }
}