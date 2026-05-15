package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver; // created local object

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;// assigning to local classs variable
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	// PageFctory
	@FindBy(css = ".mb-3") // 2 calling this webElement
	List<WebElement> products;

	@FindBy(css = ".ng-animating") // 2 calling this webElement
	WebElement spinner;

	By productsby = By.cssSelector(".mb-3");
	By toastMessage = By.cssSelector("#toast-container");
	By addToCart = By.cssSelector(".card-body button:last-of-type");

	/// action method
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsby);// first we are waiting for products to appear on page the we are
		return products;// returnig web elements

	}

	// Action method for filtering and streams
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {

		WebElement prod = getProductByName(productName);// -----how????
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementTodisappear(spinner);
		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

	}

	// can you apply page factory within WebElement.findElement-- no its not element
}
