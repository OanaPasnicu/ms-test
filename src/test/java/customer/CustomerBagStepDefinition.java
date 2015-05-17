package customer;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerBagStepDefinition {
    private static final String SHIRT_URL = "http://www.marksandspencer.com/cotton-rich-lace-yoke-t-shirt-with-staynew/p/p60066756";

    private WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Given("^I have added a shirt to my bag$")
    public void i_have_added_a_shirt_to_my_bag() throws Throwable {
        openShirtUrl();
        selectShirtSize();
        addToBasket();
    }

    @When("^I view the contents of my bag$")
    public void i_view_the_contents_of_my_bag() throws Throwable {
        openBasket();
    }

    @Then("^I can see the contents of the bag include a shirt$")
    public void i_can_see_the_contents_of_the_bag_include_a_shirt() throws Throwable {
        WebElement productUrl = driver.findElement(By.cssSelector("section.product-item .heading-product a"));

        Assert.assertEquals(SHIRT_URL, productUrl.getAttribute("href"));
    }

    private void openBasket() {
        driver.findElement(By.cssSelector("ul.basket a")).click();
    }

    private void selectShirtSize() {
        driver.findElement(By.cssSelector("label[for='16DUMMY']")).click();
    }

    private void addToBasket() {
        driver.findElement(By.cssSelector("input.basket")).submit();

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("viewBagCountHeader"), "1"));
    }

    private void openShirtUrl() {
        driver.get(SHIRT_URL);
    }
}