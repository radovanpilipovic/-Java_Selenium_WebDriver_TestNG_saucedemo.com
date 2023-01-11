package com.testngwebdriversaucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFirstName() {
        return Utils.waitForElementVisibility(driver, By.id("first-name"), Duration.ofSeconds(10));
    }

    public void setFirstName(String value) {
        WebElement firstName = this.getFirstName();
        firstName.clear();
        firstName.sendKeys(value);
    }

    public WebElement getLastName() {
        return Utils.waitForElementVisibility(driver, By.id("last-name"), Duration.ofSeconds(10));
    }

    public void setLastName(String value) {
        WebElement lastName = this.getLastName();
        lastName.clear();
        lastName.sendKeys(value);
    }

    public WebElement getPostalCode() {
        return Utils.waitForElementVisibility(driver, By.id("postal-code"), Duration.ofSeconds(10));
    }

    public void setPostalCode(String value) {
        WebElement postalCode = this.getPostalCode();
        postalCode.clear();
        postalCode.sendKeys(value);
    }

    public void customerData(String firstName, String lastName, String postalCode) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPostalCode(postalCode);
        this.clickContinueButton();
    }

    public void clickCheckoutButton() {
        Utils.waitForElementVisibility(driver, By.name("checkout"), Duration.ofSeconds(10)).click();
    }

    public void clickContinueButton() {
        Utils.waitForElementVisibility(driver, By.name("continue"), Duration.ofSeconds(10)).click();
    }

    public void clickFinishButton() {
        Utils.waitForElementVisibility(driver, By.name("finish"), Duration.ofSeconds(10)).click();
    }
    
    public boolean confirmationLetter(String text) {
        return Utils.waitForText(driver,By.className("complete-header") ,text, Duration.ofSeconds(10));
    }
    
    public boolean checkoutOverviewTitle(String text) {
        return Utils.waitForText(driver,By.className("title") ,text, Duration.ofSeconds(10));
    }
}