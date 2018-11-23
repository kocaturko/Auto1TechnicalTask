import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Part of assignment
 * Created by kocaturko on 11/23/18.
 */
public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage filterByYearAndSortDescending() {
        clickTo(By.xpath("//*[@class='side___1TuKo hidden-xs hidden-sm']/div/div[3]"));
        clickTo(By.xpath("//*[@name='yearRange.min']"));
        clickTo(By.xpath("//*[@data-qa-selector-value='2015']"));
        waitAWhile(3);
        pressEscape();
        clickTo(By.xpath("//*[@name='sort']"));
        clickTo(By.xpath("//*[@data-qa-selector-value='offerPrice.amountMinorUnits.desc']"));
        sleep(2000);

        return this;
    }
}
