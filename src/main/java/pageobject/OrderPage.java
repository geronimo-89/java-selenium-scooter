package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.old.HomePageOld;
import static org.openqa.selenium.support.How.*;

public class OrderPage extends ScooterPages {

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static final String STATION_BULVAR_ROKOSSOVSKOGO = "Бульвар Рокоссовского";
    public static final String STATION_LIKHOBORY = "Лихоборы";
    public static final String STATION_MAYAKOVSKAYA = "Маяковская";

    public static final String STATION_KITAY_GOROD = "Китай-город";

    @FindBy(how = CLASS_NAME, using = "Header_LogoScooter__3lsAR")
    private WebElement homePageButton; //На главную страницу

    @FindBy(how = CLASS_NAME, using = "Order_Content__bmtHS")
    private WebElement orderForm; //Форма заказа

    //Для кого самокат
    @FindBy(how = XPATH, using = ".//input[contains(@placeholder, 'Имя')]")
    private WebElement firstNameInput; //Имя
    @FindBy(how = XPATH, using = ".//input[contains(@placeholder, 'Фамилия')]")
    private WebElement surnameInput; //Фамилия
    @FindBy(how = XPATH, using = ".//input[contains(@placeholder, 'Адрес')]")
    private WebElement addressInput; //Адрес
    @FindBy(how = XPATH, using = ".//input[contains(@placeholder, 'Станция метро')]")
    private WebElement metroStationInput; //Станция метро
    @FindBy(how = XPATH, using = ".//input[contains(@placeholder, 'Телефон')]")
    private WebElement phoneNumberInput; //Телефон
    @FindBy(how = XPATH, using = ".//button[text()='Далее']")
    private WebElement nextButton; //кнопка Далее

    //Про аренду
    @FindBy(how = XPATH, using = ".//input[contains(@placeholder, 'Когда привезти')]")
    private WebElement deliveryDateInput; //Когда привезти самокат
    @FindBy(how = XPATH, using = ".//div[contains(text(), 'Срок аренды')]")
    private WebElement rentDaysInput; //Срок аренды
    @FindBy(how = ID, using = "black")
    private WebElement blackColor; //Цвет самоката - чёрный жемчуг
    @FindBy(how = ID, using = "grey")
    private WebElement greyColor; //Цвет самоката - серая безысходность
    @FindBy(how = XPATH, using = ".//input[contains(@placeholder, 'Комментарий для курьера')]")
    private WebElement commentInput; //Комментарий для курьера - необязательное поле
    @FindBy(how = XPATH, using = ".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']")
    private WebElement finishOrderButton; // Кнопка Заказать

    //Подтверждение заказа
    @FindBy(how = CLASS_NAME, using = "Order_Modal__YZ-d3")
    private WebElement confirmOrderPopup; //Хотите оформить заказ?
    @FindBy(how = XPATH, using = ".//button[text()='Да']")
    private WebElement confirmOrderButton; //Да
    @FindBy(how = XPATH, using = ".//div[contains(text(), 'Заказ оформлен')]")
    private WebElement orderIsConfirmedPopup; //Поп-ап: заказ оформлен

    //Календарь
    @FindBy(how = CLASS_NAME, using = "react-datepicker__header")
    private WebElement datePickerDisplay; //Поп-ап с календарем
    @FindBy(how = XPATH, using = ".//div[@class='react-datepicker']/button[text()='Next Month']")
    private WebElement nextMonthButton; //Выбрать следующий месяц
    @FindBy(how = XPATH, using = ".//div[contains(@class, 'react-datepicker__day')][preceding::div[contains(@class, 'react-datepicker__day--today')]]")
    private WebElement tomorrow; //Доставка на завтра
    @FindBy(how = XPATH, using = ".//div[@class='react-datepicker__week'][last()]/div[contains(@class, 'react-datepicker__day--0') and not (contains(@class, 'react-datepicker__day--outside-month'))][last()]")
    private WebElement lastDayOfTheMonth; //Доставка на последний день выбранного месяца

    //Срок аренды
    @FindBy(how = CLASS_NAME, using = "Dropdown-menu")
    private WebElement rentDaysDisplay; //Поп-ап с выбором количества дней аренды
    @FindBy(how = XPATH, using = ".//div[@class='Dropdown-option' and text()='сутки']")
    private WebElement rentDaysMin; //Минимальный срок аренды - сутки
    @FindBy(how = XPATH, using = ".//div[@class='Dropdown-option' and text()='семеро суток']")
    private WebElement rentDaysMax; //Максимальный срок аренды - 7 суток

    //Метро
    @FindBy(how = CLASS_NAME, using = "select-search__select")
    private WebElement stationListDisplay; //Поп-ап списка станций

    //Ошибки
    @FindBy(how = XPATH, using = ".//div[text()='Введите корректное имя']")
    private WebElement invalidNameError; //Введите корректное имя

    @FindBy(how = XPATH, using = ".//div[text()='Введите корректную фамилию']")
    private WebElement invalidSurnameError; //Введите корректную фамилию

    @FindBy(how = XPATH, using = ".//div[text()='Введите корректный адрес']")
    private WebElement invalidAddressError; //Введите корректный адрес

    @FindBy(how = XPATH, using = ".//div[text()='Выберите станцию']")
    private WebElement noMetroStationSelectedError; //Выберите станцию

    @FindBy(how = XPATH, using = ".//div[text()='Введите корректный номер']")
    private WebElement invalidPhoneNumberError; //Введите корректный номер

    //Получить элемент станции по названию
    public WebElement getElementByStationName(String name) {
        String xpath1 = ".//div[@class='select-search__select']/ul/li[@class='select-search__row']/button/div[text()='";
        String xpath2 = name;
        String xpath3 = "']";
        String fullXpath = xpath1 + xpath2 + xpath3;
        return driver.findElement(By.xpath(fullXpath));
    }

    //Ожидание загрузки страницы Для кого самокат
    public OrderPage waitForOrderPage() {
        waitForElement(firstNameInput);
        return this;
    }

    //Ввод имени и фамилии
    public OrderPage inputName(String name) {
        firstNameInput.sendKeys(name);
        return this;
    }

    public OrderPage inputSurname(String surname) {
        surnameInput.sendKeys(surname);
        return this;
    }

    //Ввод адреса
    public OrderPage inputAddress(String address) {
        addressInput.sendKeys(address);
        return this;
    }

    //Выбор станции метро через ввод
    public OrderPage inputMetroStation(String stationName) {
        metroStationInput.click();
        metroStationInput.sendKeys(stationName);
        metroStationInput.sendKeys(Keys.DOWN);
        metroStationInput.sendKeys(Keys.RETURN);
        return this;
    }

    //Выбор станции метро через скролл
    public OrderPage selectMetroStation(String name) {
        metroStationInput.click();
        waitForElement(stationListDisplay);
        WebElement station = getElementByStationName(name);
        scrollToElement(station);
        station.click();
        return this;
    }

    //Ввод номера телефона
    public OrderPage inputPhoneNumber(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
        return this;
    }

    //Переход к странице Про аренду
    public OrderPage clickNext() {
        nextButton.click();
        return this;
    }

    //Выбор доставки на завтра
    public OrderPage selectDeliveryDateTomorrow() {
        waitForElement(deliveryDateInput);
        deliveryDateInput.click();
        waitForElement(datePickerDisplay);
        tomorrow.click();
        return this;
    }

    //Выбор доставки на последний день следующего месяца
    public OrderPage selectDeliveryDateLastDayOfNextMonth() {
        deliveryDateInput.click();
        waitForElement(nextMonthButton);
        nextMonthButton.click();
        waitForElement(lastDayOfTheMonth);
        lastDayOfTheMonth.click();
        return this;
    }

    //Выбор минимального срока аренды самоката
    public OrderPage selectRentDaysMin() {
        rentDaysInput.click();
        waitForElement(rentDaysDisplay);
        rentDaysMin.click();
        return this;
    }

    //Выбор максимального срока аренды самоката
    public OrderPage selectRentDaysMax() {
        rentDaysInput.click();
        waitForElement(rentDaysDisplay);
        scrollToElement(rentDaysMax);
        rentDaysMax.click();
        return this;
    }

    //Выбор цвета самоката: черный - опционально
    public OrderPage selectBlackColor() {
        blackColor.click();
        return this;
    }

    //Выбор цвета самоката: серый - опционально
    public OrderPage selectGreyColor() {
        greyColor.click();
        return this;
    }

    //Добавление комментария - опционально
    public OrderPage addComment(String comment) {
        commentInput.sendKeys(comment);
        return this;
    }

    //Заказать
    public OrderPage placeOrder() {
        waitForElement(finishOrderButton);
        finishOrderButton.click();
        waitForElement(confirmOrderPopup);
        confirmOrderButton.click();
        return this;
    }

    //Получение подтверждения заказа
    public String getOrderConfirmation() {
        waitForElement(orderIsConfirmedPopup);
        return orderIsConfirmedPopup.getText();
    }

    //Переход к главной странице через логотип Скутера
    public HomePageOld goToHomePage() {
        homePageButton.click();
        return new HomePageOld(driver);
    }

    //TAB для получения ошибки
    public OrderPage clickTab() {
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        return this;
    }

    //Ошибка: некорректное имя
    public boolean getInvalidNameError() {
        waitForElement(invalidNameError);
        return invalidNameError.isDisplayed();
    }

    //Ошибка: некорректная фамилия
    public boolean getInvalidSurnameError() {
        waitForElement(invalidSurnameError);
        return invalidSurnameError.isDisplayed();
    }

    //Ошибка: некорректный адрес
    public boolean getInvalidAddressError() {
        waitForElement(invalidAddressError);
        return invalidAddressError.isDisplayed();
    }

    //Ошибка: не выбрана станция метро
    public boolean getNoMetroStationSelectedError() {
        waitForElement(noMetroStationSelectedError);
        return noMetroStationSelectedError.isDisplayed();
    }

    //Ошибка: некорректный номер телефона
    public boolean getInvalidPhoneNumberError() {
        waitForElement(invalidPhoneNumberError);
        return invalidPhoneNumberError.isDisplayed();
    }
}