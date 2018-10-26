import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class CsvDataDrivenTest {

    public static String url = "http:www.compendiumdev.co.uk/selenium/calculate.php";
    public static WebDriver driver = new ChromeDriver();

    private String number1;
    private String function;
    private String number2;
    private String answer;

    public CsvDataDrivenTest(String number1, String function, String number2, String answer){

        this.number1 = number1;
        this.function = function;
        this.number2 = number2;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return csvFileAsCollectionOfStringArrays(
                System.getProperty("user.dir") +
                        "/src/test/resources/data_driven.csv");

    }

    private static Collection csvFileAsCollectionOfStringArrays(String csvFileName) {

        List<String[]> csvRows = new ArrayList<String[]>();
        String rawCSVRow;
        BufferedReader csvFileReader=null;
        String delimiter=",";

        System.out.println("Reading data from " + csvFileName);

        try {
            csvFileReader = new BufferedReader(new FileReader(csvFileName));

        } catch (FileNotFoundException e) {
            System.out.println("Could Not find file: " + csvFileName);
            e.printStackTrace();
        }

        int rowNumber=1;

        try {
            while((rawCSVRow=csvFileReader.readLine()) != null){
                String delimitedItems[] = rawCSVRow.split(delimiter);
                csvRows.add(delimitedItems);
                rowNumber++;
            }

        }catch (IOException e){
            System.out.println("Error reading row number: " + rowNumber);
            e.printStackTrace();
        }

        try{
            csvFileReader.close();
        } catch (IOException e){
            System.out.println("Error closing File: " + e.getMessage());
            e.printStackTrace();
        }

        return csvRows;

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
