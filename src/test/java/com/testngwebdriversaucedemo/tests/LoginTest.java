package com.testngwebdriversaucedemo.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testngwebdriversaucedemo.pages.BasePage;
import com.testngwebdriversaucedemo.pages.DataProviderTest;
import com.testngwebdriversaucedemo.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private BasePage basePage;

    @BeforeMethod
    public void initalize() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        basePage = new BasePage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test(dataProvider = "dataTest", dataProviderClass = DataProviderTest.class)
    public void validLogin(String username, String password) {
        loginPage.signIn(username, password);
        Assert.assertTrue(basePage.checkUrl("https://www.saucedemo.com/inventory.html"));
    }

    @Test(dataProvider = "dataTest", dataProviderClass = DataProviderTest.class, priority = 1)
    public void invalidLogin(String username, String password) {
        loginPage.signIn(username, password);
        Assert.assertTrue(basePage.checkUrl("https://www.saucedemo.com/"));
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}