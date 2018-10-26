import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

public class FirstTest {

    @Test
    public void testApp(){
        System.out.println("The test test passed!");
        assertTrue(true);
    }

    @Test
    public void testAssert() {
        String underTest = "Something";

        assertEquals(underTest, "Something");
    }

    @Test
    public void driverIsTheKing(){

        WebDriver driver = new ChromeDriver();

        driver.get("https://JDSalisbury.github.io");

        String title = driver.getTitle();
        System.out.println("Title test grabbed: " + title);
        assertTrue(title.startsWith("Portfolio"));

        driver.close();
    }

}
