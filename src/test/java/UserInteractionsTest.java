import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserInteractionsTest {

   public WebDriver driver = new ChromeDriver();


//   @Test
//    public void shouldClickTest(){
//
//       driver.get("https://www.compendiumdev.co.uk/selenium/basic_html_form.html");
//
//       Action clickOn = new Actions(driver).click(driver.findElement(By.cssSelector("input[value='cb1']"))). build();
//
//       clickOn.perform();
//       System.out.println();
//   }


    @Before
    public void resetPage(){
        driver.navigate().refresh();
    }

   @Test
    public void shouldDragDraggable1ToDroppable1(){
       driver.get("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");

       WebElement draggable1 = driver.findElement(By.id("draggable1"));
       WebElement droppable1 = driver.findElement(By.id("droppable1"));

       Actions actions = new Actions(driver);

       actions.clickAndHold(draggable1).moveToElement(droppable1).release().perform();

       assertEquals("Dropped!", droppable1.getText());
   }

   @Test
    public void shouldDragAndDropDraggable2ToDroppable1(){
       driver.get("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");

       WebElement draggable2 = driver.findElement(By.id("draggable2"));
       WebElement droppable1 = driver.findElement(By.id("droppable1"));

       Actions actions = new Actions(driver);

       actions.dragAndDrop(draggable2, droppable1).release().perform();

       assertEquals("Get Off Me!", droppable1.getText());
   }

   @Test
    public void shouldDrawSomethingOnCanvas() {
       driver.get("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");


       WebElement canvas = driver.findElement((By.id("canvas")));
        WebElement eventList = driver.findElement(By.id("keyeventslist"));

        int eventCount = eventList.findElements(By.tagName("li")).size();

        new Actions(driver).clickAndHold(canvas).moveByOffset(100,100).release().perform();

        assertTrue("we should have had some draw events", eventCount < eventList.findElements(By.tagName("li")).size());

        driver.close();
   }

}
