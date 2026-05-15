package rahulshettyacademy;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	// public void SubmitOrder(String email, String password,String productName)
	// throws IOException, InterruptedException {
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		String countryName = "india";

		// LandingPage landingPage=launchApp();
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		// CartPage cartPage= new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);// does not go in page object
		CheckoutPage checkout = cartPage.goToCheckout();
		checkout.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkout.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// driver.close();

	}

	// run this after above test
	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("shreyasharma@gmail.com", "rajput@123gudiyA");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}


	
	//extent reports=creates html reports an dwe can add screenshorts to it
	
	
	
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map=new HashMap<String, String>();
//		//now setting key value pair
//		map.put("email", "shreyasharma@gmail.com");
//		map.put("password", "rajput@123gudiyA");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String, String> map1=new HashMap<String, String>();
//		//now setting key value pair
//		map.put("email", "kajal1234@gmail.com");
//		map.put("password", "rajput@123gudiyA");
//		map.put("productName", "ADIDAS ORIGINAL");
//		
		// lets say you want to run first test with two different datasets
		// create 2d arrays which accepts multiple sets
		// return new Object[][] {{"shreyasharma@gmail.com","rajput@123gudiyA","ZARA
		// COAT 3"},{"kajal1234@gmail.com","rajput@123gudiyA","ADIDAS ORIGINAL"}};
		// next is how to drive this data from external source
		// if u have more thn 16 parametrs then use hashmap

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}// write one utility which scans this jason and create hashmap out of it

}
