package tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsPackage.LandingPage;

public class InitialTest {

	@Test
	public void submitOrderTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		
		//landing page <=> catalogue page
		List<String>shoppingList = Arrays.asList("Beans","Potato", "Brocolli","Pears", "Pista");
		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-name']"));
//		
		for (int i=0;i<products.size();i++) {
			String rawName = products.get(i).getText();
			String formattedName = rawName.split("-")[0].trim();
			if(shoppingList.contains(formattedName)) {
				driver.findElements(By.xpath("//div[@class='product']//button")).get(i).click();
				
			}
		}
		//top deals page - opens new tab
		//cart items from cart icon invisible unless cart icon clicked
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		//check products test
		driver.findElement(By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]")).click();
		//checkout page
		//test with and without promo code
//		driver.findElement(By.cssSelector("input[class='promoCode']")).sendKeys("rahulshettyacademy");
//		driver.findElement(By.cssSelector("button[class='promoBtn']")).click();
		//
		driver.findElement(By.xpath("//button[contains(text(), 'Place Order')]")).click();
		//country page
		WebElement staticDropdown = driver.findElement(By.xpath("//select"));
		Select dropdown = new Select(staticDropdown);
		// dropdown select by visible text
		dropdown.selectByVisibleText("Cuba");
		driver.findElement(By.cssSelector(".chkAgree")).click();
		//test wihout accepting terms and conditions
		driver.findElement(By.xpath("//button[contains(text(), 'Proceed')]")).click();
		//confirmation page
		//mai incolo
		//top deals page
		//mai incolo
		
		
	}
}
