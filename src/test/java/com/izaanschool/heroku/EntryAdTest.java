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

import java.util.concurrent.TimeUnit;

/** problem statement
 * we want to navigate to file download Elements page from herokuapp page and
 * will see if we can download  a file to our local machine
 * to do so,
 * 1. we will navigate to that page
 * 2.we will choose a file from the page and download to my local machine
 * 3. we will check if the file has been downloaded or not

 */

public class EntryAdTest {
    public static Logger logger = LogManager.getLogger(DisappearingElelementTest.class);
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


        //find the button that we have to click to see the pup-up ad
       // WebElement button= driver.findElement(By.xpath("//*[@id=\"restart-ad\"]"));
        Thread.sleep(5000);

        //click the button
        //button.click();

        // switch to alert
        Alert alert = driver.switchTo().alert();

        String alertMessage= driver.switchTo().alert().getText(); // capture alert message

        logger.info(alertMessage); // Print Alert Message
        Thread.sleep(2000);
        alert.accept();

    }
    @After
    public void cleanup(){
        driver.close();
    }



}
