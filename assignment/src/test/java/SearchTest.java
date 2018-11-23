import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Part of assignment
 * Created by kocaturko on 11/23/18.
 */
public class SearchTest extends BaseTest {

    @Test
    public void searchCars() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.filterByYearAndSortDescending();

        assertDates();
        assertPrices();
    }

    private void assertDates() {
        List<WebElement> listDates = driver.findElements(By.xpath("//*[@data-qa-selector='results-found']/div[1]/div/div/a/ul/li[1]"));

        // Get years and compare with 2015
        int year;
        for (WebElement element : listDates) {
            String date = element.getText().substring(5, 9);
            year = Integer.parseInt(date);

            assertTrue("Listed car's model is earlier than 2015!",year >= 2015);
        }
    }

    private void assertPrices() {
        List<WebElement> listPrices = driver.findElements(By.xpath("//*[@data-qa-selector='price']"));

        // Get prices and add to list
        int amount=0;
        List<Integer> amountList = new ArrayList<>();
        for (WebElement element : listPrices) {
            String price = element.getText().substring(0, 6);
            amount = Integer.parseInt(price.replace(".", ""));
            amountList.add(amount);
        }

        // Compare elements of the list
        int arraySize = amountList.size()-1;
        for (int i=0; i<arraySize; i++){
            assertTrue("Listed cars' price is not displayed with descending order!",amountList.get(i)>=amountList.get(i+1));
        }
    }
}
