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

import java.util.List;
import java.util.concurrent.TimeUnit;
/** problem statement
 * we want to navigate to the  hover page page from herokuapp page and will see if
 * we can hover over the pictures by mouse action
 * and click on  one of the  picture
 * and go to its profile page in the same window.

 */

public class HoversTest {
    public static Logger logger = LogManager.getLogger(HoversTest.class);
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
    public void hoversForProfile1Test() {
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Hovers")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Hovers";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        // make action
        Actions act = new Actions(driver);


        // find the elements
        WebElement ele= driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));

        //hover over action
        act.moveToElement(ele).perform();

        // click on the profile
        WebElement ele2=driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
        ele2.click();

       // Assert that we landed on the next page
       boolean actualStatus= driver.findElement(By.xpath("/html/body/h1")).isDisplayed();
        Assert.assertEquals(true,actualStatus);

        logger.info("test passed");




      /*  // find the elements
       List<WebElement> figureList = driver.findElements(By.className("figure"));
        System.out.println(figureList.size());
        WebElement e=driver.findElement(By.linkText("View profile"));  //cant find the element
        System.out.println(e.getText());
        List<WebElement> ele3=driver.findElements(By.linkText("View profile"));
        System.out.println(ele3.size());

        for (int i = 0; i < figureList.size(); i++) {
            act.moveToElement(figureList.get(i)).perform();
            ele3.get(i).click();
        }*/
    }
    @After
    public void cleanup(){
        driver.close();
    }
}