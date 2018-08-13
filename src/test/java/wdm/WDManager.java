package wdm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Manage WD instances
 */
public class WDManager {

    /**
     * ThreadLocal helps manage WD instances among threads
     */
    private static ThreadLocal<WebDriver> wdm = new ThreadLocal<>();
    /**
     * ThreadLocal helps mânge WD wait instances among threads
     */
    private static ThreadLocal<WebDriverWait> waitManager = new ThreadLocal<>();

    /**
     * Get WD in current thread
     *
     * @return WD in current thread
     */
    public static WebDriver getWD() {
        return wdm.get();
    }

    /**
     * Get WD wait
     *
     * @return WD wait in current thread
     */
    public static WebDriverWait getWDWait() {
        return waitManager.get();
    }

    /**
     * Set WD to current thread
     *
     * @param driver
     */
    public static void setWD(final WebDriver driver) {
        if (wdm.get() != null) {
            dismissWD();
        }
        wdm.set(driver);
        waitManager.set(new WebDriverWait(wdm.get(), 20));
    }

    /**
     * Close and remove WD from current thread
     */
    public static void dismissWD() {
        getWD().quit();
        wdm.remove();
    }
}
