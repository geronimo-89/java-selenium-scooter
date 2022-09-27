package setup;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePage;
import pageobject.OrderPage;
import pageobject.TrackPage;

import java.util.concurrent.TimeUnit;

public class SetUpDriver {
    protected WebDriver driver;
    protected HomePage homePage;
    protected OrderPage orderPage;
    protected TrackPage trackpage;
    public static final String HOME_PAGE_LINK = "https://qa-scooter.praktikum-services.ru/";

    public void firefoxDriverSetUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void chromeDriverSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void openHomePage() {
        driver.get(HOME_PAGE_LINK);
    }

    public void setUpObjects() {
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        trackpage = new TrackPage(driver);
    }

    public void acceptCookies() {
        homePage.acceptCookies();
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

}
