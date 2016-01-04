package testing.testNG.configuration;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import testing.common.BeforeTestSettingConfg;
import testing.pageFactory.matrix.AudienceAnalysis;
import testing.pageFactory.user.UserInformation;

public class CompanyProfileTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	public BeforeTestSettingConfg beforeTestSettingConfg;

	// {companyName, companyPhoneNumber, companyStreet, companyCity, companyState, selectCompanyCountry, companyZipcode, companyThumbnail}
	@DataProvider
	public Object[][] companyProfileTestData() {
		return new Object[][] { new Object[] { "streamLyzer", "pass1", "pass2", "pass3", "pass4", "France", "pass5"}};
	}

	@Parameters({ "platform", "browser" })
	@BeforeTest
	public void launchapp(String platform, String browser) throws MalformedURLException {
		beforeTestSettingConfg = new BeforeTestSettingConfg(platform, browser);
		driver = beforeTestSettingConfg.getDriver();
	}

	@Test
	public void testSuccessLogin() throws Exception {
		UserInformation pageFactoryTestNG = new UserInformation(beforeTestSettingConfg.getDriver());
		pageFactoryTestNG.login("swadmin@streamlyzer.com", "sxmonitor");
	}

	@Test(dependsOnMethods = "testSuccessLogin", dataProvider="companyProfileTestData")
	public void test() {
		
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
}