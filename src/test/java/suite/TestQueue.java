package suite;

import framework.config.ConfigUtil;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.wdm.WDFactory;
import framework.wdm.WDManager;

import java.net.URL;

public class TestQueue {
    @BeforeClass
    public void beforeClass() {
        ConfigUtil.loadEnvInfoToQueue();
    }


    @BeforeMethod
    public void beforeMethod() {
        WDManager.setWD(WDFactory.initBrowser(ConfigUtil.getProp("browser")));
        WDManager.getWD().get(ConfigUtil.getProp("env"));
    }

    @AfterMethod
    public void afterMethod() {
        ConfigUtil.returnProp();
        WDManager.dismissWD();
    }

    @Test(priority = 1)
    public void aaa() throws Exception {
        System.out.println("aaa using" + WDManager.getWD().getTitle());
    }

    @Test(priority = 1)
    public void bbb() throws Exception {
        System.out.println("bbb using " + WDManager.getWD().getTitle());
    }

    @Test(priority = 1, groups = {})
    public void ccc() throws Exception {
        System.out.println("ccc using " + WDManager.getWD().getTitle());
    }

    @Test(priority = 1)
    public void ddd() throws Exception {
        System.out.println("ddd using " + WDManager.getWD().getTitle());
    }

    //@Test(priority = 1)
    public void eee() throws Exception {
        WDManager.setWD(WDFactory.remote(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()));
        WDManager.getWD().get("https://google.com");
        System.out.println(WDManager.getWD().getTitle());
        System.out.println("eee using " + ConfigUtil.getProp("env"));
    }

    //@Test(priority = 1)
    public void fff() throws Exception {
        WDManager.setWD(WDFactory.remote(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()));
        WDManager.getWD().get("https://facebook.com");
        System.out.println(WDManager.getWD().getTitle());
        System.out.println("fff using " + ConfigUtil.getProp("env"));
    }
}
