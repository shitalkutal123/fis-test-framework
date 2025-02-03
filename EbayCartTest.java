/**
 * 
 */
package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.main.MainTest;

/**
 * 
 */
public class EbayCartTest extends MainTest{
	
	 @Test
	    public void testAddItemToCart() throws InterruptedException {
	        driver.get("https://www.ebay.com");

	        // ✅ Search for 'book'
	        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("_nkw")));
	        searchBox.sendKeys("book");
	        searchBox.submit();

	        // ✅ Wait for search results to load
	        List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[5]/div[4]/div[3]/div[1]/div[3]/ul/li[1]/div/div[2]/a/div")));
	        System.out.println("Number of books found: " + items.size());

	        if (items.isEmpty()) {
	            Assert.fail("No search results found!");
	        }

	        // ✅ Scroll to the first book and ensure it's visible
	        WebElement firstItem = items.get(0);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstItem);
	        Thread.sleep(1000);

	        // ✅ Handle overlays before clicking
	        try {
	            firstItem.click();
	        } catch (ElementClickInterceptedException e) {
	            System.out.println("Click intercepted, trying with JavaScript Executor...");
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstItem);
	        }

	        // ✅ Switch to new tab if a new page opens
	        for (String handle : driver.getWindowHandles()) {
	            driver.switchTo().window(handle);
	        }

	        // ✅ Wait and close popups (if any)
	        try {
	            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close-button-selector"))); // Replace with actual close button selector
	            closeButton.click();
	            System.out.println("Popup closed successfully.");
	        } catch (Exception e) {
	            System.out.println("No popup found.");
	        }

	        // ✅ Wait for 'Add to Cart' button using a more reliable locator
	        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"atcBtn_btn_1\"]")));

	        // ✅ Click 'Add to Cart' using JavaScript as a fallback
	        try {
	            addToCartButton.click();
	        } catch (ElementNotInteractableException e) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
	        }

	        // ✅ Wait for cart update and verify item count
	        WebElement cartCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gh\"]/nav/div[2]/div[4]/div/a")));
	        Assert.assertTrue(Integer.parseInt(cartCount.getText()) > 0, "Cart count should be greater than 0");
	    }


}
