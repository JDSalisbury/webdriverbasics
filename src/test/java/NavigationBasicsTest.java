import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.number.OrderingComparison.greaterThan;



public class NavigationBasicsTest {

    static WebDriver driver = new ChromeDriver();
    final private String PROTOCOL = "http";
    final private String DOMAIN = "www.compendiumdev.co.uk";
    final private URL ROOT_URl = new URL(PROTOCOL + "://" + DOMAIN);

    public NavigationBasicsTest() throws MalformedURLException {
    }


    @Test
    public void navigateWithGet() {
        driver.get(ROOT_URl + "/selenium");
        String title = driver.getTitle();
        assertThat(title.startsWith("Selenium Simplified"), is(true));
        System.out.println("navigateWithGet: " + title);
    }

    @Test
    public void navigateUsingTo() {
        driver.navigate().to(ROOT_URl + "/selenium/search.php");
        String title = driver.getTitle();
        assertThat(title.startsWith("Selenium Simplified Search Engine"), is(true));
        System.out.println("navigateUsingTo: " + title);
    }

    @Test
    public void shouldGoBackAndForward() {
        driver.get(ROOT_URl + "/selenium/basic_html_form.html");
        String title = driver.getTitle();
        assertThat(title.startsWith("HTML Form Elements"), is(true));

        driver.get(ROOT_URl + "/selenium/basic_web_page.html");
        String title2 = driver.getTitle();
        assertThat(title2.startsWith("Basic Web Page Title"), is(true));

        driver.navigate().back();
        assertThat(title.startsWith("HTML Form Elements"), is(true));

        driver.navigate().forward();
        assertThat(title2.startsWith("Basic Web Page Title"), is(true));
    }

    @Test
    public void navigateWithRefresh() {
      driver.get(ROOT_URl + "/selenium/refresh.php");

        final String refreshTitleConstant = "Refreshed Page on ";

        assertThat(driver.getTitle().startsWith(refreshTitleConstant), is(true));

        long startTime = Long.parseLong(driver.getTitle().replaceFirst(refreshTitleConstant, ""));
        System.out.println(startTime);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.navigate().refresh();

        assertThat(driver.getTitle().startsWith(refreshTitleConstant), is(true));

        long endTime = Long.parseLong(driver.getTitle().replaceFirst(refreshTitleConstant, ""));
        System.out.println(endTime);

        assertThat(endTime, greaterThan(startTime));

    }


    @Test
    public void shouldSeeIfPageContainsParagraph(){
        driver.get(ROOT_URl + "/selenium/basic_web_page.html");
        String title = driver.getTitle();
        assertThat(title.startsWith("Basic Web Page Title"), is(true));

        String pageSource = driver.getPageSource();
        System.out.println(pageSource);

        assertThat(pageSource.contains("A paragraph of text"), is(true));
    }

    @Test
    public void shouldGrabAnElementByID(){
        driver.get(ROOT_URl + "/selenium/basic_web_page.html");
        WebElement para1 = driver.findElement(By.id("para1"));
        System.out.println(para1.getText());
        assertThat("A paragraph of text", is(para1.getText()));
    }

    @Test
    public void shouldGrabAnElementByLinkText(){
        driver.get(ROOT_URl + "/selenium/find_by_playground.php");
        WebElement lkTxt = driver.findElement(By.linkText("jump to para 0"));
        String underTestTxt = lkTxt.getText();
        System.out.println(underTestTxt);
        assertThat(underTestTxt, is("jump to para 0"));
    }

    @Test
    public void shouldGrabAnElementByName(){
        driver.get(ROOT_URl + "/selenium/find_by_playground.php");
        WebElement underTestName = driver.findElement(By.name("pName1"));
        String nameTxt = underTestName.getText();
        System.out.println(nameTxt);
        assertThat(nameTxt, is("This is a paragraph text"));
    }

    @Test
    public void shouldGrabAnElementByPartialLinkText(){
        driver.get(ROOT_URl + "/selenium/find_by_playground.php");
        WebElement pLnkTxt = driver.findElement(By.partialLinkText("para 9"));
        String underTestTxt = pLnkTxt.getText();
        System.out.println(underTestTxt);
        assertThat(underTestTxt, is("jump to para 9"));
    }

    @Test
    public void shouldGrabAListOfElements(){
        driver.get(ROOT_URl + "/selenium/find_by_playground.php");
        List<WebElement> elements = driver.findElements(By.className("normal"));
        Set<String> foundTags = new HashSet<String>();

        for (WebElement e: elements){
            foundTags.add(e.getTagName());
        }
        System.out.println(foundTags);
        assertTrue(foundTags.contains("p"));
    }

    @Test
    public void shouldFindElementByIdWithCSSselector(){
        driver.get(ROOT_URl + "/selenium/find_by_playground.php");
        WebElement idElement = driver.findElement(By.cssSelector("pre#pre1.normal"));
        String underTestTxt = idElement.getText();
        System.out.println(underTestTxt);
        assertThat(underTestTxt, is("within div of multiple class styles\n  "));
    }

    @Test
    public void shouldFindElementWithCSSAgain(){
        driver.get(ROOT_URl + "/selenium/find_by_playground.php");
        WebElement idElement = driver.findElement(By.cssSelector("a#a31.normal"));
        String underTestTxt = idElement.getText();
        System.out.println(underTestTxt);
        assertThat(underTestTxt, is("jump to para 5"));
    }

    @Test
    public void shouldFindElementViaXPath(){
        driver.get(ROOT_URl + "/selenium/find_by_playground.php");
        WebElement idElement = driver.findElement(By.xpath("//*[@id='a31']"));
        String underTestTxt = idElement.getText();
        System.out.println(underTestTxt);
        assertThat(underTestTxt, is("jump to para 5"));
    }

    @AfterClass
    public static void quitDriver() {
        System.out.println("Clean Up");
        driver.quit();
    }

}
