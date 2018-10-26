import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {


    public static final String SELENIUM_2_BASICS_DRIVER = "selenium2Basics.driver";

    public static WebDriver get() {

        String browserToUse ="";

        if(System.getProperties().containsKey(SELENIUM_2_BASICS_DRIVER)){
            browserToUse = System.getProperty(SELENIUM_2_BASICS_DRIVER);
        }
        switch (browserToUse){
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            default:
                return new ChromeDriver();
        }


    }
}
