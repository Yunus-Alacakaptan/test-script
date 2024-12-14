package pages;

import org.openqa.selenium.WebDriver;

/**
 * This class is used to instantiate page classes.
 */
public class BasePageFactory {
    public static <T extends BasePage> T createInstance(WebDriver driver, Class<T> page) {
        try {
            // Instantiate the page class and initialize it with the driver
            BasePage instance = page.getDeclaredConstructor().newInstance();
            instance.init(driver);

            return page.cast(instance);
        } catch (Exception e) {
            throw new RuntimeException("Page class instantiation failed for: " + page.getSimpleName(), e);
        }
    }
}