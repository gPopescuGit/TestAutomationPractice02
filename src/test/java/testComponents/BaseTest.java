package testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjectsPackage.LandingPage;

public class BaseTest {
	// initialize driver
	public WebDriver driver;
	public LandingPage landingPage;

	// create landing page
	public WebDriver initializeDriver() throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//resources//globalData.properties");
		properties.load(fis);
		String browserName = properties.getProperty("browser");
		String headlessMode = properties.getProperty("headless");
		if (browserName.equals("chrome")) {
			if (headlessMode.equals("yes")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--window-size=1920,1080");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
			} else {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}

		} else {
			System.out.println("nothing for now");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
//		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		driver.close();
		Thread.sleep(500);
	}
}
