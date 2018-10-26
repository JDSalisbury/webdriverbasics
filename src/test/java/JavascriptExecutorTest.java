import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JavascriptExecutorTest {

    private WebDriver driver = new ChromeDriver();

    @Before
    public void resetBeforeTest() {
        driver.get("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");

        driver.navigate().refresh();
    }

    @Test
    public void callAJavaScriptFunctionOnThePage(){

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();

        assertEquals("By default app has 2 actions listed", 2, actionsCount);

        js.executeScript("clearCanvas()");
        js.executeScript("draw(1, 150, 150, 40, '#FF1C0A');");
        js.executeScript("draw(2, 140, 130, 20, '#00A308');");


        actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();

        assertEquals("By default app has 2 actions listed", 5, actionsCount);
    }

    @Test
    public void addTwoArgumentsAndAssertTheResults(){

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();

        assertEquals("By default app has 2 actions listed", 2, actionsCount);

        for(int testLoop=0; testLoop < 10; testLoop++) {
            js.executeScript("draw(0, arguments[0], arguments[1], 20, arguments[2]);", testLoop*20, testLoop*20, "#" + testLoop + testLoop + "0000");
        }

        actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals(12, actionsCount);
    }

    @Test
    public void returnValuesFromJavaScript(){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        assertEquals("Javascript should calc Correctly", 40L, js.executeScript("return (arguments[0]+arguments[1]);", 20, 20));
    }

    @Test
    public void returnHardCodedValueFromJavaScript(){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        assertEquals("return 10", 10L, js.executeScript("return 10;"));
    }

    @Test
    public void changeTitleUsingJavascript(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertEquals("Javascript Canvas Example", driver.getTitle());

        js.executeScript("document.title=arguments[0]", "bob");

        assertEquals("bob", driver.getTitle());
    }

    @Test
    public void useJQueryToHideBodyWithNoParams(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

        js.executeScript("$('body').hide();");

        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
    }

    @Test
    public void hideWebElementAsParam(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

        js.executeScript("$(arguments[0]).hide();", driver.findElement(By.cssSelector("#commands")));

        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
    }

    @Test
    public void javascriptRunsAsAnAnonymousFunctionButWeCanLeaveSomeBehind(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('alert triggered by webdriver');");

        assertThat(driver.switchTo().alert().getText(), is("alert triggered by webdriver"));
        driver.switchTo().alert().accept();

    }

    @Test
    public void waitInBrowserForTimeSample(){
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        long start = System.currentTimeMillis();

        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length -1], 500);");

        System.out.println("Elapsed Time: " + (System.currentTimeMillis() - start));

        assertTrue("Elapsed time should be greater than 500 milli", (System.currentTimeMillis() - start) > 500);
    }

    @Test
    public void useXMLHttpRequest(){

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        Object response = ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.open('GET', '/selenium/ajaxselect.php?id=2', true);" +
                        "xhr.onreadystatechange = function() {" +
                        "  if (xhr.readyState == 4) {" +
                        "    callback(xhr.responseText);" +
                        "  }" +
                        "};" +
                        "xhr.send();");
        System.out.println((String)response);

        assertThat((String) response, containsString("{optionValue:10, optionDisplay: 'C++'}"));
    }

    @After
    public void cleanUp(){
        driver.close();
    }

}
