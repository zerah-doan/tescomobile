package suite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework.config.ConfigUtil;
import framework.wdm.WDFactory;
import framework.wdm.WdManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {

    //region Hooks
    @BeforeSuite
    public void beforeSuite() {
        ConfigUtil.loadEnvInfoToQueue();
        setUpReport();
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        test.set(extent.createTest(m.getName()));
        WdManager.set(WDFactory.initBrowser(ConfigUtil.getProp("browser")));
        WdManager.get().get(ConfigUtil.getProp("env"));
    }

    @AfterMethod
    public void afterMethod() {
        ConfigUtil.returnProp();
        WdManager.dismissWD();
    }

    //endregion

    //region Report
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal();

    private void setUpReport() {
        //HTML
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output\\report.html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Test Report Title");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Test Report Name");

        //Klov
        KlovReporter klovReporter = new KlovReporter();
        klovReporter.initMongoDbConnection("localhost", 27017);
        klovReporter.setProjectName("TescoMobile");
        klovReporter.setReportName("Test Report Name");
        klovReporter.setKlovUrl("http://localhost");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter, klovReporter);
    }
    //endregion

}
