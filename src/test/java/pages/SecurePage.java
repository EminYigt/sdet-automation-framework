package pages;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class SecurePage {

    private final WebDriver driver;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    @FindBy(css = "a.button.secondary.radius")
    private WebElement logoutButton;

    public SecurePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public String getFlashMessage() {
        return WaitUtils.waitForVisibility(flashMessage).getText();
    }

    public LoginPage clickLogout() {
        WaitUtils.waitForClickability(logoutButton).click();
        return new LoginPage();
    }
}