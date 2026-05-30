package tests;

import base.BaseTest;
import com.fasterxml.jackson.core.type.TypeReference;
import models.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SecurePage;
import utils.JsonUtils;
import utils.RetryAnalyzer;

import java.util.List;
import java.util.Map;

public class LoginTest extends BaseTest {

    @Test(groups = {"regression", "login"})
    public void validLoginTest() {

        HomePage homePage = new HomePage();

        LoginPage loginPage = homePage.clickFormAuthentication();

        SecurePage securePage = loginPage
                .enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!")
                .clickLoginButton();

        Assert.assertTrue(
                securePage.getFlashMessage().contains("You logged into a secure area!"),
                "Login success message is not displayed!"
        );
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {

        List<LoginTestData> users = JsonUtils.readJsonArray(
                "invalid-login-data.json",
                new TypeReference<List<LoginTestData>>() {}
        );

        Object[][] data = new Object[users.size()][1];

        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }

        return data;
    }

    @Test(groups = {"regression", "login", "negative"}, dataProvider = "invalidLoginData")
    public void invalidLoginTest(LoginTestData loginTestData) {

        HomePage homePage = new HomePage();

        LoginPage loginPage = homePage.clickFormAuthentication();

        loginPage
                .enterUsername(loginTestData.getUsername())
                .enterPassword(loginTestData.getPassword())
                .clickLoginButton();

        Assert.assertTrue(
                loginPage.getFlashMessage().contains(loginTestData.getExpectedMessage()),
                "Invalid login message is not displayed!"
        );
    }
}