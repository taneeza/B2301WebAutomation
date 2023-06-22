package com.izaanschool.heroku;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Problem Statement
 * I want to go to Heroku app website and see if I can find different elements using 8 types of locators.

 * */

public class LocatorTest {
    public static Logger logger= LogManager.getLogger(LocatorTest.class);

    public static void main(String[] args) {
        String url = "http://the-internet.herokuapp.com/";

        String driverPath= System.getProperty("user.dir")+"/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver",driverPath);
        WebDriver driver= new ChromeDriver();

        // implicit wait is the maximum time set between two steps/commands of the automation script.
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        driver.get(url);

        driver.manage().window().maximize();

        String title1= driver.getTitle();
        logger.info("title   " + title1);


        String headingH1 = driver.findElement(By.tagName("h1")).getText();
        logger.info(headingH1);


        String id = driver.findElement(By.id("content")).getText();
        logger.info("id  " + id);


        String ul=driver.findElement(By.tagName("ul")).getText();
        logger.info("ul:   " + ul);

        String id2= driver.findElement(By.id("page-footer")).getText();
        logger.info(" id2:  "+ id2);

        WebElement webelement=driver.findElement(By.tagName("h2"));
        String h2= webelement.getText();
        logger.info(webelement);
        logger.info(h2);


        String li= driver.findElement(By.tagName("li")).getText();
        logger.info(li);

        List<WebElement> listElement=driver.findElements(By.tagName("li"));
        for(int i=0; i< listElement.size();i++) {
            String listValue = listElement.get(i).getText();
            logger.info(listValue);
        }

        WebElement img=driver.findElement(new By.ByTagName("img"));
        //String linkValue=listLinks.get(0).getText();
        logger.info( "Links:  "+ img);

        WebElement tempImg = driver.findElement(By.cssSelector("img[src=\"/img/forkme_right_green_007200.png\"]"));
        String img1= tempImg.getText();
        logger.info( img1);

        driver.close();


    }

}

//String a= driver.findElement(By.tagName("title")).getText(); //dindt work
//logger.info(a);
//System.out.println("a" + a);
// List<WebElement> elementList= driver.findElements(new By.ByTagName("title"));
// String aValue = elementList.get(0).getText();
//logger.info(aValue);

// logger.info(heading);
// logger.info(id);
