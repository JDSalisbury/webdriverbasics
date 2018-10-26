import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Collection;
import java.util.Iterator;

import static junit.framework.TestCase.assertTrue;

public class DifferentWindowsNavTest {

    public WebDriver driver = new ChromeDriver();

    @Test
    public void shouldChangeBetweenWindows(){
        driver.get("http://www.compendiumdev.co.uk/selenium/frames");

        String framesWindowHandle = driver.getWindowHandle();
        System.out.println(framesWindowHandle);

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();

        String newWindowHandle = framesWindowHandle;
        System.out.println(newWindowHandle);

        Iterator aHandle = driver.getWindowHandles().iterator();
        while(newWindowHandle.equals(framesWindowHandle)){
            newWindowHandle = (String) aHandle.next();
        }
        System.out.println(newWindowHandle);
        driver.switchTo().window(newWindowHandle);

        assertTrue(driver.getTitle().contains("Selenium Simplified"));

        driver.switchTo().window(framesWindowHandle);

        assertTrue(driver.getTitle().contains("Frameset Example"));

        driver.switchTo().window(newWindowHandle);
        driver.close();
        driver.switchTo().window(framesWindowHandle);
        driver.close();
    }
}
