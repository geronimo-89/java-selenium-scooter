package pageobject.old;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageOld {

    public OrderPageOld(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;
    private JavascriptExecutor js;

    //Переход на главную
    private final By homePageButton = By.className("Header_LogoScooter__3lsAR");

    //Для кого самокат
    private final By inputFirstName = By.xpath(".//input[contains(@placeholder, 'Имя')]"); //Имя
    private final By inputSurname = By.xpath(".//input[contains(@placeholder, 'Фамилия')]"); //Фамилия
    private final By inputAddress = By.xpath(".//input[contains(@placeholder, 'Адрес')]"); //Адрес
    private final By inputMetroStation = By.xpath(".//input[contains(@placeholder, 'Станция метро')]"); //Станция метро
    private final By inputPhoneNumber = By.xpath(".//input[contains(@placeholder, 'Телефон')]"); //Телефон
    private final By nextButton = By.xpath(".//button[text()='Далее']"); //кнопка Далее

    //Про аренду
    private final By inputDeliveryDate = By.xpath(".//input[contains(@placeholder, 'Когда привезти')]"); //Когда привезти самокат
    private final By inputRentDays = By.xpath(".//div[contains(text(), 'Срок аренды')]"); //Срок аренды
    private final By blackColor = By.id("black"); //Цвет самоката - чёрный жемчуг (выбор цвета необязателен)
    private final By greyColor = By.id("grey"); //Цвет самоката - серая безысходность (выбор цвета необязателен)
    private final By inputComment = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]"); //Комментарий для курьера - необязательное поле
    private final By finishOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']"); // Кнопка Заказать

    //Подтверждение заказа
    private final By confirmOrderPopup = By.className("Order_Modal__YZ-d3"); //Хотите оформить заказ?
    private final By confirmOrderButton = By.xpath(".//button[text()='Да']"); //Да
    private final By orderConfirmedPopup = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    //Календарь
    private final By datePickerDisplay = By.className("react-datepicker__header"); //Поп-ап с календарем
    private final By selectNextMonth = By.xpath(".//div[@class='react-datepicker']/button[text()='Next Month']"); //Выбрать следующий месяц
    private final By tomorrow = By.xpath(".//div[contains(@class, 'react-datepicker__day')][preceding::div[contains(@class, 'react-datepicker__day--today')]]"); //Доставка на завтра
    private final By lastDayOfTheMonth = By.xpath(".//div[@class='react-datepicker__week'][last()]/div[contains(@class, 'react-datepicker__day--0') and not (contains(@class, 'react-datepicker__day--outside-month'))][last()]"); //Доставка на последний день выбранного месяца

    //Срок аренды
    private final By rentDaysDisplay = By.className("Dropdown-menu"); //Поп-ап с выбором количества дней аренды
    private final By rentDaysMin = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");
    private final By rentDaysMax = By.xpath(".//div[@class='Dropdown-option' and text()='семеро суток']");

    //Метро - список станций получается при вводе <setTimeout(function() {debugger;}, 5000);> в консоли
    private final By stationListDisplay = By.className("select-search__select"); //Поп-ап списка станций

    //Получить xpath станции по названию
    public String getXpathByStationName(String name) {
        String xpath1 = ".//div[@class='select-search__select']/ul/li[@class='select-search__row']/button/div[text()='";
        String xpath2 = name;
        String xpath3 = "']";
        String fullXpath = xpath1 + xpath2 + xpath3;
        return fullXpath;
    }

    //Ожидание загрузки страницы Для кого самокат
    public OrderPageOld waitForOrderDetailsPage1() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(inputFirstName));
        return this;
    }

    //Ввод имени и фамилии
    public OrderPageOld inputNameSurname(String name, String surname) {
        driver.findElement(inputFirstName).sendKeys(name);
        driver.findElement(inputSurname).sendKeys(surname);
        return this;
    }

    //Ввод адреса
    public OrderPageOld inputAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
        return this;
    }

    //Выбор станции метро через ввод
    public OrderPageOld inputMetroStation(String stationName) {
        driver.findElement(inputMetroStation).click();
        driver.findElement(inputMetroStation).sendKeys(stationName);
        driver.findElement(inputMetroStation).sendKeys(Keys.DOWN);
        driver.findElement(inputMetroStation).sendKeys(Keys.RETURN);
        return this;
    }

    //Выбор станции метро через скролл
    public OrderPageOld selectMetroStation(String name) {
        driver.findElement(inputMetroStation).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(stationListDisplay));
        By station = By.xpath(getXpathByStationName(name));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(station));
        driver.findElement(station).click();
        return this;
    }

    //Ввод номера телефона
    public OrderPageOld inputPhoneNumber(String phoneNumber) {
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
        return this;
    }

    //Переход к странице Про аренду
    public OrderPageOld clickNext() {
        driver.findElement(nextButton).click();
        return this;
    }

    //Ожидание загрузки страницы Про аренду
    public OrderPageOld waitForOrderDetailsPage2() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(inputDeliveryDate));
        return this;
    }

    //Выбор доставки на завтра
    public OrderPageOld selectDeliveryDateTomorrow() {
        driver.findElement(inputDeliveryDate).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(datePickerDisplay));
        driver.findElement(tomorrow).click();
        return this;
    }

    //Выбор доставки на последний день следующего месяца
    public OrderPageOld selectDeliveryDateLastDayOfNextMonth() {
        driver.findElement(inputDeliveryDate).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(selectNextMonth));
        driver.findElement(selectNextMonth).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(lastDayOfTheMonth));
        driver.findElement(lastDayOfTheMonth).click();
        return this;
    }

    //Выбор минимального срока аренды самоката
    public OrderPageOld selectRentDaysMin() {
        driver.findElement(inputRentDays).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(rentDaysDisplay));
        driver.findElement(rentDaysMin).click();
        return this;
    }

    //Выбор максимального срока аренды самоката
    public OrderPageOld selectRentDaysMax() {
        driver.findElement(inputRentDays).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(rentDaysDisplay));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(rentDaysMax));
        driver.findElement(rentDaysMax).click();
        return this;
    }

    //Выбор цвета самоката: черный - опционально
    public OrderPageOld selectBlackColor() {
        driver.findElement(blackColor).click();
        return this;
    }

    //Выбор цвета самоката: серый - опционально
    public OrderPageOld selectGreyColor() {
        driver.findElement(greyColor).click();
        return this;
    }

    //Добавление комментария - опционально
    public OrderPageOld addComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
        return this;
    }

    //Заказать
    public OrderPageOld placeOrder() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(finishOrderButton));
        driver.findElement(finishOrderButton).click();
        return this;
    }

    //Подтверждение заказа
    public OrderPageOld confirmOrder() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(confirmOrderPopup));
        driver.findElement(confirmOrderButton).click();
        return this;
    }

    //Ожидание окна подтверждения заказа
    public OrderPageOld waitForOrderConfirm() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(orderConfirmedPopup));
        return this;
    }

    //Получение подтверждения заказа
    public String getOrderConfirmation() {
        return driver.findElement(orderConfirmedPopup).getText();
    }

    //Переход к главной странице через логотип Скутера
    public HomePageOld goToHomePage() {
        driver.findElement(homePageButton).click();
        return new HomePageOld(driver);
    }
}
