package setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePage;
import pageobject.OrderPage;

public class SetUpDriver {
    private WebDriver driver;
    private HomePage homePage;
    private OrderPage orderPage;
    private String homePagelink = "https://qa-scooter.praktikum-services.ru/";


    public void setUpObjects() {
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        driver.get(homePagelink);
        driver.manage().window().maximize();
        homePage.acceptCookies();
    }
    public void firefoxSetUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    public void chromeSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public SetUpDriver(WebDriver driver, HomePage homePage, OrderPage orderPage) {
        this.driver = driver;
        this.homePage = homePage;
        this.orderPage = orderPage;
    }
}
