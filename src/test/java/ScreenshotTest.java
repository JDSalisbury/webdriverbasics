import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class ScreenshotTest {

    public Random rand = new Random();

    @Test
    public void goToPageScreenshot() throws IOException {
        String num = String.valueOf(rand.nextInt(100)+1);
        String addNum = String.valueOf(rand.nextInt(100)+1 + rand.nextInt(100)+1);
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");

        TakesScreenshot snap = (TakesScreenshot) driver;

        File tempScreenshot = snap.getScreenshotAs(OutputType.FILE);

        System.out.println(tempScreenshot.getAbsolutePath());

        File myScreenshotDirectory = new File("C:\\Users\\jsalisbury\\Pictures\\Screenshots");

        File myScreenshot = new File(myScreenshotDirectory,   addNum + num +"new.png");
        FileUtils.moveFile(tempScreenshot, myScreenshot);

        assertThat(myScreenshot.length(), is(greaterThan(0L)));

        driver.get("file://" + myScreenshot.getAbsolutePath());

        driver.close();
    }
}
