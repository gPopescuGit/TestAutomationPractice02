package pageObjectsPackage;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import abstractComponentPackage.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h4[@class='product-name']")
	List<WebElement> productsOnPage;

	By setQuantityTextBox = By.cssSelector(".quantity");
	By addToCartBtn = By.xpath("//div[@class='product']//button");
	By cartPreview = By.cssSelector(".cart-preview.active");

	@FindBy(css = "p[class='product-name']")
	List<WebElement> productsInCart;

	@FindBy(css = "img[alt='Cart']")
	WebElement cartBtn;

	@FindBy(xpath = "//button[contains(text(), 'PROCEED TO CHECKOUT')]")
	WebElement proceedToCheckoutBtn;
	
	@FindBy(xpath = "//a[@href='#/offers']")
	WebElement goToTopDealsBtn;

	public void addProductsToCart(List<String> shoppingList) {

		for (int i = 0; i < productsOnPage.size(); i++) {
			String rawName = productsOnPage.get(i).getText();
			String formattedName = rawName.split("-")[0].trim();
			if (shoppingList.contains(formattedName)) {
				driver.findElements(addToCartBtn).get(i).click();
			}
			if (shoppingList.size() == 0) {
				// add all products
				driver.findElements(addToCartBtn).get(i).click();
			}
		}
		if (shoppingList.size() != 0)
			Assert.assertTrue(checkCartContent(shoppingList));
	}

	private boolean checkCartContent(List<String> shoppingList) {
		cartBtn.click();
		waitForElementToAppear(cartPreview);

		for (int i = 0; i < shoppingList.size(); i++) {
			if (!shoppingList.contains(productsInCart.get(i).getText().split("-")[0].trim())) {
				cartBtn.click();
				return false;
			}
		}
		cartBtn.click();
		return true;
	}

	public CheckoutPage goToCheckout() {
		cartBtn.click();
		if (!proceedToCheckoutBtn.getDomAttribute("class").contains("disabled")) {
			proceedToCheckoutBtn.click();
			CheckoutPage checkoutPage = new CheckoutPage(driver);
			return checkoutPage;
		} else {
			return null;
		}
	}
	
	public TopDealsPage goToTopDeals() {
		goToTopDealsBtn.click();
		TopDealsPage topDealsPage = new TopDealsPage(driver);
		return topDealsPage;
	}

	@FindBy(css = ".search-keyword")
	WebElement searchBox;

	public void searchAndAddProductToCart(String productName) {
		searchBox.sendKeys(productName);
		//reinitialize after search
		productsOnPage = driver.findElements(By.xpath("//h4[@class='product-name']"));
		driver.findElements(addToCartBtn).get(0).click();
		searchBox.clear();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}

	private boolean isElementVisible(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void addProductsToCartHeadless(List<String> shoppingList) {
		int maxScrollAttempts = 20; // Prevent infinite loop
		int foundProducts = 0;
		int scrollStep = 300;
		int scrollAttempts = 0;

		while (foundProducts < shoppingList.size() && scrollAttempts < maxScrollAttempts) {
			// initialize list at every iteration
			productsOnPage = driver.findElements(By.xpath("//h4[@class='product-name']"));
			for (int i = 0; i < productsOnPage.size(); i++) {
				String rawName = productsOnPage.get(i).getText();
				String formattedName = rawName.split("-")[0].trim();
				if (shoppingList.contains(formattedName) && isElementVisible(productsOnPage.get(i))) {
					driver.findElements(addToCartBtn).get(i).click();
					foundProducts++;
				}
			}
			if (foundProducts <= shoppingList.size()) {
				break;
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0," + scrollStep + ");");
			scrollAttempts++;
		}
		Assert.assertEquals(foundProducts, shoppingList.size(), "Not all products were found!");
	}

//			TODO	
//			public void addProductsToCart(List<Map.Entry<String, Integer>> shoppingList) throws InterruptedException {
//			    // Iterate over the shopping list
//					WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	//
//			    for (Map.Entry<String, Integer> entry : shoppingList) {
//			    	List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-name']"));
//			        String productName = entry.getKey(); // Get the product name
//			        String quantity = entry.getValue().toString();     // Get the quantity
	//
//			        // Iterate over the products on the page
//			        for (int i = 0; i < products.size(); i++) {
//			            String rawName = products.get(i).getText();
//			            String formattedName = rawName.split("-")[0].trim();
	//
//			            // Check if the product name matches
//			            if (formattedName.equals(productName)) {
//			                // Add the product to the cart 'quantity' times
//			            	 explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElements(setQuantityTextBox).get(i)));
//			            	driver.findElements(setQuantityTextBox).get(i).clear();
//							driver.findElements(setQuantityTextBox).get(i).sendKeys(quantity);
//			            	 explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElements(addToCartBtn).get(i)));
	//
//							driver.findElements(addToCartBtn).get(i).click();
//			                Thread.sleep(1000);
	//
//			                break; // Exit the inner loop once the product is found
//			            }
//			        }
//			    }
//			}
}
