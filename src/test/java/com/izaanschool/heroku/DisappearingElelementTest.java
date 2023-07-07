package com.izaanschool.heroku;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/** problem statement
 * we want to navigate to disappearing  Elements page from herokuapp page and
 * will see if the buttons are working or not.
 * to do so,
 * 1. we will navigate to that page
 * 2. click the home button.
 * verify if it is taking me to the homepage or not.

 */

public class DisappearingElelementTest {
    public static Logger logger = LogManager.getLogger(DisappearingElelementTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest() {
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
        driver.findElement(By.linkText("Disappearing Elements")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Disappearing Elements";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");


        //go to upload file  page by clicking  hyperlik from the homepage.
        driver.findElement(By.linkText("Home")).click();

        //verify if we landed on the page or not by comparing the heading
        String h1 = driver.findElement(By.tagName("h1")).getText();
        logger.info(h1);
        String h1actual = "Welcome to the-internet";
        Assert.assertEquals(h1actual, h1);
        logger.info("test passed");


        Thread.sleep(2000);

    }
    @After
    public void cleanup(){
        driver.close();
    }

}
