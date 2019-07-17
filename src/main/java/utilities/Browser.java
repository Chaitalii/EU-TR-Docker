package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import webdriver.WebDriverFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/*
 * Bean representing a browser. It contains name, version and platform fields.
 */
public class Browser {

    public static String baseUrl = PropertyLoader.loadProperty("site.url");
    public static String BrowserName = PropertyLoader.loadProperty("browser.name");
//    private static String BrowserVersion = PropertyLoader.loadProperty("browser.version");
    public static WebDriver driver;

    public static void Initialize() throws MalformedURLException
    {
//    	driver = WebDriverFactory.getInstance(BrowserName);
    	 DesiredCapabilities dc = DesiredCapabilities.chrome();
         driver = new RemoteWebDriver(new URL("http://35.246.142.197:4444/wd/hub"), dc);
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	
//    	http://35.246.142.197:4444/grid/console
        goTo(baseUrl);
    }

    public static String getTitle()
    {
        return driver.getTitle();
    }

    public static WebDriver Driver()
    {
        return driver;
    }

    public static void goTo(String url)
    {
    	driver.get(url);
    }

    public static void close()
    {
    	driver.close();
    }
}
