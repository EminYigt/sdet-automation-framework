package pages;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    public LoginPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterUsername(String username) {
        WaitUtils.waitForVisibility(usernameInput).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        WaitUtils.waitForVisibility(passwordInput).sendKeys(password);
        return this;
    }

    public SecurePage clickLoginButton() {
        WaitUtils.waitForClickability(loginButton).click();
        return new SecurePage();
    }

    public String getFlashMessage() {
        return WaitUtils.waitForVisibility(flashMessage).getText();
    }
}