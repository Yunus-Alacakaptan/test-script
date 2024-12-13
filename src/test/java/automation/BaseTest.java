package automation;

import driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import pages.BasePage;
import pages.BasePageFactory;
import pages.PaymentPage;

public abstract class BaseTest {
    private static final Logger LOG = LogManager.getLogger(BaseTest.class);

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

    /*@AfterMethod
    public void assessTestStatus(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Allure.addAttachment("Step screenshot: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } else if (result.getStatus() == ITestResult.SKIP) {
            Allure.addAttachment("Step screenshot: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            // do nothing
        }
    }*//**/

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
