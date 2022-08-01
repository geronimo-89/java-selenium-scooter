package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private WebDriver driver;
    private JavascriptExecutor js;
    private Actions actions;

    private By faq = By.className("Home_FAQ__3uVm4"); //Раздел Вопросы о важном
    private By question0 = By.id("accordion__heading-0"); // Вопрос 1: Сколько это стоит? И как оплатить?
    private By answer0 = By.id("accordion__panel-0"); //ответ на вопрос 1
    private By question1 = By.id("accordion__heading-1"); // Вопрос 2: Хочу сразу несколько самокатов! Так можно?
    private By answer1 = By.id("accordion__panel-1"); //ответ на вопрос 2
    private By question2 = By.id("accordion__heading-2"); // Вопрос 3: Как рассчитывается время аренды?
    private By answer2 = By.id("accordion__panel-2"); //ответ на вопрос 3
    private By question3 = By.id("accordion__heading-3"); // Вопрос 4: Можно ли заказать самокат прямо на сегодня?
    private By answer3 = By.id("accordion__panel-3"); //ответ на вопрос 4
    private By question4 = By.id("accordion__heading-4"); // Вопрос 5: Можно ли продлить заказ или вернуть самокат раньше?
    private By answer4 = By.id("accordion__panel-4"); //ответ на вопрос 5
    private By question5 = By.id("accordion__heading-5"); // Вопрос 6: Вы привозите зарядку вместе с самокатом?
    private By answer5 = By.id("accordion__panel-5"); //ответ на вопрос 6
    private By question6 = By.id("accordion__heading-6"); // Вопрос 7: Можно ли отменить заказ?
    private By answer6 = By.id("accordion__panel-6"); //ответ на вопрос 7
    private By question7 = By.id("accordion__heading-7"); // Вопрос 8: Я живу за МКАДом, привезёте?
    private By answer7 = By.id("accordion__panel-7"); //ответ на вопрос 8
    private By[] questions = {question0, question1, question2, question3, question4, question5, question6, question7};
    private By[] answers = {answer0, answer1, answer2, answer3, answer4, answer5, answer6, answer7};
    private By acceptCookiesButton = By.className("App_CookieButton__3cvqF"); //"да все привыкли" = кнопка принятия Кукиз


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToQuestions() {
        actions = new Actions (driver);
        WebElement faqList = driver.findElement(faq);
        actions.moveToElement(faqList);
        actions.perform();
    }
    public void acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
    }

    public String getQuestionAnswer(int number) throws Exception
    {
        if (number < 0 || number > (answers.length - 1)) {
            throw new Exception("Question numbers must be between 0 and 7");
        }
        driver.findElement(questions[number]).click();
        String answer = driver.findElement(answers[number]).getText();
        return answer;
    }


}
