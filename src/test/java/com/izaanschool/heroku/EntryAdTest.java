package com.izaanschool.heroku;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/** problem statement
 * we want to navigate to entry ad Elements page from herokuapp page and
 * will see if we can see the ad window and close the popup ad  window


 */

public class EntryAdTest {
    public static Logger logger = LogManager.getLogger(EntryAdTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void entryAdTest() {
        String driverPath = System.getProperty("user.dir") + "/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();

    }

    @Test
    public void disappearingElementTest() throws InterruptedException {
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Entry Ad")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Entry Ad";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");



        // ********* CLICK HERE verifying **************//
        driver.findElement(By.linkText("click here")).click();
       // String closeKey = driver.findElement(By.xpath("//*[@id=\"modal\"]/div[2]/div[3]/p")).getText();
        Thread.sleep(5000);
       // logger.info(closeKey);

        //find modal window and verify
        String modalWindow = driver.findElement(By.xpath("//*[@id=\"modal\"]/div[2]/div[1]/h3")).getText();
        Assert.assertEquals("THIS IS A MODAL WINDOW",modalWindow);
        Thread.sleep(3000);

       //click the close key
        driver.findElement(By.xpath("//*[@id=\"modal\"]/div[2]/div[3]/p")).click();
        logger.info("close Button clicked");
    }

    @After
    public void cleanUp() {
        driver.close();

    }
    }





