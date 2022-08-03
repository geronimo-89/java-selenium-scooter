package chrometests;

import org.junit.Test;
import setup.SetUpChrome;
import static org.junit.Assert.assertEquals;
import static pageobject.old.HomePageOld.YANDEX_LINK;

public class HomeButtonsTest extends SetUpChrome {

    @Test
    public void checkGoToHomePage() {
        assertEquals(
                HOME_PAGE_LINK,
                homePage
                        .orderMethod2()
                        .waitForOrderPage()
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