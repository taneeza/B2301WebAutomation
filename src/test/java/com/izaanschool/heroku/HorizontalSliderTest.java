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
 * we want to navigate to the  horizontal slider page page from herokuapp page and will see if
 * the horizontal slides to the right or not
 * to do so,
 * 1. we will navigate to that page
 * 2.we will slide the slider from left to right and right to left
 * 3. we will check if the A element has been moved to element B and vice versa.

 */
//https://stackoverflow.com/questions/15171745/how-to-slidemove-slider-in-selenium-webdriver
//https://www.youtube.com/watch?v=1aBu0xxGIFM


public class HorizontalSliderTest {
    public static Logger logger = LogManager.getLogger(HorizontalSliderTest.class);
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
    public void dragSliderLeft (){
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Horizontal Slider")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Horizontal Slider";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        //find the slider
        WebElement slider = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/input"));

        //find the output element
        WebElement output = driver.findElement(By.id("range"));
        System.out.println(output.getText());

        //drag action
        Actions act = new Actions (driver);
        act.dragAndDropBy(slider, 0, 0).perform();

        System.out.println("What Is The Output After Dragging Left? " + output.getText());
    }

    @Test
    public void dragSliderRight ()
    {
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Horizontal Slider")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Horizontal Slider";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        WebElement slider = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/input"));

        WebElement output = driver.findElement(By.id("range"));

        Actions act = new Actions (driver);
        act.dragAndDropBy(slider, 50, 0).perform();

        System.out.println("What Is The Output After Dragging Right? " + output.getText());
    }
   /* @After
    public void cleanup(){

        driver.close();
    }*/


}



