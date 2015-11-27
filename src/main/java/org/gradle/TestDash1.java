package org.gradle;


import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDash1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
//	  driver = new HtmlUnitDriver();
//	  driver.get("http://www.google.com");
	  
    baseUrl = "https://stdb.streamlyzer.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testDash1() throws Exception {
    driver.get(baseUrl + "/accounts/sign_in");
    driver.findElement(By.id("account_email")).clear();
    driver.findElement(By.id("account_email")).sendKeys("swadmin@streamlyzer.com");
    driver.findElement(By.id("account_password")).clear();
    driver.findElement(By.id("account_password")).sendKeys("sxmonitor");
    driver.findElement(By.name("commit")).click();
//    WebElement x = driver.findElement(By.name("commit"));
//    
//    WebDriverWait wait = new WebDriverWait(driver, 10);
//    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("")));
//    
//    Alert alert	= driver.switchTo().alert();
//    String textOnAlert = alert.getText();
//    
//    WebElement message = (new WebDriverWait(driver, 5)).until(new ExpectedCondition<WebElement>() {
//		public WebElement apply(WebDriver d) {
//			return d.findElement(By.id("page4"));
//		}
//	});
//    
    File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(srcFile, new File("C:\\Users\\Streamlyzer\\Desktop\\gradle test\\src.png"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
