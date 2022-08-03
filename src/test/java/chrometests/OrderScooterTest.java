package chrometests;

import org.junit.Test;
import setup.SetUpChrome;

import static org.junit.Assert.assertTrue;

public class OrderScooterTest extends SetUpChrome {

    private static final String EXPECTED_ORDER_CONFIRM_TEXT = "Заказ оформлен";
    private static final String STATION_MAYAKOVSKAYA = "Маяковская";
    private static final String STATION_PLANERNAYA = "Планерная";


    @Test
    public void orderScooterCase1() {
        assertTrue((
                homePage
                        .orderMethod1()
                        .waitForOrderPage()
                        .inputNameSurname("Александр", "Пушкин")
                        .inputAddress("г. Москва ул. Тверская 99")
                        .selectMetroStation(STATION_MAYAKOVSKAYA)
                        .inputPhoneNumber("89162223344")
                        .clickNext()
                        .selectDeliveryDateTomorrow()
                        .selectRentDaysMin()
                        .selectBlackColor()
                        .selectGreyColor()
                        .placeOrder()
                        .getOrderConfirmation()
        )
                .contains(EXPECTED_ORDER_CONFIRM_TEXT));
    }

    @Test
    public void orderScooterCase2() {
        assertTrue((
                homePage
                        .orderMethod2()
                        .waitForOrderPage()
                        .inputNameSurname("Вениамин", "Череззаборногузадерищенко")
                        .inputAddress("город Химки, улица Дружбы, дом 1")
                        .inputMetroStation(STATION_PLANERNAYA)
                        .inputPhoneNumber("+79035556699")
                        .clickNext()
                        .selectDeliveryDateLastDayOfNextMonth()
                        .selectRentDaysMax()
                        .addComment("Позвоните за час до доставки")
                        .placeOrder()
                        .getOrderConfirmation()
        )
                .contains(EXPECTED_ORDER_CONFIRM_TEXT));

    }
}
