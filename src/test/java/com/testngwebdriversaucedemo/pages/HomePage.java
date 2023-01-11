package com.testngwebdriversaucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMenuButton() {
        Utils.waitForElementVisibility(driver, By.id("react-burger-menu-btn"), Duration.ofSeconds(10)).click();
    }

    public void clickLogOutLink() {
        Utils.waitForElementVisibility(driver, By.id("logout_sidebar_link"), Duration.ofSeconds(10)).click();
    }

    public void clickItemLink(String item) {
        Utils.waitForElementVisibility(driver, By.xpath("//div[.=\"" + item + "\"]"), Duration.ofSeconds(10)).click();
    }

}