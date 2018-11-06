package logic.pages;

import framework.wdm.WdManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        driver = WdManager.get();
        wait = WdManager.getWait();
        PageFactory.initElements(driver, this);
    }


    //region Useful actions

    /**
     * @param tbl table which contains labels and values
     * @param lbl label to fill value in
     * @param val value to be filled
     */
    protected void enterValueByLabel(WebElement tbl, String lbl, String val) {
        WebElement ele =
                tbl.findElement(By.xpath("//tr/td[@class='label' and contains(text(),'" + lbl + "')]/following-sibling::td/input"));
        //ele.click();
        // ele.clear();
        ele.sendKeys(val);
    }

    /**
     * @param tbl table which contains labels and values
     * @param lbl label to get value
     * @return
     */
    protected String getValueByLabel(WebElement tbl, String lbl) {
        return tbl.findElement(By.xpath("")).getText();
    }

    protected WebElement getCell(WebElement tbl, int row, int col) {
        return tbl.findElement(By.xpath("//tr[" + row + "]/td[" + col + "]"));
    }

    public void waitUntilElementVisible(WebElement ele) {
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    //endregion
}
