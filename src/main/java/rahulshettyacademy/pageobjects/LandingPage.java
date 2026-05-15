package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver; // created local object

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;// assigning to local classs variable
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmail=driver.findElement(By.id("userEmail"));
	// PageFctory
	// driver.findElement(By.id("userEmail"))
	@FindBy(id = "userEmail")
	WebElement userEmail;
	// how this annotation know about constructr-- so there is a method called
	// initElements
	// which will take car of constructing using driver argument
	// driver.findElement(By.id("userPassword"))
	@FindBy(id = "userPassword")
	WebElement userPassword;
	// driver.findElement(By.id("login")).click();
	@FindBy(id = "login")
	WebElement submit;

	// .ng-tns-c4-9.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	// implementing action methodsfor Page Factory webElement to impliment logic
	// action Method
	public ProductCatalogue loginApplication(String email, String Password) {
		// .sendKeys("shreyasharma@gmail.com");
		userEmail.sendKeys(email);
		userPassword.sendKeys(Password);// should come from test not from page object
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}

	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	// Action Method
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

}
