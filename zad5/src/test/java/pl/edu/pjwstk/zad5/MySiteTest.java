package pl.edu.pjwstk.zad5;

/**
 * Created by adrian on 28.03.17.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import org.openqa.selenium.chrome.ChromeDriver;


public class MySiteTest {
    private static WebDriver driver;
    WebElement element;

    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    @BeforeClass
    public static void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "/home/adrian/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Before
    public void setUp() throws Exception {
        driver.manage().deleteAllCookies(); //czyszczenie ciasteczek(sesji)
    }

    @Test
    public void homePage() throws IOException, InterruptedException {
        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/projekt.php");
        element = driver.findElement(By.id("powitanie"));
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("/home/adrian/zad5/glowna.png"));
        assertNotNull(screenshot);
        assertEquals(element.isDisplayed(), true);
        assertEquals(element.getText(), "Witaj w mojej księdze gości");
    }

    @Test
    public void administratorPanel() throws IOException, InterruptedException {
        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/projekt.php");
        driver.findElement(By.linkText("Panel administracyjny")).click();
        element = driver.findElement(By.name("user"));
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        FileUtils.copyFile(screenshot, new File("/home/adrian/zad5/logowanie.png"));
        assertEquals(element.isDisplayed(), true);
        assertEquals(element.getAttribute("name"), "user");
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
        FileUtils.copyFile(screenshot, new File("/home/adrian/zad5/nieudane.png"));
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
        FileUtils.copyFile(screenshot, new File("/home/adrian/zad5/zalogowany.png"));
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
        FileUtils.copyFile(screenshot, new File("/home/adrian/zad5/sesja.png"));
        assertEquals(session_id.getValue().length() > 0, true);
    }


    @Test
    public void javascriptTest() throws IOException {
        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/projekt.php");
        element = driver.findElement(By.id("powitanie"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='5px solid blue'", element);
        screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        FileUtils.copyFile(screenshot, new File("/home/adrian/zad5/js.png"));
        assertNotNull(element);
        assertEquals(element.isDisplayed(), true);
        assertEquals(element.getCssValue("border"), "5px solid rgb(0, 0, 255)");
    }
    @Test(expected = org.openqa.selenium.WebDriverException.class)
    public void jQueryTest() throws IOException {

        driver.get("http://szuflandia.pjwstk.edu.pl/~s12622/WPR/Projekt/projekt.php");
        element = (WebElement) ((JavascriptExecutor)driver).executeScript("return $('.jqueryTest')[0]");
        assertNotNull(element);
        assertEquals(element.isDisplayed(), false);
    }

    @AfterClass
    public static void cleanp() {
        driver.quit();
    }
}
