package base;

import config.ConfigReader;
import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        logger.info("Test setup started.");
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        logger.info("Navigated to base URL: {}",
                ConfigReader.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            logger.error("Test failed: {}", result.getName());

            String screenshotPath = ScreenshotUtils.takeScreenshot(result.getName());
            logger.info("Screenshot saved: {}", screenshotPath);

            ScreenshotUtils.attachScreenshotToAllure();
            logger.info("Screenshot attached to Allure report");
        }

        DriverManager.quitDriver();
        logger.info("Test teardown completed");
    }
}