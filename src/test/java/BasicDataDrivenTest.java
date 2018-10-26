import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class BasicDataDrivenTest {

    public static String url = "http:www.compendiumdev.co.uk/selenium/calculate.php";
    public static WebDriver driver = new ChromeDriver();

    private String number1;
    private String function;
    private String number2;
    private String answer;



    public BasicDataDrivenTest(String number1, String function, String number2, String answer){

        this.number1 = number1;
        this.function = function;
        this.number2 = number2;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{{"1", "plus", "1", "2"},{"2", "times", "3", "6"},{"5", "divide", "2", "2.5"},{"10", "minus", "4", "6"} });
    }


    @Test
    public void shouldCalculateTwoValues(){
        driver.get(url);

        WebElement num1 = driver.findElement(By.id("number1"));
        num1.sendKeys(this.number1);

        WebElement num2 = driver.findElement(By.id("number2"));
        num2.sendKeys(this.number2);

        WebElement functionList = driver.findElement(By.id("function"));
        functionList.findElement(By.cssSelector("option[value='" + this.function + "']")).click();

        WebElement calculateButton = driver.findElement(By.id("calculate"));
        calculateButton.click();

        WebElement answer = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("answer")));

        assertThat(answer.getText(), is(equalTo(this.answer)));

    }

}
