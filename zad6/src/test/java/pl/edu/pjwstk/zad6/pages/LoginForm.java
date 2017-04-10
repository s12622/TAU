package pl.edu.pjwstk.zad6.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginForm extends WebDriverPage {

    public LoginForm(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/admin.php");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void setLoginData(String loginName, String loginKey) {
        WebElement e = findElement(By.name(loginName));
        e.sendKeys(loginKey);
    }

    public void setPasswordData(String passwordName, String passwordKey) {
        WebElement e = findElement(By.name(passwordName));
        e.sendKeys(passwordKey);
    }

    public void userClicksLoginSubmit(String submitId) {
        WebElement e = findElement(By.id(submitId));
        e.click();
    }

    public String getFailedDataId(String failedDivId) {
        WebElement e = findElement(By.id(failedDivId));
        return e.getText();
    }

    public String getPassedDataId(String logoutText) {
        WebElement e = findElement(By.linkText(logoutText));
        return e.getText();
    }

    public void userClicksBackToHomePageLink(String backToHomePageLink) {
        WebElement e = findElement(By.linkText(backToHomePageLink));
        e.click();
    }

    public Boolean sessionIdLengthIsGreater(Boolean sessionIdGrater) {
        Cookie session_id = manage().getCookieNamed("PHPSESSID");
        assertEquals(session_id.getValue().length() > 0, true);
        return session_id.getValue().length() > 0;
    }
}
