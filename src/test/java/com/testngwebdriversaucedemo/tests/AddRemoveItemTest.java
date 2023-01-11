package com.testngwebdriversaucedemo.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testngwebdriversaucedemo.pages.BasePage;
import com.testngwebdriversaucedemo.pages.HomePage;
import com.testngwebdriversaucedemo.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class AddRemoveItemTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private BasePage basePage;
    private HomePage homePage;
    private Dotenv dotenv;

    @BeforeMethod
    public void initalize() {
        WebDriverManager.chromedriver().setup();
        dotenv = Dotenv.configure().load();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        basePage = new BasePage(driver);
        homePage = new HomePage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.signIn(dotenv.get("STANDARD_USER"), dotenv.get("VALID_PASSWORD"));
    }

    @Test(priority = 0)
    public void addItemFromHomePage() {
        basePage.addItem("backpack");
        Assert.assertTrue(basePage.itemInShoppingCart());
    }

    @Test(priority = 1)
    public void removeItemFromHomePage() {
        basePage.addItem("backpack");
        basePage.removeItem("backpack");
        Assert.assertFalse(basePage.itemInShoppingCart());
    }

    @Test(priority = 2)
    public void addItemFromItemPage() {
        homePage.clickItemLink("Sauce Labs Backpack");
        basePage.addItem("backpack");
        Assert.assertTrue(basePage.itemInShoppingCart());
    }

    @Test(priority = 3)
    public void removeItemFromItemPage() {
        homePage.clickItemLink("Sauce Labs Backpack");
        basePage.addItem("backpack");
        basePage.removeItem("backpack");
        Assert.assertFalse(basePage.itemInShoppingCart());
    }

    @Test(priority = 4)
    public void removeItemFromCheckoutPage() {
        basePage.addItem("backpack");
        basePage.clickShoppingCartLink();
        basePage.removeItem("backpack");
        Assert.assertFalse(basePage.itemInShoppingCart());
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
