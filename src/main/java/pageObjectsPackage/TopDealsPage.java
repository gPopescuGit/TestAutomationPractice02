package pageObjectsPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponentPackage.AbstractComponent;

public class TopDealsPage extends AbstractComponent{
	
	WebDriver driver;
	
	public TopDealsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="search-field")
	WebElement searchBox;
	
	@FindBy(css=".table tbody tr")
	List<WebElement> rows;
	
	@FindBy(xpath = "//select")
	WebElement staticSelect;
	
	public String searchDeal(String productName) {
		searchBox.sendKeys(productName);
		String foundDeal = rows.get(0).getText();
		if(!foundDeal.equals(null)) {
			return driver.findElement(By.cssSelector(".table tbody tr td:nth-child(3)")).getText();
		}
		
		return "No deal found";
	}
	
}
