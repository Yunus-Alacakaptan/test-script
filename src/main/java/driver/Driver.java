package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class Driver {

    public static final String REMOTE_WEBDRIVER_URL = "http://192.168.1.217:4444/wd/hub";

    private static final String BROWSER_CONFIG_FILE = "browser.properties";
    private static final int PAGE_TIME_OUT = 30;
    private static WebDriver driver;

    private Driver() {
    }

    private static WebDriver init() {
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");


                driver = new ChromeDriver(chromeOptions);


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PAGE_TIME_OUT));
        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver getDriver() {
        return (driver == null) ? init() : driver;
    }
}
