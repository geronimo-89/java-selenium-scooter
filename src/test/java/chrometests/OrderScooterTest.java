package chrometests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.SetUpChrome;

import static org.junit.Assert.assertTrue;
import static pageobject.OrderPage.*;

@RunWith(Parameterized.class)
public class OrderScooterTest extends SetUpChrome {

    private static final String EXPECTED_ORDER_CONFIRM_TEXT = "Заказ оформлен";
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String metroStation;

    public OrderScooterTest(String name, String surname, String address, String metroStation, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
    }

    @Parameterized.Parameters
    public static Object[][] answersData() {
        return new Object[][]{
                {"АКСЁНА", "КАШТАНОВА", "г. Москва, ул. Тверская 99", STATION_MAYAKOVSKAYA, "+74956667788"},
                {"Сергей Михалыч", "Христорождественский", "123456 г. Химки, ул. Дружбы, 78 кв. 90, подъезд 1", STATION_BULVAR_ROKOSSOVSKOGO, "9999999999999"},
                {"Ян", "Ли", "Тверь", STATION_LIKHOBORY, "11111111111"},
                {"Василиса", "Кошкина-Мышкина", "Москва, Дом-яйцо", STATION_KITAY_GOROD, "98765432101"}, //Кейс позитивный, составные фамилии существуют, но он фейлится, т.к. в форме баг - не принимает символ дефиса
        };
    }


    @Test
    public void orderScooterCase1() {
        assertTrue((
                homePage
                        .makeOrderFromUpperButton()
                        .waitForOrderPage()
                        .inputName(name)
                        .inputSurname(surname)
                        .inputAddress(address)
                        .selectMetroStation(metroStation)
                        .inputPhoneNumber(phoneNumber)
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
                        .makeOrderFromLowerButton()
                        .waitForOrderPage()
                        .inputName(name)
                        .inputSurname(surname)
                        .inputAddress(address)
                        .inputMetroStation(metroStation)
                        .inputPhoneNumber(phoneNumber)
                        .clickNext()
                        .selectDeliveryDateLastDayOfNextMonth()
                        .selectRentDaysMax()
                        .addComment("Съешь ещё этих мягких французских булок, да выпей же чаю.")
                        .placeOrder()
                        .getOrderConfirmation()
        )
                .contains(EXPECTED_ORDER_CONFIRM_TEXT));

    }
}
