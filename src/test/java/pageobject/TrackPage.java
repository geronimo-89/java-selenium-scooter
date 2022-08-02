package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TrackPage {

    private WebDriver driver;
    private JavascriptExecutor js;

    public TrackPage(WebDriver driver) {
        this.driver = driver;
    }
}
