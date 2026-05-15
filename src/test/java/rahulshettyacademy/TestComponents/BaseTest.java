package rahulshettyacademy.TestComponents;

import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		// properties class can read global properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis); // how to send file as an input stream.
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		//if sysytem.property is true the it will execute secnd and if its false it will execute third
		// String browserName = prop.getProperty("browser");

//		if (browserName.equalsIgnoreCase("chrome")) {
//
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();// whatever driver we are cearting we are assigning it to global variable

		// TO RUN IN HEADLESS MODE
	//	if (browserName.equalsIgnoreCase("chrome")) {// this needs exact match
			if (browserName.contains("chrome")) {
			// to run in headless mode create a class named chrome option
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));// help u tpo run in full screen
			// TO RUN IN HEADLESS MODE

		} else if (browserName.equalsIgnoreCase("edge")) {
			// edge
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// if u passs json file it will scan the entire content of json and convert that
		// into one
		// string variavle
		// here we are reading json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert json content to hashmap.. basically we are convertingstring to
		// hashmap
		// with dependency Jackson Databind we can convert spring content to hashmap
		// download and add to pom .xml

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		// data is a list with two argument

		return data;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		// first cast your driver to screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	// how to se global properties for tests
	// New file should have .properties extension the using properties class in java
	// u will be able to parse this file
	// and Will be able to extract all global parametrr values
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApp() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);// CREATED OBJECT OF THT CLASS
		landingPage.goTo();// after creating object got ot landing page
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		// Thread.sleep(5000);
		driver.close();

	}
}
