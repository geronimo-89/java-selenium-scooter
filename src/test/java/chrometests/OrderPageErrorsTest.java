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

    @Test
    public void checkInvalidSurnameError() {
        assertTrue(
                orderPage
                        .inputSurname("Smith")
                        .clickTab()
                        .getInvalidSurnameError()
        );
    }

    @Test
    public void checkInvalidAddressError() {
        assertTrue(
                orderPage
                        .inputAddress("Planet Mars")
                        .clickTab()
                        .getInvalidAddressError()
        );
    }

    @Test
    public void checkNoMetroStationSelectedError() {
        assertTrue(
                orderPage
                        .clickNext()
                        .getNoMetroStationSelectedError()
        );
    }

    @Test
    public void checkInvalidPhoneNumberError() {
        assertTrue(
                orderPage
                        .inputPhoneNumber("0")
                        .clickTab()
                        .getInvalidPhoneNumberError()
        );
    }
}