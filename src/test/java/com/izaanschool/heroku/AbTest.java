
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


/**  * Problem Statement
         *I want to go to Heroku app website and verify if we can navigate to the A/B page
        * 1. we will verify the heading of the page
         */




public class AbTest {
      public static Logger logger = LogManager.getLogger(AbTest.class);
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
      public void abTest() {
            //go to Add/remove  page by clicking add/remove hyperlik from the homepage.
            driver.findElement(By.linkText("A/B Testing")).click();

            //verify if we landed on the page or not by comparing the heading
            String h3actual = driver.findElement(By.tagName("h3")).getText();
            logger.info(h3actual);
            String h3expect = "A/B Test Variation 1";
            Assert.assertEquals(h3expect, h3actual);
            logger.info("test passed");
      }
}
