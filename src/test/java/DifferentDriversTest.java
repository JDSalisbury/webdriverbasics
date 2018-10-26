
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DifferentDriversTest {

    @Test
    public void shouldOpenWithChrome(){
        WebDriver chromeDriver = new ChromeDriver();

        chromeDriver.get("http://www.google.com");

        chromeDriver.quit();
    }

    @Test
    public void shouldOpenWithFireFox(){
        WebDriver foxDriver = new FirefoxDriver();

        foxDriver.get("http://www.google.com");

        foxDriver.quit();
    }

//    @Test
//    public void shouldOpenWithIE(){
//        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//        caps.setCapability("ignoreZoomSetting", true);
//        WebDriver ieDriver = new InternetExplorerDriver(caps);
//
//        ieDriver.get("http://www.google.com");
//    }

    @Test
    public void shouldOpenWithEdge(){

        WebDriver edgeDriver = new EdgeDriver();

        edgeDriver.get("http://www.google.com");

        edgeDriver.quit();
    }



}


