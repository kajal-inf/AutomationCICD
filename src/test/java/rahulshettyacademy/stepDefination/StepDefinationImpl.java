package rahulshettyacademy.stepDefination;

import java.io.IOException;

import io.cucumber.java.en.Given;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.LandingPage;

public class StepDefinationImpl extends BaseTest {
	public LandingPage landingPage;

	@Given("i landed on Ecommerce page")
	public void i_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApp();
	}
	@Given("^logged in with username(.+)and password(.+)$")
}
