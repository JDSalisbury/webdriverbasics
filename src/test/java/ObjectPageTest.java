import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ObjectPageTest {

    public WebDriver driver = new ChromeDriver();
    public BasicAjaxPageObject basicAjaxPage = new BasicAjaxPageObject(driver);
    public ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);

    @Test
    public void shouldStartBasicAjaxPageObject(){
        basicAjaxPage.getURL();
        basicAjaxPage.selectCategory(3);
        new WebDriverWait(driver, 10).until(basicAjaxPage.ajaxActionIsComplete());
        basicAjaxPage.selectLanguage(23);
        basicAjaxPage.clickSubmit();
        processedFormPage.waitUntilPageIsLoaded();


        assertThat( processedFormPage.getValueFor("language_id"), is("23"));
        driver.close();
    }
}
