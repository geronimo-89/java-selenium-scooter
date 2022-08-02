package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriver driver;
    private JavascriptExecutor js;


    //Для кого самокат
    private By inputFirstName = By.xpath(".//input[contains(@placeholder, 'Имя')]"); //Имя
    private By inputSurname = By.xpath(".//input[contains(@placeholder, 'Фамилия')]"); //Фамилия
    private By inputAddress = By.xpath(".//input[contains(@placeholder, 'Адрес')]"); //Адрес
    private By inputMetroStation = By.xpath(".//input[contains(@placeholder, 'Станция метро')]"); //Станция метро
    private By inputPhoneNumber = By.xpath(".//input[contains(@placeholder, 'Телефон')]"); //Телефон
    private By nextButton = By.xpath(".//button[text()='Далее']"); //кнопка Далее

    //Про аренду
    private By inputDeliveryDate = By.xpath(".//input[contains(@placeholder, 'Когда привезти')]"); //Когда привезти самокат
    private By inputRentDays = By.xpath(".//div[contains(text(), 'Срок аренды')]"); //Срок аренды
    private By blackColor = By.id("black"); //Цвет самоката - чёрный жемчуг (выбор цвета необязателен)
    private By greyColor = By.id("grey"); //Цвет самоката - серая безысходность (выбор цвета необязателен)
    private By inputComment = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]"); //Комментарий для курьера - необязательное поле
    private By finishOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']"); // Кнопка Заказать

    //Подтверждение заказа
    private By confirmOrderPopup = By.className("Order_Modal__YZ-d3"); //Хотите оформить заказ?
    private By confirmOrderButton = By.xpath(".//button[text()='Да']"); //Да
    private By orderConfirmedPopup = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    //Календарь
    private By datePickerDisplay = By.className("react-datepicker__header"); //Поп-ап с календарем
    private By selectNextMonth = By.xpath(".//div[@class='react-datepicker']/button[text()='Next Month']"); //Выбрать следующий месяц
    private By tomorrow = By.xpath(".//div[contains(@class, 'react-datepicker__day')][preceding::div[contains(@class, 'react-datepicker__day--today')]]"); //Доставка на завтра
    private By lastDayOfTheMonth = By.xpath(".//div[@class='react-datepicker__week'][last()]/div[contains(@class, 'react-datepicker__day--0') and not (contains(@class, 'react-datepicker__day--outside-month'))][last()]"); //Доставка на последний день выбранного месяца

    //Срок аренды
    private By rentDaysDisplay = By.className("Dropdown-menu"); //Поп-ап с выбором количества дней аренды
    private By rentDaysMin = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");
    private By rentDaysMax = By.xpath(".//div[@class='Dropdown-option' and text()='семеро суток']");

    //Метро - список станций получается при вводе <setTimeout(function() {debugger;}, 5000);> в консоли
    private By stationListDisplay = By.className("select-search__select"); //Поп-ап списка станций

    //Получить xpath станции по названию
    public String getXpathByStationName(String name) {
        String xpath1 = ".//div[@class='select-search__select']/ul/li[@class='select-search__row']/button/div[text()='";
        String xpath2 = name;
        String xpath3 = "']";
        String fullXpath = xpath1 + xpath2 + xpath3;
        return fullXpath;
    }

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание загрузки страницы Для кого самокат
    public void waitForOrderDetailsPage1() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(inputFirstName));
    }

    //Ввод имени и фамилии
    public void inputNameSurname(String name, String surname) {
        driver.findElement(inputFirstName).sendKeys(name);
        driver.findElement(inputSurname).sendKeys(surname);
    }

    //Ввод адреса
    public void inputAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    //Выбор станции метро через ввод
    public void inputMetroStation(String stationName) {
        driver.findElement(inputMetroStation).click();
        driver.findElement(inputMetroStation).sendKeys(stationName);
        driver.findElement(inputMetroStation).sendKeys(Keys.DOWN);
        driver.findElement(inputMetroStation).sendKeys(Keys.RETURN);
    }
    //Выбор станции метро через скролл
    public void selectMetroStation(String name) {
        driver.findElement(inputMetroStation).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(stationListDisplay));
        By station = By.xpath(getXpathByStationName(name));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(station));
        driver.findElement(station).click();
    }

    //Ввод номера телефона
    public void inputPhoneNumber(String phoneNumber) {
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }

    //Переход к странице Про аренду
    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    //Ожидание загрузки страницы Про аренду
    public void waitForOrderDetailsPage2() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(inputDeliveryDate));
    }

    //Выбор доставки на завтра
    public void selectDeliveryDateTomorrow() {
        driver.findElement(inputDeliveryDate).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(datePickerDisplay));
        driver.findElement(tomorrow).click();
    }

    //Выбор доставки на последний день следующего месяца
    public void selectDeliveryDateLastDayOfNextMonth() {
        driver.findElement(inputDeliveryDate).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(selectNextMonth));
        driver.findElement(selectNextMonth).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(lastDayOfTheMonth));
        driver.findElement(lastDayOfTheMonth).click();
    }

    //Выбор минимального срока аренды самоката
    public void selectRentDaysMin() {
        driver.findElement(inputRentDays).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(rentDaysDisplay));
        driver.findElement(rentDaysMin).click();
    }

    //Выбор максимального срока аренды самоката
    public void selectRentDaysMax() {
        driver.findElement(inputRentDays).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(rentDaysDisplay));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(rentDaysMax));
        driver.findElement(rentDaysMax).click();
    }

    //Выбор цвета самоката: черный - опционально
    public void selectBlackColor() {
        driver.findElement(blackColor).click();
    }

    //Выбор цвета самоката: серый - опционально
    public void selectGreyColor() {
        driver.findElement(greyColor).click();
    }

    //Добавление комментария - опционально
    public void addComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    //Заказать
    public void makeOrder() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(finishOrderButton));
        driver.findElement(finishOrderButton).click();
    }

    //Подтверждение заказа
    public void confirmOrder() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(confirmOrderPopup));
        driver.findElement(confirmOrderButton).click();
    }

    //Ожидание окна подтверждения заказа
    public void waitForOrderConfirm() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(orderConfirmedPopup));
    }

    //Получение подтверждения заказа
    public String getOrderConfirmation() {
        return driver.findElement(orderConfirmedPopup).getText();
    }

}
