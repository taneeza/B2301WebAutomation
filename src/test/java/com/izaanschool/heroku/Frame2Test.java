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

import java.util.List;
import java.util.concurrent.TimeUnit;


/** problem statement
 * we want to navigate to the  frame page from herokuapp page and will see if
 * 1.we can go to the Frame page from there.
 * 2.then we will click on nested frame link
 * 3. we will see if the nested frames are working properly or not.
 */


public class Frame2Test {
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
    public void nestedFrameTest () throws InterruptedException {
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Frames")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Frames";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        // now go to the Nested Frames page by clicking the iframe link
        driver.findElement(By.linkText("Nested Frames")).click();

        logger.info("test passed");

        //find the no. of frames

        List<WebElement> frames= driver.findElements(By.tagName("frame"));
        System.out.println( "no of frames :  " + frames.size());

        //switch to the bottom frame using index no or name or id. here we used name.

        driver.switchTo().frame("frame-bottom");

        // get the body text from the bottom frame.
        String text1= driver.findElement(By.tagName("body")).getText();

        System.out.println( text1);

        // get out of the bottom frame to the main frame.

        driver.switchTo().parentFrame();

        //switch to the top frame
        driver.switchTo().frame("frame-top");

        //now in top frame there are 1 frameset . inside the frameset there are 3 frames.

        List<WebElement> topFrames=driver.findElements(By.tagName("frame"));
        System.out.println("No of frame in top frame:   "+ topFrames.size());

        // switch from top frame to its child left frame

        driver.switchTo().frame("frame-left");

        //get the text "LEFT"
        String leftFrame= driver.findElement(By.tagName("body")).getText();

        System.out.println(" left frame text =   " + leftFrame);

        // switch to parentframe
        driver.switchTo().parentFrame();

        //switch to the middle frame
        driver.switchTo().frame("frame-middle");

        //get the text "MIDDLE"
        String middleFrame= driver.findElement(By.tagName("body")).getText();
        System.out.println(" middle frame text =   " + middleFrame);

        //go to the default container

        driver.switchTo().defaultContent();

        driver.quit();

    }
}
