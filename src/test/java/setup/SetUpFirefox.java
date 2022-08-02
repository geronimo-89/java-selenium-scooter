package setup;

import org.junit.Before;

public class SetUpFirefox extends SetUpDriver {

    @Before
    public void fireFoxTestSetup() {
        firefoxDriverSetUp();
        openHomePage();
        setUpObjects();
        maximizeWindow();
        acceptCookies();
    }
}
