import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Part of assignment
 * Created by kocaturko on 11/23/18.
 */
public abstract class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForAjax(WebDriver driver) {
        try {
            WebDriverWait myWait = new WebDriverWait(driver, 10);
            ExpectedCondition<Boolean> conditionToCheck = input -> {
                JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
                boolean stillRunningAjax = (Boolean) jsDriver
                        .executeScript("return window.jQuery != undefined && jQuery.active != 0");
                return !stillRunningAjax;
            };

            myWait.until(conditionToCheck);
        } catch (Exception ex) {
            System.out.println("Ajax Waiting Time Expired");
        }
    }

    public void clickTo(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        waitForAjax(driver);
    }

    public WebDriverWait waitAWhile(long waitTime) {
        return new WebDriverWait(driver, waitTime);
    }

    public void pressEscape() {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ESCAPE).perform();
    }

    public void sleep(long time) {
        try{
            Thread.sleep(time);
        } catch (InterruptedException ignore) {
        }
    }
}