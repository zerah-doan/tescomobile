package suite;

import framework.config.ConfigUtil;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.wdm.WDFactory;
import framework.wdm.WdManager;

import java.net.URL;

public class TestQueue {
    @BeforeClass
    public void beforeClass() {
        ConfigUtil.loadEnvInfoToQueue();
    }


    @BeforeMethod
    public void beforeMethod() {
        WdManager.set(WDFactory.initBrowser(ConfigUtil.getProp("browser")));
        WdManager.get().get(ConfigUtil.getProp("env"));
    }

    @AfterMethod
    public void afterMethod() {
        ConfigUtil.returnProp();
        WdManager.dismissWD();
    }

    @Test(priority = 1)
    public void aaa() throws Exception {
        System.out.println("aaa using" + WdManager.get().getTitle());
    }

    @Test(priority = 1)
    public void bbb() throws Exception {
        System.out.println("bbb using " + WdManager.get().getTitle());
    }

    @Test(priority = 1, groups = {})
    public void ccc() throws Exception {
        System.out.println("ccc using " + WdManager.get().getTitle());
    }

    @Test(priority = 1)
    public void ddd() throws Exception {
        System.out.println("ddd using " + WdManager.get().getTitle());
    }

    //@Test(priority = 1)
    public void eee() throws Exception {
        WdManager.set(WDFactory.remote(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()));
        WdManager.get().get("https://google.com");
        System.out.println(WdManager.get().getTitle());
        System.out.println("eee using " + ConfigUtil.getProp("env"));
    }

    //@Test(priority = 1)
    public void fff() throws Exception {
        WdManager.set(WDFactory.remote(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()));
        WdManager.get().get("https://facebook.com");
        System.out.println(WdManager.get().getTitle());
        System.out.println("fff using " + ConfigUtil.getProp("env"));
    }
}
