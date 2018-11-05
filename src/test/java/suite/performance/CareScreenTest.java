package suite.performance;

import com.aventstack.extentreports.Status;
import javafx.util.Pair;
import logic.pages.care.FindPage;
import logic.pages.care.LoginPage;
import logic.util.Timer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import suite.BaseTest;

public class CareScreenTest extends BaseTest {
    protected LoginPage loginPage;
    protected FindPage findPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage();
        findPage = new FindPage();
    }

    @Test
    public void testLoginScreen() {
        Timer.start();
        loginPage.login("admin", "ADMIN1");
        test.get().log(Status.PASS, "Login loading time: " + Timer.stop() + " ms");

        Timer.start();
        findPage.findCustomer(new Pair<>("First Name", "first*"));
        test.get().log(Status.PASS, "Find customer loading time: " + Timer.stop() + " ms");

        Timer.start();
        findPage.openCustomerByIndex(1);
        test.get().log(Status.PASS, "Open customer loading time: " + Timer.stop() + " ms");

    }

    @Test
    public void testLoginScreen2() {
        Timer.start();
        loginPage.login("admin", "ADMIN1");
        System.out.println("Elapsed time on 2: " + Timer.stop());

    }

    @Test
    public void testLoginScreen3() {
        Timer.start();
        loginPage.login("admin", "ADMIN1");
        System.out.println("Elapsed time on 3: " + Timer.stop());

    }
}
