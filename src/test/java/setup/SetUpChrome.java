package setup;

import org.junit.Before;

public class SetUpChrome extends SetUpDriver {

    @Before
    public void chromeTestSetup() {
        chromeDriverSetUp();
        openHomePage();
        setUpObjects();
        maximizeWindow();
        acceptCookies();
    }
}
