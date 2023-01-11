package com.testngwebdriversaucedemo.pages;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import io.github.cdimascio.dotenv.Dotenv;

public class DataProviderTest {

    private Dotenv dotenv;

    @DataProvider(name = "dataTest")

    public Object[][] dataSet1(Method m) {
        dotenv = Dotenv.configure().load();
        Object[][] testdata = null;
        if (m.getName().equals("validLogin")) {
            testdata = new Object[][] {
                    { dotenv.get("STANDARD_USER"), dotenv.get("VALID_PASSWORD") },
                    { dotenv.get("PROBLEM_USER"), dotenv.get("VALID_PASSWORD") },
                    { dotenv.get("PERFORMANCE_GLITCH_USER"), dotenv.get("VALID_PASSWORD") }
            };
        } else if (m.getName().equals("invalidLogin")) {
            testdata = new Object[][] {
                    { dotenv.get("LOCKED_OUT_USER"), dotenv.get("VALID_PASSWORD") },
                    { "", dotenv.get("VALID_PASSWORD") },
                    { dotenv.get("STANDARD_USER"), "" },
                    { "", "" },
                    { dotenv.get("STANDARD_USER"), dotenv.get("INVALID_PASSWORD") }
            };
        } else if (m.getName().equals("invalidlyFilledCustomerInfoFields")) {
            testdata = new Object[][] {
                    { "", "", "" },
                    { "exFirstName", "", "" },
                    { "", "exLastName", "" },
                    { "", "", "exPostalCode" },
                    { "exFirstName", "exLastName", "" },
                    { "exFirstName", "", "exPostalCode" },
                    { "", "exLastName", "" },
                    { "", "exLastName", "exPostalCode" }
            };
        }
        return testdata;
    }

}
