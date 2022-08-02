package chrometests;

import org.junit.Test;
import setup.SetUpChrome;

import static org.junit.Assert.assertTrue;

public class OrderScooterTest extends SetUpChrome {

    private static final String EXPECTED_ORDER_CONFIRM_TEXT = "Заказ оформлен";
    private static final String STATION_MAYAKOVSKAYA = "Маяковская";
    private static final String STATION_PLANERNAYA = "Планерная";


    @Test
    public void makeOrder1() {
        assertTrue((
                homePage
                        .orderMethod1()
                        .waitForOrderDetailsPage1()
                        .inputNameSurname("Александр", "Пушкин")
                        .inputAddress("г. Москва ул. Тверская 99")
                        .selectMetroStation(STATION_MAYAKOVSKAYA)
                        .inputPhoneNumber("89162223344")
                        .clickNext()
                        .waitForOrderDetailsPage2()
                        .selectDeliveryDateTomorrow()
                        .selectRentDaysMin()
                        .selectBlackColor()
                        .selectGreyColor()
                        .placeOrder()
                        .confirmOrder()
                        .waitForOrderConfirm()
                        .getOrderConfirmation()
        )
                .contains(EXPECTED_ORDER_CONFIRM_TEXT));
    }

    @Test
    public void makeOrder2() {
        assertTrue((
                homePage
                        .orderMethod2()
                        .waitForOrderDetailsPage1()
                        .inputNameSurname("Вениамин", "Череззаборногузадерищенко")
                        .inputAddress("город Химки, улица Дружбы, дом 1")
                        .inputMetroStation(STATION_PLANERNAYA)
                        .inputPhoneNumber("+79035556699")
                        .clickNext()
                        .waitForOrderDetailsPage2()
                        .selectDeliveryDateLastDayOfNextMonth()
                        .selectRentDaysMax()
                        .addComment("Позвоните за час до доставки")
                        .placeOrder()
                        .confirmOrder()
                        .waitForOrderConfirm()
                        .getOrderConfirmation()
        )
                .contains(EXPECTED_ORDER_CONFIRM_TEXT));

    }
}
