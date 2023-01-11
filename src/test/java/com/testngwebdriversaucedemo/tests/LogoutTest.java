package com.testngwebdriversaucedemo.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.testngwebdriversaucedemo.pages.BasePage;
import com.testngwebdriversaucedemo.pages.HomePage;
import com.testngwebdriversaucedemo.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class LogoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private BasePage basePage;
    private HomePage homePage;
    private Dotenv dotenv;

    @BeforeTest
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
    }

    @Test
    public void logOut() {
        loginPage.signIn(dotenv.get("STANDARD_USER"), dotenv.get("VALID_PASSWORD"));
        homePage.clickMenuButton();
        homePage.clickLogOutLink();
        basePage.checkUrl("https://www.saucedemo.com/");
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
