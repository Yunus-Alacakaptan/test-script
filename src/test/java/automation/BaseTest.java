package automation;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import pages.BasePage;
import pages.BasePageFactory;
import pages.PaymentPage;


public abstract class BaseTest {

    protected PaymentPage paymentPage;

    protected WebDriver driver = Driver.getDriver();

    public abstract void init();

    protected <T extends BasePage> T createInstance(Class<T> page) {
        return BasePageFactory.createInstance(driver, page);
    }

    @BeforeClass
    public void setup() {
        init();

        // instantiate POM pages
        paymentPage = createInstance(PaymentPage.class);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
