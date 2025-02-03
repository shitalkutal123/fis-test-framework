/**
 * 
 */
package com.automation.main;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * 
 */
public class MainTest {
	
	
	    protected WebDriver driver;
	    protected WebDriverWait wait;

	    @BeforeClass
	    public void setUp() {
	    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaibh\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	        //WebDriverManager.chromedriver().setup(); // Auto-manage WebDriver
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized");
	        options.addArguments("--disable-gpu");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--remote-allow-origins=*");

	        driver = new ChromeDriver(options);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	
	
}
