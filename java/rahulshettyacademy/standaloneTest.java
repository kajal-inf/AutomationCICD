package rahulshettyacademy;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class standaloneTest {

	public static void main(String[] args) {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		LandingPage landingPage = new LandingPage(driver);// CREATED OBJECT OF THT CLASS

		driver.findElement(By.id("userEmail")).sendKeys("shreyasharma@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("rajput@123gudiyA");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// product.findement- search scope will be inside this section
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// wait until that toat msg appaears ion screen
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// class- ng-animating
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// use invisiblity(web elemet) if its slow

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// now we are valkidating if products are added to cart ornot.
		// .cartSection h3
		// xpath//*[@class='cartSection']
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// again apply stream to check if product zarais there
		Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		// we are just checking if any product text matched to zara. and if it will
		// match it will return boolean value as true
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "india").build().perform();
//		driver.findElement(By.cssSelector(".form-group input")).sendKeys("india");
//		List<WebElement> listCountry=driver.findElements(By.cssSelector("button[class*='ng-star-inserted']"));
//		listCountry.stream().filter(s->s.getText().equalsIgnoreCase("india"))
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();
		// put assertion to check if text appearys
		String confrmmess = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confrmmess.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//new comments are added
	}

}
