package chrometests;

import org.junit.Before;
import org.junit.Test;
import setup.SetUpChrome;

import static org.junit.Assert.assertTrue;

public class OrderPageErrorsTest extends SetUpChrome {

    @Before
    public void goToOrderPage() {
        homePage
                .makeOrderFromUpperButton()
                .waitForOrderPage();
    }

    @Test
    public void checkInvalidNameError() {
        assertTrue(
                orderPage
                        .inputName("John")
                        .clickTab()
                        .getInvalidNameError()
        );


    }
}
