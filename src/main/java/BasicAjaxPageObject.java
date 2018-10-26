import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;




public class BasicAjaxPageObject {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "combo1")
    private WebElement categorySelect;

    @FindBy(how = How.ID, using = "combo2")
    private WebElement languageSelect;

    @FindBy(how = How.NAME, using = "submitbutton")
    private WebElement submit;



    public BasicAjaxPageObject(WebDriver aDriver) {
        driver = aDriver;
        PageFactory.initElements(driver, this);
    }


    public void getURL() {
        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    public void selectCategory(int categoryValue) {

        categorySelect.findElement(By.cssSelector("option[value='" + categoryValue +"']")).click();
    }

    public ExpectedCondition<Boolean> ajaxActionIsComplete() {
        return ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxBusy"));
    }

    public void selectLanguage(int languageValue) {
        languageSelect.findElement(By.cssSelector("option[value='" + languageValue + "']")).click();
    }

    public void clickSubmit() {
        submit.click();
    }
}
