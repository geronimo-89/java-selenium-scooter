package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.How.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.old.HomePageOld;

public class OrderPage {

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;
    private JavascriptExecutor js;

    @FindBy(how = CLASS_NAME, using = "Header_LogoScooter__3lsAR")
    private WebElement homePageButton; //На главную страницу

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

    //Получить xpath станции по названию
    public String getXpathByStationName(String name) {
        String xpath1 = ".//div[@class='select-search__select']/ul/li[@class='select-search__row']/button/div[text()='";
        String xpath2 = name;
        String xpath3 = "']";
        String fullXpath = xpath1 + xpath2 + xpath3;
        return fullXpath;
    }

    //Ожидание загрузки страницы Для кого самокат
    public OrderPage waitForOrderPage() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(firstNameInput));
        return this;
    }

    //Ввод имени и фамилии
    public OrderPage inputNameSurname(String name, String surname) {
        firstNameInput.sendKeys(name);
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
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(stationListDisplay));
        WebElement station = driver.findElement(By.xpath(getXpathByStationName(name)));
        js.executeScript("arguments[0].scrollIntoView();", station);
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
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(deliveryDateInput));
        return this;
    }

    //Выбор доставки на завтра
    public OrderPage selectDeliveryDateTomorrow() {
        deliveryDateInput.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(datePickerDisplay));
        tomorrow.click();
        return this;
    }

    //Выбор доставки на последний день следующего месяца
    public OrderPage selectDeliveryDateLastDayOfNextMonth() {
        deliveryDateInput.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(nextMonthButton));
        nextMonthButton.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(lastDayOfTheMonth));
        lastDayOfTheMonth.click();
        return this;
    }

    //Выбор минимального срока аренды самоката
    public OrderPage selectRentDaysMin() {
        rentDaysInput.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(rentDaysDisplay));
        rentDaysMin.click();
        return this;
    }

    //Выбор максимального срока аренды самоката
    public OrderPage selectRentDaysMax() {
        rentDaysInput.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(rentDaysDisplay));
        js.executeScript("arguments[0].scrollIntoView();", rentDaysMax);
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
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(finishOrderButton));
        finishOrderButton.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(confirmOrderPopup));
        confirmOrderButton.click();
        return this;
    }

    //Получение подтверждения заказа
    public String getOrderConfirmation() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(orderIsConfirmedPopup));
        return orderIsConfirmedPopup.getText();
    }

    //Переход к главной странице через логотип Скутера
    public HomePageOld goToHomePage() {
        homePageButton.click();
        return new HomePageOld(driver);
    }
}
