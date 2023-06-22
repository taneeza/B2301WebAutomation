package com.izaanschool.heroku;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Problem Statement
 * I want to go to Heroku app website and verify the checkbox page check boxes are
 * working properly.
 * 1. So I want to check the box and verify that box is checked
 * 2. I also want to uncheck a box and verify the box is unchecked
 * */

public class CheckBoxesPageTest {
    public static Logger logger= LogManager.getLogger(CheckBoxesPageTest.class);
    WebDriver driver;
    String url=  "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest(){
        String driverPath= System.getProperty("user.dir")+ "/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver",driverPath);

        driver= new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void checkBoxTest(){
        //go to checkbox page by clicking checkbox hyperlink from the homepage.
        driver.findElement(By.linkText("Checkboxes")).click();

        //verify if we landed on checkbox page or not by comparing the heading
       String h3= driver.findElement(By.tagName("h3")).getText();
       logger.info(h3);
       String h3actual="Checkboxes";
       Assert.assertEquals(h3actual,h3);
       logger.info("test passed");


       // check one checkbox
        driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
        boolean actualStatus= driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).isSelected();
        Assert.assertEquals(true,actualStatus);
        logger.info("Test passed");
    }

    @After
    public void cleanup(){
        driver.close();
    }



}
