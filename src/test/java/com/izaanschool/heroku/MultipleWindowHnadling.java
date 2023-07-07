package com.izaanschool.heroku;
import java.util.Iterator;
import java.util.Set;

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

import java.util.List;
import java.util.concurrent.TimeUnit;

/** problem statement
 * we want to navigate to the Multiple Windows page from herokuapp page and will see if
 * 1.we can go to the Multiple Windows page from there.
 * 2.then we will click on "click here" button from that page
 * 3. A new tab/window will open .
 * we will see if we can switch to that window to locate the heading of that tab/window
 */

//https://www.youtube.com/watch?v=oywjm-W3wac
public class MultipleWindowHnadling {
    public static Logger logger = LogManager.getLogger(AddRemoveElementsTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest() {
        String driverPath = System.getProperty("user.dir") + "/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void addRemoveElementTest() throws InterruptedException {
        //go to Add/remove  page by clicking add/remove hyperlik from the homepage.
        driver.findElement(By.linkText("Multiple Windows")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3actual = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3actual);
        String h3expected = "Opening a new window";
        Assert.assertEquals(h3expected, h3actual);
        logger.info("test passed");

        // find the element to click
        WebElement clickButton= driver.findElement(By.linkText("Click Here"));
        clickButton.click();

        //find all the tab /windows
        Set<String> handles = driver.getWindowHandles();
        Iterator it= handles.iterator();
        String parentWindow= (String) it.next();
        String childWindow= (String) it.next();
        // switch to newtab

        driver.switchTo().window(childWindow);

        Thread.sleep(3000);

        //verify that we landed on the new tab
        String headingActual= driver.findElement(By.tagName("h3")).getText();
        logger.info(headingActual);
        String headingExpected= "New Window";
        Assert.assertEquals(headingExpected,headingActual);
        logger.info("test Passed");

    }

    @After
    public void addRemoveTest() {
        driver.close();

          }
}
