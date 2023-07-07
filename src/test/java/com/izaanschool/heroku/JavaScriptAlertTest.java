package com.izaanschool.heroku;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

/** Problem Statement
 *  open http://the-internet.herokuapp.com
 *  click JavaScript Alerts and open JavaScript Alerts page
 *  a page should open showing 3 button to click
 * I want click "Click for JS Promp" and pop up window should show up with fillup tab
 * I want to write "Selenium" and click ok
 * Result should sho
 */



public class JavaScriptAlertTest {
    public static Logger logger = LogManager.getLogger(NotificationMessagesTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest() {
        String driverPath = System.getProperty("user.dir") + "/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void javaScriptTest() throws InterruptedException {
        //click the link to navigate to the page
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        logger.info("JavaScript Alert page opened");

        // click "CLICK FOR JS PROMPT"
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[3]/button")).click();
        // Write "Selenium" inside pop up tab
       Alert alert = driver.switchTo().alert();
        Thread.sleep(3000);
        alert.sendKeys("Selenium");
        Thread.sleep(3000);
        alert.accept();

        Thread.sleep(3000);

        // Verify Result message
        WebElement resultMessage = driver.findElement(By.id("result"));
        Assert.assertEquals(resultMessage.getText(), "You entered: Selenium");

        String message= resultMessage.getText();

        logger.info("test passed  " + message );

    }
    @After
    public void cleanup () {
        driver.close();
    }
}
