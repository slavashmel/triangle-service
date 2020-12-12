package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "user")
    public static WebElement txtUsername;

    @FindBy(how = How.ID, using = "pass")
    public static WebElement txtPassword;

    @FindBy(how = How.CLASS_NAME, using = "loginHiddenButton")
    public static WebElement btnLogin;

    public static void EnterLoginCredentials(String username, String password) {
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
    }

    public static void ClickLoginButton() {
        btnLogin.submit();
    }
}
