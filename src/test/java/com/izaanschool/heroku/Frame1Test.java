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

import java.util.concurrent.TimeUnit;


/** problem statement
 * we want to navigate to the  frame page from herokuapp page and will see if
 * 1.we can go to the iFrame page from there
 * 2. we can write our text inside the text area inside the iframe
 */

//ref link : https://www.youtube.com/watch?v=XVxT8B6S0Y8

public class Frame1Test {
    public static Logger logger = LogManager.getLogger(Frame1Test.class);
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
    public void iFrameTest () throws InterruptedException {
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Frames")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Frames";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        // now go to the iFrame page by clicking the iframe link
        driver.findElement(By.linkText("iFrame")).click();

        // verify that we landed on the expected iframe page by comparing the headings
        //An iFrame containing the TinyMCE WYSIWYG Editor
        String heading3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(heading3);
        String headingActual = "An iFrame containing the TinyMCE WYSIWYG Editor";
        Assert.assertEquals(headingActual, heading3);
        logger.info("test passed");

        // switch to the inside frame from the main content
        driver.switchTo().frame("mce_0_ifr");
        System.out.println("passed");

        // locate the boyd of the element to write text inside the iframe

        WebElement iFrameTextBox=   driver.findElement(By.xpath("//*[@id=\"tinymce\"]"));
        Thread.sleep(2000);

        //clear the text box
        iFrameTextBox.clear();
        Thread.sleep(2000);

        //add new text
        iFrameTextBox.sendKeys("I am writing a new text into the Text Area Inside the frame");
        Thread.sleep(2000);

        //print the text
        String s= driver.findElement(By.tagName("p")).getText();
        System.out.println(s);


        //switch back to the main content
        driver.switchTo().defaultContent();
        driver.quit();

    }
    }
