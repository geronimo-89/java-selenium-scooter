package chrometests;

import org.junit.Test;
import setup.SetUpChrome;
import static org.junit.Assert.assertEquals;
import static pageobject.HomePage.YANDEX_LINK;

public class HomeButtonsTest extends SetUpChrome {

    @Test
    public void checkGoToHomePage() {
        assertEquals(
                HOME_PAGE_LINK,
                homePage
                        .orderMethod2()
                        .waitForOrderDetailsPage1()
                        .goToHomePage()
                        .waitForHomePage()
                        .getCurrentURL());
    }

    @Test
    public void checkGoToYandex() {
        assertEquals(
                YANDEX_LINK,
                homePage.goToYandex()
                        .getCurrentURL());
    }

}