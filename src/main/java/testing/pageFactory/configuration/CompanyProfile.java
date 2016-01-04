package testing.pageFactory.configuration;

import java.io.File;
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

public class CompanyProfile extends LoadableComponent<UserInformation> {
	private WebDriver driver = null;

	@FindBy(id = "menu-setting")
	private WebElement menuSetting;
	
	@FindBy(xpath = "//*[@id='side-bottom-menu']/span[2]/ul/li[1]/a")
	private WebElement companyProfile;

	@FindBy(id = "company_name")
	private WebElement companyName;
	
	@FindBy(id = "company_phone_number")
	private WebElement companyPhoneNumber;
	
	@FindBy(id = "company_street")
	private WebElement companyStreet;
	
	@FindBy(id = "company_city")
	private WebElement companyCity;
	
	@FindBy(id = "company_state")
	private WebElement companyState;
	
	@FindBy(id = "company_country")
	private WebElement companyCountry;
	private Select selectCompanyCountry;

	@FindBy(id = "company_zipcode")
	private WebElement companyZipcode;
	
	@FindBy(id = "company_thumbnail")
	private WebElement companyThumbnail;
	
	@FindBy(id = "//*[@id='edit_company_1']/div[2]/div[8]/div/input")
	private WebElement save;
	
	public CompanyProfile(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		menuSetting.click();
		companyProfile.click();
	}

	@Override
	protected void isLoaded() throws Error {
	}

	public void setCompanyName(String companyName) {
		this.companyName.sendKeys(companyName);
	}

	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber.sendKeys(companyPhoneNumber);
	}

	public void setCompanyStreet(String companyStreet) {
		this.companyStreet.sendKeys(companyStreet);
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity.sendKeys(companyCity);
	}

	public void setCompanyState(String companyState) {
		this.companyState.sendKeys(companyState);
	}

	public void setCompanyCountry(String companyCountry) {
		selectCompanyCountry = new Select(this.companyCountry);
		selectCompanyCountry.selectByVisibleText(companyCountry);
	}

	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode.sendKeys(companyZipcode);
	}

	public void setCompanyThumbnail(File file) {
		this.companyThumbnail.sendKeys(file.getAbsolutePath());
	}

}
