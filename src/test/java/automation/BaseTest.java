package automation;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import pages.BasePage;
import pages.BasePageFactory;
import pages.PaymentPage;

/**
 * Base class for all Test classes.
 * Includes utility methods to create instances of POM classes.
 */
public abstract class BaseTest {
    protected WebDriver driver = Driver.getDriver();

    protected PaymentPage paymentPage;
    protected <T extends BasePage> T createInstance(Class<T> page) {
        return BasePageFactory.createInstance(driver, page);
    }
    @BeforeClass
    public void setup() {

        // Instantiate POM pages
        paymentPage = createInstance(PaymentPage.class);
    }
    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
