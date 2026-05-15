package rahulshettyacademy.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException
	{
		//properties class can read global properties
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis); // how to send file as an input stream.
		String browserName =prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{

			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();// whatever driver we are cearting we are assigning it to global  variable
			
		}
		else if(browserName.equalsIgnoreCase("edge") )
		{
			//edge
		}
		else if(browserName.equalsIgnoreCase("firefox") )
		{
			//firefox
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		
		
		
	}
	
	// how to se global properties for tests
	// New file should have .properties extension the using properties class in java u will be able to parse this file 
	// and Will be able to extract all global parametrr values
	@BeforeMethod
	public LandingPage launchApp() throws IOException
	{
		 driver=initializeDriver();
		  landingPage = new LandingPage(driver);// CREATED OBJECT OF THT CLASS
			landingPage.goTo();// after creating object got ot landing page
			return landingPage;
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.close();

	}
}
