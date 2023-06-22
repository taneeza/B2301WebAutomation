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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.lang.Thread;
import java.io.*;

import java.util.concurrent.TimeUnit;


/** problem statement
 * we want to navigate to Drag And Drop Elements page from herokuapp page and will see if
 * the drag and drop action works porperly or not
 * to do so,
 * 1. we will navigate to that page
 * 2.we will drag element A and release it on element B.
 * 3. we will check if the A element has been moved to element B and vice versa.

 */

public class ContextMenu {
    public static Logger logger = LogManager.getLogger(CheckBoxesPageTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest() {
        String driverPath = System.getProperty("user.dir") + "/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        // implicit wait is the maximum time set between two steps/commands of the automation script.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void contextMenuTest() throws InterruptedException {
        //go to Add/remove  page by clicking add/remove hyperlik from the homepage.
        driver.findElement(By.linkText("Context Menu")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Context Menu";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        //https://www.guru99.com/double-click-and-right-click-selenium.html


        // Right click the button to launch right click menu options
        Actions action = new Actions(driver);

        //find the box
       WebElement box= driver.findElement(By.id("hot-spot"));

       //right click
        action.contextClick(box).perform();
       Thread.sleep(5000);



        // Accept the Alert
        driver.switchTo().alert().accept();
        logger.info("Right click Alert Accepted");
        logger.info("test passed");
        Thread.sleep(5000);

       // /        Alert alert = driver.switchTo ().alert ();
//        String alertMessage = driver.switchTo ().alert ().getText ();
//        logger.info (alertMessage);
//        Thread.sleep (5000);
//        alert.accept ();



        //verify (not finding the element)
        //driver.findElement(By.xpath("//*[@id=\"content\"]/script"));
        //Assert.assertEquals("Option 1", actualValue);

        //logger.info(actualValue);

    }
    @After
    public void cleanup(){

        driver.close();
    }

}
