package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import abstractComponentPackage.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		checkConfirmationMessage();
	}
	
	By confirmationMsgLocator = By.xpath("//span[contains(text(),'Thank you, your order has been placed successfully')]");
	String expectedMsg = "Thank you, your order has been placed successfully";
	
	public void checkConfirmationMessage() {
		String confirmationMsg = driver.findElement(confirmationMsgLocator).getText();
		Assert.assertTrue(confirmationMsg.contains(expectedMsg));
	}

}
