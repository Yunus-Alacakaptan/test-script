package automation;

import org.testng.annotations.Test;

public class PaymentTest extends BaseTest{

    @Override
    public void init() {
    }

    @Test(description = "Test payment with credit card", priority = 1)
    public void testPaymentWithCreditCard() {
        paymentPage.open();
        paymentPage.checkout();
        paymentPage.payment("55", "4242 4242 4242 4242", 1226, 123);
    }
}
