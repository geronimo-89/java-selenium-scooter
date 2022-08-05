package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.openqa.selenium.support.How.*;

public class TrackPage extends ScooterPages {

    public TrackPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = CLASS_NAME, using = "Track_NotFound__6oaoY")
    private WebElement orderNotFound; //Ошибка: такого заказа нет

    //Проверка отображения окна с ошибкой номера заказа
    public boolean getOrderNotFoundState() {
        waitForElement(orderNotFound);
        return orderNotFound.isDisplayed();
    }

}