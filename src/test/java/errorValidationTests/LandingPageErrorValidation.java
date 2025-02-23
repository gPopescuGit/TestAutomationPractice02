package errorValidationTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectsPackage.CheckoutPage;
import pageObjectsPackage.CountryPage;
import testComponents.BaseTest;

public class LandingPageErrorValidation extends BaseTest{

	
	@Test(groups = "ErrorHandling")
	public void AttemptCheckoutWithoutProduct() throws InterruptedException {

		CheckoutPage checkoutPage = landingPage.goToCheckout();
		Assert.assertEquals(checkoutPage, null);
	}
}
