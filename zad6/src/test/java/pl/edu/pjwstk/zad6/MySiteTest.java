package pl.edu.pjwstk.zad6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

// UWAGA -- przerobilem dla phantomjs -- powinno dzialac na pracowni PJATK
public class MySiteTest {

    private static WebDriver driver;
    WebElement element;
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    @Before
    public void setUp() throws Exception {
        driver.manage().deleteAllCookies(); //czyszczenie ciasteczek(sesji)
        driver.manage().getCookies().clear();
    }

    @BeforeClass
    public static void driverSetup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "phantomjs"
        );
        driver = new PhantomJSDriver(caps);
    }


    @AfterClass
    public static void cleanp() {
        driver.quit();
    }

    @Test
    public void loginFailed() throws IOException, InterruptedException {
        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/admin.php");
        driver.findElement(By.name("user")).sendKeys("Adrian");
        driver.findElement(By.name("haslo")).sendKeys("test");
        driver.findElement(By.id("submit")).click();
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        element = driver.findElement(By.id("błąd"));
        FileUtils.copyFile(screenshot, new File("target/nieudane.png"));
        assertEquals(element.isDisplayed(), true);
        assertEquals(element.getAttribute("id"), "błąd");

    }

    @Test
    public void loginPassed() throws IOException, InterruptedException {
        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/admin.php");
        driver.findElement(By.name("user")).sendKeys("Adrian");
        driver.findElement(By.name("haslo")).sendKeys("projekt");
        driver.findElement(By.id("submit")).click();
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        element = driver.findElement(By.linkText("Wyloguj"));
        FileUtils.copyFile(screenshot, new File("target/zalogowany.png"));
        assertEquals(element.isDisplayed(), true);
        assertEquals(element.getText(), "Wyloguj");

    }

    @Test
    public void checkSession() throws IOException, InterruptedException {
        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/admin.php");
        driver.findElement(By.name("user")).sendKeys("Adrian");
        driver.findElement(By.name("haslo")).sendKeys("projekt");
        driver.findElement(By.id("submit")).click();
        Cookie session_id = driver.manage().getCookieNamed("PHPSESSID");
        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/projekt.php");
        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/admin.php");
        screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        FileUtils.copyFile(screenshot, new File("target/sesja.png"));
        assertEquals(session_id.getValue().length() > 0, true);
    }

}