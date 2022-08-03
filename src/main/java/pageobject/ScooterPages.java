package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterPages {

    protected WebDriver driver;
    protected JavascriptExecutor js;

    //Стандартное ожидание элемента
    public WebElement waitForElement(WebElement element) {
        return new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(element));
    }

    //Скролл до элемента
    public void scrollToElement(WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    //Показать текущий URL страницы
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}
