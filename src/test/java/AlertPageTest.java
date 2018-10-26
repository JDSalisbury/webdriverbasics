import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class AlertPageTest {

    public static WebDriver driver = new ChromeDriver();

    @Test
    public void shouldAcceptAndDismissAlert(){
        driver.get("http://compendiumdev.co.uk/selenium/alerts.html");

        WebElement alertTriggerButton = driver.findElement(By.cssSelector("input#alertexamples"));

        alertTriggerButton.click();

        String alertMessage = "I am an alert box!";

        assertEquals(alertMessage, driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();
    }

    @Test
    public void shouldDismissAndAcceptAlert(){
        driver.get("http://compendiumdev.co.uk/selenium/alerts.html");
        WebElement alertTriggerButton = driver.findElement(By.cssSelector("input#alertexamples"));

        alertTriggerButton.click();


        String alertMessage = "I am an alert box!";

        assertEquals(alertMessage, driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void shouldConfirmAlert(){
        driver.get("http://compendiumdev.co.uk/selenium/alerts.html");
        WebElement alertTriggerButton = driver.findElement(By.id("confirmexample"));
        WebElement confirmResult = driver.findElement(By.id("confirmreturn"));

        alertTriggerButton.click();

        Alert confirmAlert = driver.switchTo().alert();

        confirmAlert.accept();

        assertEquals("true", confirmResult.getText());
    }

    @AfterClass
    public static void cleanUp(){
        driver.quit();
    }

}
