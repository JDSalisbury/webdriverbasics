import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class DriverManagerTest {

    public WebDriver driver;

    public void driverManagerGetAndUrlTitleAssert() {
        driver = DriverManager.get();
        driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertThat(driver.getTitle(), is("Basic Web Page Title"));
    }

    @Test
    public void createADefaultDriver(){

        driverManagerGetAndUrlTitleAssert();

        driver.quit();

    }

    @Test
    public void createAFirefoxDriver(){
        System.setProperty(DriverManager.SELENIUM_2_BASICS_DRIVER, "firefox");

        driverManagerGetAndUrlTitleAssert();

        driver.quit();

    }

    ////    Jenkins didnt like edge
//    @Test
//    public void createAEdgeDriver(){
//        System.setProperty(DriverManager.SELENIUM_2_BASICS_DRIVER, "edge");
//
//        driverManagerGetAndUrlTitleAssert();
//
//        driver.quit();
//
//    }


    @After
    public void cleanup(){
        driver.quit();
    }
}
