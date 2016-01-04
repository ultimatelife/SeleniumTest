package testing.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BeforeTestSettingConfg {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	String url = "";
	
	public BeforeTestSettingConfg(String platform, String browser) throws MalformedURLException {
		String URL = "https://rc.streamlyzer.com/";
		DesiredCapabilities caps = new DesiredCapabilities();
		
		// Platforms
		if (platform.equalsIgnoreCase("Windows")) {
			System.out.println("Platform is Windows");
			caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);

			if (browser.equalsIgnoreCase("firefox")) {
				System.out.println(" Executing on FireFox");
				String Node = "http://localhost:5559/wd/hub";
				// DesiredCapabilities cap = DesiredCapabilities.firefox();
				
				caps = DesiredCapabilities.firefox();
				caps.setBrowserName("firefox");
				
				driver = new RemoteWebDriver(new URL(Node), caps);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// Launch website
				driver.navigate().to(URL);
				driver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.out.println(" Executing on CHROME");
				// DesiredCapabilities cap = DesiredCapabilities.chrome();
				caps.setBrowserName("chrome");
//				caps.setCapability(ChromeOptions.CAPABILITY, "true");
				
				String Node = "http://localhost:5557/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), caps);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				// Launch website
				driver.navigate().to(URL);
				driver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase("InternetExplorer")) {
				System.out.println(" Executing on IE");
				// DesiredCapabilities cap = DesiredCapabilities.chrome();
				caps.setBrowserName("ie");
				// cap.setVersion("11.0.26");
				String Node = "http://localhost:5558/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), caps);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				// Launch website
				driver.navigate().to(URL);
				driver.manage().window().maximize();
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
			
		}

		if (platform.equalsIgnoreCase("MAC")){
			caps.setPlatform(org.openqa.selenium.Platform.MAC);
//			DANA
		}

		if (platform.equalsIgnoreCase("Ubuntu")){
			caps.setPlatform(org.openqa.selenium.Platform.ANDROID);
//			Zino
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public boolean isAcceptNextAlert() {
		return acceptNextAlert;
	}

	public void setAcceptNextAlert(boolean acceptNextAlert) {
		this.acceptNextAlert = acceptNextAlert;
	}

	public StringBuffer getVerificationErrors() {
		return verificationErrors;
	}

	public void setVerificationErrors(StringBuffer verificationErrors) {
		this.verificationErrors = verificationErrors;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
