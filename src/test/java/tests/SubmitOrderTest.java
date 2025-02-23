package tests;

import java.time.Duration;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsPackage.CheckoutPage;
import pageObjectsPackage.CountryPage;
import pageObjectsPackage.LandingPage;
import pageObjectsPackage.TopDealsPage;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	List<String>shoppingList = Arrays.asList("Beans","Potato", "Brocolli","Pears", "Pista");
//	List<Map.Entry<String, Integer>> shoppingList = new ArrayList<>();

	
	@Test(groups="Purchase")
	public void submitOrderNoPromoCodeTest() throws InterruptedException {

		landingPage.addProductsToCart(shoppingList);
		CheckoutPage checkoutPage = landingPage.goToCheckout();
		CountryPage countryPage = checkoutPage.placeOrder();
		countryPage.chooseCountryAndConfirmOrder("Cuba");
	}
	
	@Test (groups = "Purchase")
	public void submitOrderWithPromoCodeTest() {
		landingPage.addProductsToCart(shoppingList);
		CheckoutPage checkoutPage = landingPage.goToCheckout();
		checkoutPage.addPromoCode("rahulshettyacademy");
		CountryPage countryPage = checkoutPage.placeOrder();
		countryPage.chooseCountryAndConfirmOrder("Cuba");
	}
	
	@Test(groups = "Purchase")
	public void submitOrderAllProductsOnceTest() throws InterruptedException {
		//send empty shopping list 
		List<String> allProductsOnceShoppingList = new ArrayList<>();
		landingPage.addProductsToCart(allProductsOnceShoppingList);
		CheckoutPage checkoutPage = landingPage.goToCheckout();
		CountryPage countryPage = checkoutPage.placeOrder();
		countryPage.chooseCountryAndConfirmOrder("China");
	}
	
	@Test(groups = "Purchase")
	public void submitOrderSearchProductTest() {
		landingPage.searchAndAddProductToCart("Potato");
		CheckoutPage checkoutPage = landingPage.goToCheckout();
		CountryPage countryPage = checkoutPage.placeOrder();
		countryPage.chooseCountryAndConfirmOrder("Cuba");
	}
	
	@Test(groups = "Purchase")
	public void findProductDealTest() {
		TopDealsPage tdp = landingPage.goToTopDeals();
		//change tab
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		String foundDeal = tdp.searchDeal("Potato");
		Assert.assertEquals(foundDeal, "22");//hardcoded value
	}
	
	
}
