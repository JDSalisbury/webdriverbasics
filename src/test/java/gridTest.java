import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class gridTest {

    public static WebDriver driver;

//    @BeforeClass
//    public static void connectToGrid(){
//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        cap.setCapability("platform", Platform.WINDOWS);
//
//        try {
//            driver = new RemoteWebDriver(
//                    new URL("http://192.168.11.114:4444/wd/hub"),
//                    cap);
//        } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//
//    }
//
//    @Test
//    public void simpleInteraction() {
//        driver.get("http://www.google.com");
//
//        assertThat(driver.getTitle(), is("Google"));
//    }
}
