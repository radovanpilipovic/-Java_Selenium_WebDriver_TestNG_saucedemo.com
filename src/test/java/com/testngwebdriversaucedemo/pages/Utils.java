package com.testngwebdriversaucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static WebElement waitForElementVisibility(WebDriver driver, By selector, Duration waitInterval) {
        WebElement element = new WebDriverWait(driver, waitInterval)
                .until(ExpectedConditions.visibilityOfElementLocated(selector));
        return element;
    }

    public static WebElement waitToBeClickable(WebDriver driver, By selector, Duration waitInterval) {
        WebElement element = (new WebDriverWait(driver, waitInterval))
                .until(ExpectedConditions.elementToBeClickable(selector));
        return element;
    }

    public static boolean waitForUrl(WebDriver driver, String url, Duration waitInterval) {
        return (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.urlToBe(url));
    }

    public static boolean waitForText(WebDriver driver, By locator, String text, Duration waitInterval) {
        return (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.textToBe(locator, text));
    }

    public static boolean isPresent(WebDriver webdriver, By selector) {
        try {
            webdriver.findElement(selector);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
