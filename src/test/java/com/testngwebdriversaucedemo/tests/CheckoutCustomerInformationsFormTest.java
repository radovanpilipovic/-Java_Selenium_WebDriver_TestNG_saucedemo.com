package com.testngwebdriversaucedemo.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testngwebdriversaucedemo.pages.BasePage;
import com.testngwebdriversaucedemo.pages.CheckoutPage;
import com.testngwebdriversaucedemo.pages.DataProviderTest;
import com.testngwebdriversaucedemo.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class CheckoutCustomerInformationsFormTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private BasePage basePage;
    private CheckoutPage checkoutPage;
    private Dotenv dotenv;

    @BeforeMethod
    public void initalize() {
        WebDriverManager.chromedriver().setup();
        dotenv = Dotenv.configure().load();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        basePage = new BasePage(driver);
        checkoutPage = new CheckoutPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.signIn(dotenv.get("STANDARD_USER"), dotenv.get("VALID_PASSWORD"));
        basePage.addItem("backpack");
        basePage.clickShoppingCartLink();
        checkoutPage.clickCheckoutButton();
    }

    @Test(priority = 0)
    public void validlyFilledCustomerInfoFields() {
        checkoutPage.customerData("John", "Dart", "123456");
        Assert.assertTrue(checkoutPage.checkoutOverviewTitle("CHECKOUT: OVERVIEW"));
    }

    @Test(dataProvider = "dataTest", dataProviderClass = DataProviderTest.class)
    public void invalidlyFilledCustomerInfoFields(String firstName, String lastName, String postalCode) {
        checkoutPage.customerData(firstName, lastName, postalCode);
        checkoutPage.clickContinueButton();
        Assert.assertTrue(basePage.errorMessageVisible());
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
