package testing.testNG.matrix;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testing.common.BeforeTestSettingConfg;
import testing.pageFactory.matrix.AudienceAnalysis;
import testing.pageFactory.user.UserInformation;

public class AudienceAnalysisTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	public BeforeTestSettingConfg beforeTestSettingConfg;

	// {chartType, TimeUnit, toPeriod, fromPeriod, Audience, filterType, filterInputInfo, Group}
	@DataProvider
	public Object[][] testData() {
		return new Object[][] { new Object[] { 2, 2, "to", "from", 0, "Select", "", 1 },
								new Object[] { 2, 2, "to", "from", 0, "User ID", "none", 1 }};
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
		driver.findElement(By.xpath("//a[@title='Audience']")).click();
		driver.findElement(By.xpath("//a[@title='Audience Analysis']")).click();
		driver.findElement(By.xpath("//a[@title='Audience Analysis']")).click();
	}

	@Test(dependsOnMethods = "testSuccessLogin", dataProvider = "testData")
	public void audienceAnalysis(int chartType, int timeUnitType, String toPeriod, String fromPeriod, int audience,
			String filterType, String filterInfo, int group) {
		driver.findElement(By.xpath("//a[@title='Audience Analysis']")).click();
		driver.findElement(By.xpath("//a[@title='Audience Analysis']")).click();

		AudienceAnalysis audienceAnalysis = new AudienceAnalysis(driver);
		audienceAnalysis.setChartAndTimeUnitType(chartType, timeUnitType);
		audienceAnalysis.setPeriod(fromPeriod, toPeriod);
		audienceAnalysis.setAudience(audience);
		audienceAnalysis.setFilter(filterType, filterInfo);
		audienceAnalysis.setGroup(group);
		
		WebElement chart = null;
		if (chartType == 0) {
			chart = (new WebDriverWait(driver, 30)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath("//*[@id='highcharts-18']/svg/rect"));
				}
			});
		} else if (chartType == 1) {
			chart = (new WebDriverWait(driver, 30)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath("//*[@id='highcharts-37']/svg/rect"));
				}
			});
		} else if (chartType == 2) {
			chart = (new WebDriverWait(driver, 30)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.id("table-accounts"));
				}
			});
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
}