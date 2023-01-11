package com.testngwebdriversaucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver; 

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUsername() {
        return Utils.waitForElementVisibility(driver, By.id("user-name"), Duration.ofSeconds(10));
    }

    public void setUsername(String value) {
        WebElement username = this.getUsername();
        username.clear();
        username.sendKeys(value);
    }

    public WebElement getPassword() {
        return Utils.waitForElementVisibility(driver, By.id("password"), Duration.ofSeconds(10));
    }

    public void setPassword(String value) {
        WebElement password = this.getPassword();
        password.clear();
        password.sendKeys(value);
    }

    public void clickLoginButton() {
        Utils.waitToBeClickable(driver, By.name("login-button"), Duration.ofSeconds(10)).click();
    }

    public void signIn(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        this.clickLoginButton();
    }
    
}