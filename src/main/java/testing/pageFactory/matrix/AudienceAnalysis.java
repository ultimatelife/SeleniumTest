package testing.pageFactory.matrix;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testing.pageFactory.user.UserInformation;

public class AudienceAnalysis extends LoadableComponent<UserInformation> {
	private WebDriver driver = null;

	@FindBy(xpath = "//a[@title='Audience']")
	private WebElement audienceTab;
	@FindBy(xpath = "//a[@title='Audience Analysis']")
	private WebElement audienceAnalysisTab;

	// Common
	@FindBy(xpath = "//*[@id='steps-uid-0']/div[3]/ul/li[1]/a")
	private WebElement back;
	@FindBy(xpath = "//*[@id='steps-uid-0']/div[3]/ul/li[2]/a")
	private WebElement done;
	@FindBy(xpath = "//*[@id='steps-uid-0']/div[3]/ul/li[3]/a")
	private WebElement next;

	// Period
	@FindBy(id = "data-model-from")
	private WebElement data_model_from;
	@FindBy(id = "data-model-to")
	private WebElement data_model_to;

	// Audience(0.Audience 1.Audience Active 2.New Audience 3.Bounce Rate)
	@FindBy(xpath = "//*[@id='steps-uid-0-p-1']/descendant::div/label/input")
	private List<WebElement> radiosAudience;

	// Filter
	@FindBy(xpath = "//*[@id='steps-uid-0-p-2']/div[1]/div/div/div[2]/select")
	private WebElement filter;
	private Select selectFilter;
	@FindBy(xpath = "//*[@id='steps-uid-0-p-2']/div[1]/div/div/div[4]/input")
	private WebElement inputFilter;
//	@FindBy(xpath = "//*[@id='steps-uid-0-p-2']/div[1]/div/div/div[2]/select/descendant::option")
//	private List<WebElement> selectFilterOptions;

	// Group
	@FindBy(xpath = "//*[@id='steps-uid-0-p-3']/descendant::div/label/input")
	private List<WebElement> radiosGroup;

	// Chart & Time Type
	@FindBy(xpath = "//*[@id='steps-uid-0']/div[4]/div/span[1]")
	private WebElement chartAndTime;
	@FindBy(xpath = "//*[@id='chart-wizard-charttype']/div/descendant::label")
	private List<WebElement> chartTypes;
	@FindBy(xpath = "//*[@id='chart-wizard-timeunit']/div/descendant::label")
	private List<WebElement> timeUnits;
	@FindBy(id="//div[@id='chart-wizard-charttype']/div/label[3]")
	private WebElement chartWizardUpdate;

	public AudienceAnalysis(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		audienceTab.click();
		audienceAnalysisTab.click();
		audienceAnalysisTab.click();
	}

	@Override
	protected void isLoaded() throws Error {
	}
	
	public void success() {
		// Chart & Time Type
		chartAndTime.click();
		chartTypes.get(2).click();
		timeUnits.get(2).click();
		System.out.println("sososo");
		
		// Period
//		driver.findElement(
//				By.xpath("//fieldset[@id='steps-uid-0-p-0']/div/div/div/div/ul/li/div/div/table/tbody/tr/td[6]"))
//				.click();
//		driver.findElement(
//				By.xpath("//fieldset[@id='steps-uid-0-p-0']/div/div/div/div/ul/li/div/div/table/tbody/tr[2]/td"))
//				.click();
		this.next.click();

		
		// Audience
		radiosAudience.get(0).click();
		this.next.click();

		// Filter
		this.next.click();

		// Group
		radiosGroup.get(1).click();
		
		// Done
		done.click();
		
		WebElement table = (new WebDriverWait(driver, 30)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("table-accounts"));
			}
		});
	}
	
	public void success2() {
		// Chart & Time Type
		setChartAndTimeUnitType(2, 2);
		setPeriod("01/03/2016 1:18 AM", "01/04/2016 1:18 AM");
		setAudience(0);
		setFilter("Select", "");
		setGroup(1);
		
		WebElement table = (new WebDriverWait(driver, 30)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id("table-accounts"));
			}
		});
	}
	
	public void setChartAndTimeUnitType(int chartType, int timeUnitType){
		chartAndTime.click();
		chartTypes.get(chartType).click();
		timeUnits.get(timeUnitType).click();
	}
	
	public void resetChartAndTimeUnitType(int chartType, int timeUnitType){
		chartAndTime.click();
		chartTypes.get(chartType).click();
		timeUnits.get(timeUnitType).click();
		chartWizardUpdate.click();
	}
	
	public void setPeriod(String from, String to){
//		driver.findElement(
//				By.xpath("//fieldset[@id='steps-uid-0-p-0']/div/div/div/div/ul/li/div/div/table/tbody/tr/td[6]"))
//				.sendKeys("01/03/2016 00:00");
//		driver.findElement(
//				By.xpath("//fieldset[@id='steps-uid-0-p-0']/div/div/div/div/ul/li/div/div/table/tbody/tr[2]/td"))
//				.sendKeys("01/04/2016 00:00");
//		driver.findElement(By.cssSelector("span.fa.fa-chevron-left")).click();
//	    driver.findElement(By.xpath("//fieldset[@id='steps-uid-0-p-0']/div/div/div/div/ul/li/div/div/table/tbody/tr[3]/td[4]")).click();
		next.click();
	}
	
	public void setAudience(int num){
		radiosAudience.get(num).click();
		next.click();
	}
	
	public void setFilter(String filterType, String info){
		selectFilter = new Select(filter);
		
		if(!filterType.equals("Select")){
			selectFilter.selectByVisibleText(filterType);
			inputFilter.sendKeys(info);
		}
		next.click();
	}
	
	public void setGroup(int num){
		radiosGroup.get(num).click();
		done.click();
	}
}
