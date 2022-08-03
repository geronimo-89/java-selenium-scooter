package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.How.*;

public class TrackPage {

    public TrackPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;
    private JavascriptExecutor js;

    @FindBy(how = CLASS_NAME, using = "Track_NotFound__6oaoY")
    private WebElement orderNotFound; //Ошибка: такого заказа нет

    //Проверка отображения окна с ошибкой номера заказа
    public boolean getOrderNotFoundState() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(orderNotFound));
        return orderNotFound.isDisplayed();
    }

}
