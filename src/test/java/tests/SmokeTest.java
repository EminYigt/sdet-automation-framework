package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void verifyHomePageTitle() {

        String actualTitle = driver.getTitle();

        Assert.assertFalse(
                actualTitle.isEmpty(),
                "Title is empty!"
        );

        System.out.println(actualTitle);
    }
}