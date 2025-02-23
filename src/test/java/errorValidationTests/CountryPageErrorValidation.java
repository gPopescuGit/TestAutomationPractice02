package errorValidationTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsPackage.CheckoutPage;
import pageObjectsPackage.CountryPage;
import testComponents.BaseTest;

public class CountryPageErrorValidation extends BaseTest{
	
	@Test(groups = "ErrorHandling")
	public void submitOrderWithoutTermsAndConditionsTest() {
		landingPage.searchAndAddProductToCart("Potato");
		CheckoutPage checkoutPage = landingPage.goToCheckout();
		CountryPage countryPage = checkoutPage.placeOrder();
		countryPage.chooseCountry("Cuba");
		boolean confirmOrder = countryPage.confirmOrder();
		Assert.assertEquals(confirmOrder, false, "Expected Terms and conditions to NOT be selected");
	}
	
	@Test(groups = "ErrorHandling")
	public void submitOrderWithoutCountryTest() {
		landingPage.searchAndAddProductToCart("Potato");
		CheckoutPage checkoutPage = landingPage.goToCheckout();
		CountryPage countryPage = checkoutPage.placeOrder();
		
		String currentCountry = countryPage.getCurrentCountry();
		Assert.assertEquals(currentCountry, "Select", "Expected country to NOT be selected");
		countryPage.acceptTermsAndConditions();
		boolean confirmOrder = countryPage.confirmOrder();
		/*
		 * ConfirmOrderPage is reached even without country selected due to minor bug in the page implementation
		 */
		Assert.assertEquals(confirmOrder, false, "Terms and conditions or Country to are selected when neither of them was expected");
	}
}
