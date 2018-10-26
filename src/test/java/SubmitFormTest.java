import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class SubmitFormTest {

    public WebDriver driver = new ChromeDriver();
    public SubmitFormTest() throws MalformedURLException {
    }

    public void submitAndWait() {
        driver.findElement(By.cssSelector("input[type='submit']:nth-child(2)")).click();
//        new WebDriverWait(driver, 20).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    @Test
    public void shouldSubmitFormAndAssertPageTitleChanges(){
        driver.get("https://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        submitAndWait();

        assertThat(driver.getTitle(), is("Processed Form Details"));


    }


    @Test
    public void shouldClearTypeCommentSubmitAndCheck(){
        driver.get("https://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        driver.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(3) > td > textarea")).clear();

        submitAndWait();

        WebElement commentP = driver.findElement(By.cssSelector("body > p:nth-child(4)"));

        assertThat(commentP.getText(), is("No Value for comments"));


    }

    @Test
    public void shouldSubmitFormWithRadio1Selected(){
        driver.get("https://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        driver.findElement(By.cssSelector("input[type='radio']:nth-child(2)")).click();

        submitAndWait();

        String radio = driver.findElement(By.cssSelector("#_valueradioval")).getText();
        assertThat(radio, is("rd1"));

    }



    @Test
    public void shouldSubmitFormWithDropDownItemFiveSelected(){
        driver.get("https://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        driver.findElement(By.cssSelector(" td > select > option:nth-child(5)")).click();

        submitAndWait();

        String dropdown = driver.findElement(By.cssSelector("#_valuedropdown")).getText();
        assertThat(dropdown, is("dd5"));
    }



    @Test
    public void shouldSubmitWithAFileAndCheckNameOnOutput() throws URISyntaxException {
        driver.get("https://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));

        File testFile = new File(this.getClass().getResource("/testTextFile.txt").toURI());

        fileInput.sendKeys(testFile.getAbsolutePath());

        submitAndWait();

        assertEquals("testTextFile.txt", driver.findElement(By.id("_valuefilename")).getText());


    }

    @Test
    public void shouldSelectMultipleItems(){
        driver.get("https://www.compendiumdev.co.uk/selenium/basic_html_form.html");


        WebElement multipleSelectElement;

        multipleSelectElement = driver.findElement(By.cssSelector("select[multiple='multiple']"));

        Select multipleSelect = new Select(multipleSelectElement);

        assertTrue(multipleSelect.isMultiple());

        List<WebElement> selectedElements = multipleSelect.getAllSelectedOptions();
        assertEquals(1, selectedElements.size());
        assertEquals("Selection Item 4", selectedElements.get(0).getText().trim());

        multipleSelect.deselectAll();

        selectedElements = multipleSelect.getAllSelectedOptions();
        assertEquals(0, selectedElements.size());

    }

//    @Test
//    public void exampleUsingExpectedConditionsFluent(){
//        driver.get("https://www.compendiumdev.co.uk/selenium/basic_html_form.html");
//
//        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
//                                withTimeout(10, TimeUnit.SECONDS).
//                                pollingEvery(100, TimeUnit.MILLISECONDS).
//                                ignoring(NotFoundException.class);
//
//        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));
//
//        assertEquals("HTML Form Elements", driver.getTitle());
//    }

    @Test
    public void visitSearchPageAndCheckNoLastSearchCookie(){
        driver.get("http://compendiumdev.co.uk/selenium/search.php");

        System.out.println(driver.manage().getCookies());
        driver.manage().deleteAllCookies();

        driver.navigate().refresh();

        Cookie aCookie = driver.manage().getCookieNamed("SeleniumSimplifiedLastSearch");

        assertEquals(null, aCookie);
    }

    @After
    public void closeWindow(){
        driver.close();
    }
}
