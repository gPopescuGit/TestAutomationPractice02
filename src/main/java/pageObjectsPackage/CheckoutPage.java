package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponentPackage.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[contains(text(), 'Place Order')]")
	WebElement placeOrderBtn;

	By promoCodeTextBox = By.cssSelector("input[class='promoCode']");
	By promoCodeApplyBtn = By.cssSelector("button[class='promoBtn']");
	By promoCodeAppliedLabel =By.cssSelector(".promoInfo");
	
	//promo code	"rahulshettyacademy"
	public void addPromoCode(String promoCode) {
		waitForElementToAppear(promoCodeTextBox);
		driver.findElement(promoCodeTextBox).sendKeys(promoCode);
		driver.findElement(promoCodeApplyBtn).click();
		waitForElementToAppear(promoCodeAppliedLabel);
	}
	
	
	
	public CountryPage placeOrder() {
		placeOrderBtn.click();
		CountryPage countryPage = new CountryPage(driver);
		return countryPage;
	}
}
