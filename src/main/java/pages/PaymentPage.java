package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static utils.UrlConstants.BASE_URL;
import static utils.UrlConstants.PAYMENT_PAGE;

public class PaymentPage extends BasePage {

    @FindBy(css = "[data-qa-id='landing-pay-now']")
    private WebElement payNowButton;
    @FindBy(css = "[data-qa-id='billing-split-bill']")
    private WebElement splitBillButton;

    @FindBy(id = "select-custom")
    private WebElement customAmountButton;

    @FindBy(id = "fullWidth")
    private WebElement inputAmount;

    @FindBy(id = "split-bill")
    private WebElement confirmButton;

    @FindBy(id = "tip_10")
    private WebElement tipTenButton;

    @FindBy(id = "cardNumber")
    private WebElement cardNumberFrame;

    @FindBy(id = "checkout-frames-card-number")
    private WebElement cardNumberField;

    @FindBy(id = "expiryDate")
    private WebElement expiryDateFrame;

    @FindBy(id = "checkout-frames-expiry-date")
    private WebElement expiryDateField;

    @FindBy(id = "cvv")
    private WebElement securityNumberFrame;

    @FindBy(id = "checkout-frames-cvv")
    private WebElement securityNumberField;

    @FindBy(id = "checkout-action-btn")
    private WebElement confirmPaymentButton;

    public PaymentPage open() {
        driver.get(BASE_URL + PAYMENT_PAGE);
        return this;
    }

    public void checkout(String amount) {
        clickOn(payNowButton);

        clickAfterLoaded(splitBillButton);
        clickOn(customAmountButton);
        sendKeysTo(inputAmount, amount);
        clickOn(confirmButton);

        clickAfterLoaded(tipTenButton);
    }

    public void payment(String cardNumber, String expiry, String cvv) {

        switchFrame(cardNumberFrame);
        sendKeysTo(cardNumberField, cardNumber);

        switchFrame(expiryDateFrame);
        sendKeysTo(expiryDateField, expiry);

        switchFrame(securityNumberFrame);
        sendKeysTo(securityNumberField, cvv);

        switchToMainFrame();
        clickOn(confirmPaymentButton);
        checkValidPayment();
    }


    private void clickOn(WebElement element) {
        element.click();
    }
    private void sendKeysTo(WebElement inputField, String keysToSend) {
        inputField.sendKeys(keysToSend);
    }


    private void clickAfterLoaded(WebElement elementToClick) {
        for (int i = 0; i < 10; i++) {
            try {
                elementToClick.click();
                break;
            } catch (Exception e) {
                System.out.println("Retrying due to: " + e.getMessage());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.out.println("Click attempt interrupted due to:  " + ie);
                    break;
                }
            }
        }
    }


    private void switchFrame(WebElement iFrame) {
        switchToMainFrame();
        driver.switchTo().frame(iFrame);
    }
    private void switchToMainFrame() {
        driver.switchTo().defaultContent();
    }


    private void checkValidPayment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(driver -> driver.getPageSource().contains("Payment was successful"));
        } catch (Exception e) {
            Assert.fail("Payment validation failed: " + e.getMessage());
        }
    }
}
