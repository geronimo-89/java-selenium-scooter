package pageobject.old;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.OrderPage;

import java.util.ArrayList;

public class HomePageOld {

    public HomePageOld(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;
    private JavascriptExecutor js;
    public static final String HOME_PAGE_LINK = "https://qa-scooter.praktikum-services.ru/";
    public static final String YANDEX_LINK = "https://yandex.ru/";
    private final By homePage = By.className("Home_HomePage__ZXKIX"); //Главная страница
    private final By yandexButton = By.className("Header_LogoYandex__3TSOI"); //Кнопка Яндекса

    //Cookies
    private final By acceptCookiesButton = By.className("App_CookieButton__3cvqF"); //"да все привыкли" = кнопка принятия Кукиз

    //Кнопки заказа
    private final By orderButtonUpper = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']"); //кнопка Заказать вверху страницы
    private final By orderButtonLower = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']"); //кнопка Заказать внизу страницы

    //Раздел Вопросы о важном
    private final By question0 = By.id("accordion__heading-0"); // Вопрос 1: Сколько это стоит? И как оплатить?
    private final By answer0 = By.id("accordion__panel-0"); //ответ на вопрос 1
    private final By question1 = By.id("accordion__heading-1"); // Вопрос 2: Хочу сразу несколько самокатов! Так можно?
    private final By answer1 = By.id("accordion__panel-1"); //ответ на вопрос 2
    private final By question2 = By.id("accordion__heading-2"); // Вопрос 3: Как рассчитывается время аренды?
    private final By answer2 = By.id("accordion__panel-2"); //ответ на вопрос 3
    private final By question3 = By.id("accordion__heading-3"); // Вопрос 4: Можно ли заказать самокат прямо на сегодня?
    private final By answer3 = By.id("accordion__panel-3"); //ответ на вопрос 4
    private final By question4 = By.id("accordion__heading-4"); // Вопрос 5: Можно ли продлить заказ или вернуть самокат раньше?
    private final By answer4 = By.id("accordion__panel-4"); //ответ на вопрос 5
    private final By question5 = By.id("accordion__heading-5"); // Вопрос 6: Вы привозите зарядку вместе с самокатом?
    private final By answer5 = By.id("accordion__panel-5"); //ответ на вопрос 6
    private final By question6 = By.id("accordion__heading-6"); // Вопрос 7: Можно ли отменить заказ?
    private final By answer6 = By.id("accordion__panel-6"); //ответ на вопрос 7
    private final By question7 = By.id("accordion__heading-7"); // Вопрос 8: Я живу за МКАДом, привезёте?
    private final By answer7 = By.id("accordion__panel-7"); //ответ на вопрос 8
    private By[] questions = {question0, question1, question2, question3, question4, question5, question6, question7};
    private By[] answers = {answer0, answer1, answer2, answer3, answer4, answer5, answer6, answer7};

    //Найти ответ на вопрос с нужным номером и промотать до него
    public HomePageOld scrollToQuestion(int number) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(questions[number]));
        return this;
    }

    //Принять куки
    public HomePageOld acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
        return this;
    }

    //Получить ответ на вопрос с выбранным номером со страницы
    public String getAnswer(int number) throws Exception {
        if (number < 0 || number > (answers.length - 1)) {
            throw new Exception("Question number must be between 0 and 7");
        }
        driver.findElement(questions[number]).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answers[number]));
        String answer = driver.findElement(answers[number]).getText();
        return answer;
    }

    //Кликаем кнопку Заказать вверху страницы
    public OrderPage orderMethod1() {
        driver.findElement(orderButtonUpper).click();
        return new OrderPage(driver);
    }

    //Кликаем кнопку Заказать внизу страницы
    public OrderPage orderMethod2() {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonLower));
        driver.findElement(orderButtonLower).click();
        return new OrderPage(driver);
    }

    //Ожидание загрузки главной страницы
    public HomePageOld waitForHomePage() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(homePage));
        return this;
    }

    //Показать текущий URL страницы
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    //Кликнуть на логотип Яндекса
    public HomePageOld goToYandex() {
        js = (JavascriptExecutor) driver;
        driver.findElement(yandexButton).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(YANDEX_LINK));
        return this;
    }
}