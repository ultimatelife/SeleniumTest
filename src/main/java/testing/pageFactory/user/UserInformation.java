package testing.pageFactory.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.AssertJUnit;

public class UserInformation extends LoadableComponent<UserInformation>{
	private WebElement account_email;
	private WebElement account_password;
	@FindBy(name="commit")
	private WebElement commit;
	@FindBy(id="account_email-error")
	private WebElement account_email_error;
	
	private WebDriver driver = null;
	
	public UserInformation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void load() {
	}
	
	@Override
	protected void isLoaded(){
	}
	
	public void login(String account_email, String account_password) {
		this.account_email.clear();
		this.account_password.clear();
		
		this.account_email.sendKeys(account_email);
		this.account_password.sendKeys(account_password);
		this.commit.click();
	}
	
	public String notEmailType(){
		return account_email_error.getText();
	}
	
	public void close() {
		this.driver.close();
	}
	
}
