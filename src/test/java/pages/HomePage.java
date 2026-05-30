package pages;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class HomePage {

    private final WebDriver driver;

    @FindBy(linkText = "Form Authentication")
    private WebElement formAuthenticationLink;

    public HomePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickFormAuthentication() {
        WaitUtils.waitForClickability(formAuthenticationLink).click();
        return new LoginPage();
    }
}