import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CookiesExerciseTest {

    private WebDriver driver = new ChromeDriver();
    private WebElement queryInput;
    private WebElement submitButton;


    private void refreshPageObjects() {
        queryInput = driver.findElement(By.name("q"));
        submitButton = driver.findElement(By.name("btnG"));
    }

    @Before
    public void setup(){
        driver.get("http://compendiumdev.co.uk/selenium/search.php");
        driver.manage().deleteAllCookies();

        refreshPageObjects();
    }

    @Test
    public void doASearchAndCheckForCookies(){
        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        Set<Cookie> cookies = driver.manage().getCookies();

        for(Cookie aCookie : cookies) {
            if(aCookie.getName().contentEquals("seleniumSimplifiedSearchNumVisits")){
                assertEquals("Should be my First Visit", String.valueOf(1), aCookie.getValue());
            }
        }
    }

    @Test
    public void getCookieDirectly() {

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals("Should be my first visit", 1, Integer.parseInt(aCookie.getValue()));
    }

    @Test
    public void changeCookieVisitsCount(){
        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        refreshPageObjects();

        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals("Should be my first visit", 1, Integer.parseInt(aCookie.getValue()));

        // clone cookie and use my value.
        Cookie aNewCookie = new Cookie(aCookie.getName(), String.valueOf(42), aCookie.getDomain(), aCookie.getPath(), aCookie.getExpiry(), aCookie.isSecure());

        driver.manage().deleteCookie(aCookie);
        driver.manage().addCookie(aNewCookie);

        queryInput.clear();
        queryInput.sendKeys("Visits Changed Test");
        submitButton.click();

        aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals("Should be my first visit", 43, Integer.parseInt(aCookie.getValue()));

    }

    @After
    public void cleanUp(){
        driver.quit();
    }

}
