package utils;

import drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import io.qameta.allure.Attachment;

public class ScreenshotUtils {

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] attachScreenshotToAllure() {

        return ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    public static String takeScreenshot(String testName) {

        File screenshot = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.FILE);

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

        Path destination = Paths.get(screenshotPath);

        try {
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot.toPath(), destination);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }

        return screenshotPath;
    }
}