package pl.edu.pjwstk.zad6;

/**
 * Created by adrian on 04.04.17.
 */


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;


public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on login form page")
    public void userOnLoginFormPage() {
        pages.loginform().open();
    }

    @When("user inserts login: $loginKey in input with name: $loginName")
    public void userInsertsLoginData(String loginName, String loginKey) {
        pages.loginform().setLoginData(loginKey, loginName);
    }

    @Then("user inserts password: $passwordKey in input with name: $passwordNane")
    public void userInsertsPasswordData(String passwordName, String passwordKey) {
        pages.loginform().setPasswordData(passwordKey, passwordName);
    }

    @When("user clicks on submit button with id: $submitId")
    public void userClicksOnSubmitButton(String submitId) {
        pages.loginform().userClicksLoginSubmit(submitId);
    }

    @Then("site displays failed login text in div with id: $failedDivId")
    public void siteDisplaysFailedLoginText(String failedDivId) {
        pages.loginform().getFailedDataId(failedDivId);
    }

    @Then("site contains link with text: $logoutText")
    public void siteContainsLogoutTextLink(String logoutText) {
        pages.loginform().getPassedDataId(logoutText);
    }

    @When("user clicks on link with text: $homePageText")
    public void userClicksOnHomePageLinkText(String homePageText) {
        pages.loginform().userClicksBackToHomePageLink(homePageText);
    }

    @Then("user clicks on link with text: $administratorPanelText")
    public void userClicksOnAdministratorPanelLinkText(String administratorPanelText) {
        pages.homepage().userClicksAdministratorPanelLink(administratorPanelText);
    }

    @Then("session is restored and session id's length is greater than 0: $sessionLengthBool")
    public void sessionIdLengthIsGreater(Boolean $sessionLengthBool) {
        pages.loginform().sessionIdLengthIsGreater($sessionLengthBool);
    }
}