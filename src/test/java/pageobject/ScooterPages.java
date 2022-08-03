package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterPages {

    public ScooterPages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;
    private JavascriptExecutor js;

    //Стандартное ожидание элемента
    public WebElement waitForElement(WebElement element) {
        return new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(element));
    }
}
