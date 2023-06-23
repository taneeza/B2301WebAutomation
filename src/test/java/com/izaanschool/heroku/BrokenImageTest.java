package com.izaanschool.heroku;

import io.restassured.RestAssured;
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
import io.restassured.response.*;
import static io.restassured.RestAssured.given;

import java.util.List;

/**
 * Problem Statement
 * I want to go to Heroku app website and verify the broken image page is
 * working properly.
 * 1. So I want to the broken image page and find out all the broken image
 * 2.   //https://www.youtube.com/watch?v=8Gp9gl3CqCg
 * */

public class BrokenImageTest {

        public static Logger logger = LogManager.getLogger(BrokenImageTest.class);
        WebDriver driver;
        String url = "http://the-internet.herokuapp.com/";

        @Before
        public void beforeTest() {
            String driverPath = System.getProperty("user.dir") + "/driver/windows/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);

            driver = new ChromeDriver();

            driver.get(url);
            driver.manage().window().maximize();
            //Thread.sleep(5000);
        }

        @Test
        public void brokenImageTest() {
            //go to broken image page by clicking checkbox hyperlink from the homepage.
            driver.findElement(By.linkText("Broken Images")).click();

            //verify if we landed on broken image page or not by comparing the heading
            String h3 = driver.findElement(By.tagName("h3")).getText();
            logger.info(h3);
            String h3actual = "Broken Images";
            Assert.assertEquals(h3actual, h3);
            logger.info("test passed");




            //list all the images
            List<WebElement> imgList= driver.findElements(By.tagName("img"));
            logger.info(imgList.size());



            //find out the broken image by
            // getting the response code and image width

          for(int i=0;i<imgList.size();i++){
              Response response= RestAssured.given().contentType("application/json")
                      .get(imgList.get(i).getAttribute("src"));
              logger.info("print the response code"+response.getStatusCode());
              if(response.getStatusCode()==200){
                  if(imgList.get(i).getAttribute("naturalWidth").equals("0")) {
                      logger.info("the image is broken");
                  }else {
                      logger.info("the image is  not broken");

              }

              }

          }

        }

    @After
    public void cleanup(){
            driver.close();
    }

        }



