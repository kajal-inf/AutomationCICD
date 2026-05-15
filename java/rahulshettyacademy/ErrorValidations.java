package rahulshettyacademy;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

	@Test
	public void LoginErrorValidation() throws IOException {

		// LandingPage landingPage=launchApp();
		landingPage.loginApplication("smhreyasharma@gmail.com", "rmajput@123gudiyA");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

		@Test
		public void ProductErrorValidation() throws IOException {
			String productName = "ZARA COAT 3";
			//LandingPage landingPage=launchApp();
			ProductCatalogue productCatalogue = landingPage.loginApplication("kajal1234@gmail.com", "rajput@123gudiyA");
			// ProductCatalogue productCatalogue = new ProductCatalogue(driver);
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage = productCatalogue.goToCartPage();
			// CartPage cartPage= new CartPage(driver);
			Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);// does not go in page object
			

		}


}
