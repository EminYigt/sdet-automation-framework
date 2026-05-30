package utils;

import config.ConfigReader;
import drivers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private WaitUtils() {
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(ConfigReader.getInt("timeout"))
        );
    }

    public static WebElement waitForVisibility(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickability(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitForTitleContains(String titlePart) {
        return getWait().until(ExpectedConditions.titleContains(titlePart));
    }
}