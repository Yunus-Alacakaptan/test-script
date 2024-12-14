package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.UrlConstants.BASE_URL;
import static utils.UrlConstants.PAYMENT_PAGE;

public class PaymentPage extends BasePage{

    @FindBy(css = "[data-qa-id='billing-you-pay']")
    private WebElement youPayText;

    @FindBy(css = "[data-qa-id='landing-pay-now']")
    private WebElement payNowButton;

    @FindBy(className = "Splash_loadingWrapper__w7l9_")
    private WebElement loadingWrapper;
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

    public void checkout() {
        this.payNowButton.click();
        /*this.waitForClickable();*/
        waitForLoadingToDisappear(splitBillButton);
        //this.splitBillButton.click();
        System.out.println("splitbillbutton passed");
        this.customAmountButton.click();
    }

    public void payment(String amount, String cardNumber, int expiry, int cvv) {
        this.inputAmount.sendKeys(amount);
        this.confirmButton.click();
        waitForLoadingToDisappear(tipTenButton);
        //this.tipTenButton.click();
        switchFrame(cardNumberFrame);
        this.cardNumberField.sendKeys(cardNumber);
        driver.switchTo().defaultContent();
        switchFrame(expiryDateFrame);
        this.expiryDateField.sendKeys(String.valueOf(expiry));
        driver.switchTo().defaultContent();
        switchFrame(securityNumberFrame);
        this.securityNumberField.sendKeys(String.valueOf(cvv));
        driver.switchTo().defaultContent();
        this.confirmPaymentButton.click();
    }
    private void waitForLoadingToDisappear(WebElement elementToClick) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("waiting passed");
        /*wait.until(ExpectedConditions.visibilityOfElementLocated(loadingWrapper));*/
        System.out.println("visibility passed");

        for (int i = 0; i < 10; i++) {
            try {
                elementToClick.click();
                break; // Exit the loop if click is successful
            } catch (Exception e) {
                // Handle other exceptions
                System.out.println("Retrying due to: " + e.getMessage());
                try {
                    Thread.sleep(500); // Sleep before retrying
                } catch (InterruptedException ie) {
                    // Handle interruption during sleep
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    System.out.println("Thread was interrupted, exiting loop");
                    break; // Exit the loop on interruption
                }
            }
        }
    }
    private void waitForClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(splitBillButton));
    }
    private void switchFrame(WebElement iFrame) {
        driver.switchTo().frame(iFrame);
    }
    }

    /*public void checkValidUserRegistration(String name, String password) {
        // check that the current Url changed after submitting the registration form
        Assert.assertTrue(driver.getCurrentUrl().contains(UrlConstants.SAVE_USER_PAGE));

        Assert.assertTrue(mainContentContainer.getText().contains(name));
        Assert.assertTrue(mainContentContainer.getText().contains(password));
    }*/
