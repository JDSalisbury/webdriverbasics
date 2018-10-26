import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ManipulationTest {


    public WebDriver driver = new ChromeDriver();


//    @Test
//    public void shouldClickThroughMenu(){
//        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
//
//        driver.findElement(By.cssSelector("option[value='3']")).click();
//        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector("option[value='23']")));
//        driver.findElement(By.cssSelector("option[value='23']")).click();
//        driver.findElement(By.name("submitbutton")).click();
//
//        new WebDriverWait(driver, 40).until(
////                ExpectedConditions.titleIs("Processed Form Details")
////        );
//
//        assertThat(driver.findElement(By.cssSelector("#_valuelanguage_id")).getText(), is("23"));
//
//
//    }
}
