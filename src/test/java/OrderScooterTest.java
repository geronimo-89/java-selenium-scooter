import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePage;
import pageobject.OrderPage;

import static org.junit.Assert.*;

public class OrderScooterTest {

    private WebDriver chromeDriver;
    private HomePage chromeHomePage;
    private OrderPage chromeOrderPage;
    private String expectedOrderConfirmText = "Заказ оформлен";

    @Before
    public void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeHomePage = new HomePage(chromeDriver);
        chromeOrderPage = new OrderPage(chromeDriver);
        chromeDriver.get("https://qa-scooter.praktikum-services.ru/");
        chromeDriver.manage().window().maximize();
        chromeHomePage.acceptCookies();
    }

    @Test
    public void makeOrder1() {
        chromeHomePage.orderMethod1();
        chromeOrderPage.waitForOrderDetailsPage1();
        chromeOrderPage.inputNameSurname("Александр", "Пушкин");
        chromeOrderPage.inputAddress("г. Москва ул. Тверская 99");
        chromeOrderPage.inputMetroStation("Маяковская");
        chromeOrderPage.inputPhoneNumber("89162223344");
        chromeOrderPage.clickNext();
        chromeOrderPage.waitForOrderDetailsPage2();
        chromeOrderPage.selectDeliveryDateTomorrow();
        chromeOrderPage.selectRentDaysMin();
        chromeOrderPage.selectBlackColor();
        chromeOrderPage.selectGreyColor();
        chromeOrderPage.makeOrder();
        chromeOrderPage.confirmOrder();
        chromeOrderPage.waitForOrderConfirm();
        String actualOrderConfirmText = chromeOrderPage.getOrderConfirmation();
        assertTrue(actualOrderConfirmText.contains(expectedOrderConfirmText));
    }

    @Test
    public void makeOrder2() {
        chromeHomePage.orderMethod2();
        chromeOrderPage.waitForOrderDetailsPage1();
        chromeOrderPage.inputNameSurname("Вениамин", "Череззаборногузадерищенко");
        chromeOrderPage.inputAddress("город Химки, улица Дружбы, дом 1");
        chromeOrderPage.inputMetroStation("Планерная");
        chromeOrderPage.inputPhoneNumber("+79035556699");
        chromeOrderPage.clickNext();
        chromeOrderPage.waitForOrderDetailsPage2();
        chromeOrderPage.selectDeliveryDateLastDayOfNextMonth();
        chromeOrderPage.selectRentDaysMax();
        chromeOrderPage.addComment("Позвоните за час до доставки");
        chromeOrderPage.makeOrder();
        chromeOrderPage.confirmOrder();
        chromeOrderPage.waitForOrderConfirm();
        String actualOrderConfirmText = chromeOrderPage.getOrderConfirmation();
        assertTrue(actualOrderConfirmText.contains(expectedOrderConfirmText));

    }

    @After
    public void tearDown() {
        chromeDriver.quit();
    }

}
