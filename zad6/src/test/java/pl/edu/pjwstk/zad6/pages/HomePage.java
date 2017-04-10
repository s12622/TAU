package pl.edu.pjwstk.zad6.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by adrian on 04.04.17.
 */

public class HomePage extends WebDriverPage {

    public HomePage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/projekt.php");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void userClicksAdministratorPanelLink(String adminPanelLink) {
        WebElement e = findElement(By.linkText(adminPanelLink));
        e.click();
    }
}
