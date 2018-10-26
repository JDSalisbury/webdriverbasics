import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcessedFormPage{

    private WebDriver driver;



    public ProcessedFormPage(WebDriver aDriver) {
        driver = aDriver;
    }

    public void waitUntilPageIsLoaded() {
        new WebDriverWait(driver, 40).until(ExpectedConditions.titleIs("Processed Form Details"));
    }


    public String getValueFor(String valueID) {
       return driver.findElement(By.cssSelector("#_value"+ valueID)).getText();
    }
}
