package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import abstractComponentPackage.AbstractComponent;

public class CountryPage extends AbstractComponent {

	WebDriver driver;

	public CountryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select")
	WebElement staticDropdown;

	@FindBy(css = ".chkAgree")
	WebElement agreeTermsAndConditions;

	@FindBy(xpath = "//button[contains(text(), 'Proceed')]")
	WebElement proceed;

	public ConfirmationPage chooseCountryAndConfirmOrder(String country) {
		chooseCountry(country);
		acceptTermsAndConditions();
		confirmOrder();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

	public void chooseCountry(String country) {
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText(country);
	}

	public void acceptTermsAndConditions() {
		agreeTermsAndConditions.click();
	}

	public boolean confirmOrder() {
		if(agreeTermsAndConditions.isSelected()
				|| !getCurrentCountry().equals("Select")) {
			proceed.click();
			return true;
		}else {
			return false;
		}
	}
	
	public String getCurrentCountry() {
		Select dropdown = new Select(staticDropdown);
		String visibleCountryText = dropdown.getFirstSelectedOption().getText();
		return visibleCountryText;
	}
}
