package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.UrlConstants.BASE_URL;
import static utils.UrlConstants.PAYMENT_PAGE;

public class PaymentPage extends BasePage{

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

    @FindBy(id = "tip-10")
    private WebElement tipTenButton;

    @FindBy(id = "checkout-frames-card-number")
    private WebElement cardNumberField;

    @FindBy(id = "expiryDate")
    private WebElement expiryDateField;

    @FindBy(id = "cvv")
    private WebElement securityNumberField;

    @FindBy(id = "checkout-action-btn")
    private WebElement confirmPaymentButton;

    public PaymentPage open() {
        driver.get(BASE_URL + PAYMENT_PAGE);
        return this;
    }

    public void checkout() {
        this.payNowButton.click();
        waitForLoadingToDisappear();
        System.out.println("waitForLoadingToDisappear passed");
        this.splitBillButton.click();
        System.out.println("splitbillbutton passed");
        this.customAmountButton.click();
    }

    public void payment(String amount, String cardNumber, int expiry, int cvv) {
        this.inputAmount.sendKeys(amount);
        this.confirmButton.click();
        this.tipTenButton.click();
        this.cardNumberField.sendKeys(cardNumber);
        this.expiryDateField.sendKeys(String.valueOf(expiry));
        this.securityNumberField.sendKeys(String.valueOf(cvv));
        this.confirmPaymentButton.click();
    }
    private void waitForLoadingToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("waiting passed");
        wait.until(ExpectedConditions.visibilityOf(loadingWrapper));
        System.out.println("visibility passed");
        wait.until(ExpectedConditions.invisibilityOf(loadingWrapper));
        System.out.println("invisibility passed");
    }
    }

    /*public void checkValidUserRegistration(String name, String password) {
        // check that the current Url changed after submitting the registration form
        Assert.assertTrue(driver.getCurrentUrl().contains(UrlConstants.SAVE_USER_PAGE));

        Assert.assertTrue(mainContentContainer.getText().contains(name));
        Assert.assertTrue(mainContentContainer.getText().contains(password));
    }*/
