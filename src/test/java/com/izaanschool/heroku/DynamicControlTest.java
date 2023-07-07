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

import java.util.List;
import java.util.concurrent.TimeUnit;

//Both CKEditor and TinyMCE are JavaScript based, which means they can be
// automated using Selenium WebDriver's native API just like any other HTML web applications.

/**  * Problem Statement
 *I want to go to Heroku app website and verify if we can navigate to the Dynamic Controls page
 * I will see
 * 1. if I can click the remove button and after clicking if the add button appears and checkbox disappears
 * 3. if I can click the enable button and after that if the disable button appears or not.
 * 4. after enabling if I can write inside the text box
 */

public class DynamicControlTest {
    public static Logger logger = LogManager.getLogger(DynamicControlTest.class);
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
    public void dynamicControlRemoveButtonTest() throws InterruptedException {
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Dynamic Controls")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h4")).getText();
        logger.info(h3);
        String h3expected = "Dynamic Controls";
        Assert.assertEquals(h3expected, h3);
        logger.info("test passed");

        // find if chceck box is present
        WebElement box= driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input"));
        logger.info("checkbox is present");

        //find the remove button
        WebElement removeButton= driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button"));

        //get the text of the button
        String button1= removeButton.getText();
        logger.info("button" + button1);

        //click the remove button
        removeButton.click();
        Thread.sleep(5000);

        //find the add button
        WebElement addButton =driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button"));

        //get the text of the button
        String button2= addButton.getText();
        logger.info(button2);

        // verify the button name is add

        Assert.assertEquals("Add",button2);
        logger.info("add button appeared");

        //verify if the checkbox is deleted or not. list size will be zero.
        List<WebElement> checkbox= driver.findElements(By.xpath("//*[@id=\"checkbox\"]/input"));
        int x= checkbox.size();
        //size should be 0 when no checkbox is present.
        Assert.assertEquals(0, x);

        logger.info("no checkbox present");
    }
    @Test
    public void dynamicControlEnableButtonTest() throws InterruptedException {
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Dynamic Controls")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h4")).getText();
        logger.info(h3);
        String h3expected = "Dynamic Controls";
        Assert.assertEquals(h3expected, h3);
        logger.info("test passed");

        // find if enable is present
        WebElement enableButton= driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        String button3= enableButton.getText();
        logger.info("enable button is present");
        logger.info(button3);

        //click
        enableButton.click();
        Thread.sleep(5000);

        //find the disable button
        WebElement disableButton =driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));

        //get the text of the button
        String button4= disableButton.getText();
        logger.info(button4);

        // verify the button name is add

        Assert.assertEquals("Disable",button4);
        logger.info("disable button appeared");

        //write text in the box
        driver.findElement(By.xpath("//*[@id=\"input-example\"]/input")).sendKeys("Hello World!");

    }

    @After
    public void cleanup(){

        driver.close();
    }


    }
