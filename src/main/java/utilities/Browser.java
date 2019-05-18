package utilities;

import org.openqa.selenium.WebDriver;
import webdriver.WebDriverFactory;

import java.util.concurrent.TimeUnit;

/*
 * Bean representing a browser. It contains name, version and platform fields.
 */
public class Browser {

    public static String baseUrl = PropertyLoader.loadProperty("site.url");
    public static String BrowserName = PropertyLoader.loadProperty("browser.name");
//    private static String BrowserVersion = PropertyLoader.loadProperty("browser.version");
    public static WebDriver driver;

    public static void Initialize()
    {
    	driver = WebDriverFactory.getInstance(BrowserName);
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
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
