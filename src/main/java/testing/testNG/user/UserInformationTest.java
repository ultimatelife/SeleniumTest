package testing.testNG.user;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import testing.common.BeforeTestSettingConfg;
import testing.pageFactory.user.UserInformation;

public class UserInformationTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	public BeforeTestSettingConfg beforeTestSettingConfg;
	
	@Parameters({ "platform", "browser" })
	@BeforeTest
	public void launchapp(String platform, String browser) throws MalformedURLException {
		beforeTestSettingConfg = new BeforeTestSettingConfg(platform, browser);
	}

	@Test
	public void testFailEmailSyn() throws Exception {
		UserInformation pageFactoryTestNG = new UserInformation(beforeTestSettingConfg.getDriver());
		pageFactoryTestNG.login("swadmincom", "sxmonito");
		Assert.assertEquals(pageFactoryTestNG.notEmailType(), "Please enter a valid email address.");
	}
	
	@Test
	public void testSuccessLogin() throws Exception {
		UserInformation pageFactoryTestNG = new UserInformation(beforeTestSettingConfg.getDriver());
		pageFactoryTestNG.login("swadmin@streamlyzer.com", "sxmonitor");
	}

	@AfterTest
	public void closeBrowser() {
		beforeTestSettingConfg.getDriver().quit();
	}
}
