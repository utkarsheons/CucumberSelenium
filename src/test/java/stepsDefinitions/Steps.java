package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import junit.framework.Assert;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Steps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("the user is on the nopCommerce login page")
    public void the_user_is_on_the_nop_commerce_login_page() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='First name:']//following-sibling::input")).sendKeys("abc");
        driver.findElement(By.xpath("//label[text()='Last name:']//following-sibling::input")).sendKeys("def");
        driver.findElement(By.xpath("//label[text()='Email:']//following-sibling::input")).sendKeys("test4x@gmail.com");
        driver.findElement(By.xpath("//label[text()='Password:']//following-sibling::input")).sendKeys("test4x@123");
        driver.findElement(By.xpath("//label[text()='Confirm password:']//following-sibling::input")).sendKeys("test4x@123");
        driver.findElement(By.xpath("//button[text()='Register']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Log out']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Log in']")).click();

    }
    @When("the user enters valid credentials \\(username: {string}, password: {string})")
    public void the_user_enters_valid_credentials_username_password(String username, String password) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In')]"))));
//        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='Email:']//following-sibling::input")).sendKeys(username);
        driver.findElement(By.xpath("//label[text()='Password:']//following-sibling::div/input")).sendKeys(password);


    }
    @When("the user clicks on the Login button")
    public void the_user_clicks_on_the_login_button() {
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

    }
    @Then("the user should be redirected to the My Account page")
    public void the_user_should_be_redirected_to_the_my_account_page() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li/a[text()='My account']"))));
//        Thread.sleep(2000);
        boolean status = driver.findElement(By.xpath("//li/a[text()='My account']")).isDisplayed();
        Assert.assertEquals(status, true);

    }
    @Then("the user should see a welcome message")
    public void the_user_should_see_a_welcome_message() {
        boolean status = driver.findElement(By.xpath("//h2[text()='Welcome to our store']")).isDisplayed();
        Assert.assertEquals(status, true);
        driver.quit();

    }

}
