package pl.edu.pjwstk.zad6;

import org.jbehave.web.selenium.WebDriverProvider;
import pl.edu.pjwstk.zad6.pages.HomePage;
import pl.edu.pjwstk.zad6.pages.LoginForm;

/**
 * Created by adrian on 04.04.17.
 */


public class Pages {

    private WebDriverProvider driverProvider;

    //Pages -- moze byc ich kilka
    private HomePage homePage;
    private LoginForm loginForm;

    public Pages(WebDriverProvider driverProvider) {
        super();
        this.driverProvider = driverProvider;
    }

    public HomePage homepage() {
        if (homePage == null) {
            homePage = new HomePage(driverProvider);
        }
        return homePage;
    }

    public LoginForm loginform() {
        if(loginForm == null) {
            loginForm = new LoginForm(driverProvider);
        }
        return loginForm;
    }

}