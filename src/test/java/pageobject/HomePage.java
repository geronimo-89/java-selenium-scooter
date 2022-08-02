package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.How.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public HomePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    //Cookies
    @FindBy(how = CLASS_NAME, using = "App_CookieButton__3cvqF")
    private WebElement acceptCookiesButton; //"да все привыкли" = кнопка принятия Кукиз

    private WebDriver driver;
    private JavascriptExecutor js;
    public static final String YANDEX_LINK = "https://yandex.ru/";

    @FindBy(how = CLASS_NAME, using = "Home_HomePage__ZXKIX")
    private WebElement homePage; //главная страница
    @FindBy(how = CLASS_NAME, using = "Header_LogoYandex__3TSOI")
    private WebElement yandexButton; //Кнопка Яндекса

    //Кнопки заказа
    @FindBy(how = XPATH, using = ".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']")
    private WebElement orderButtonUpper; //кнопка Заказать вверху страницы
    @FindBy(how = XPATH, using = ".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']")
    private WebElement orderButtonLower; //кнопка Заказать внизу страницы

    //Раздел Вопросы о важном
    @FindAll({@FindBy(how = XPATH, using = ".//div[@data-accordion-component='AccordionItemHeading']")})
    private List<WebElement> questionsList; //список вопросов

    @FindAll({@FindBy(how = XPATH, using = ".//div[@data-accordion-component='AccordionItemPanel']")})
    private List<WebElement> answersList; //список ответов


    //Найти ответ на вопрос с нужным номером и промотать до него
    public HomePage scrollToQuestion(int number) {
        js.executeScript("arguments[0].scrollIntoView();", questionsList.get(number));
        return this;
    }

    //Принять куки
    public HomePage acceptCookies() {
        acceptCookiesButton.click();
        return this;
    }

    //Получить ответ на вопрос с выбранным номером со страницы
    public String getAnswer(int number) throws Exception {
        if (number < 0 || number > (questionsList.size() - 1)) {
            throw new Exception("Question number must be between 0 and 7");
        }
        questionsList.get(number).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(answersList.get(number)));
        String answer = answersList.get(number).getText();
        return answer;
    }

    //Кликаем кнопку Заказать вверху страницы
    public OrderPage orderMethod1() {
        orderButtonUpper.click();
        return new OrderPage(driver);
    }

    //Кликаем кнопку Заказать внизу страницы
    public OrderPage orderMethod2() {
        js.executeScript("arguments[0].scrollIntoView();", orderButtonLower);
        orderButtonLower.click();
        return new OrderPage(driver);
    }

    //Ожидание загрузки главной страницы
    public HomePage waitForHomePage() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(homePage));
        return this;
    }

    //Показать текущий URL страницы
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    //Кликнуть на логотип Яндекса
    public HomePage goToYandex() {
        yandexButton.click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(YANDEX_LINK));
        return this;
    }
}
