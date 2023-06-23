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
import java.util.concurrent.TimeUnit;


/** problem statement
 * we want to navigate to basic auth Elements page from herokuapp page and
 * will see if the basic auth page works or not.
 * we will pass username and password
 * and verify if we have be authorised and
 * verify if we landed on the page or not by comparing the heading

 */


public class BasicAuthTest {

        public static Logger logger = LogManager.getLogger(BasicAuthTest.class);
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
    public void basicAuthTest(){

            driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");


            //verify if we landed on the page or not by comparing the heading
         String h3actual = driver.findElement(By.tagName("h3")).getText();
           logger.info(h3actual);
          String h3expect = "Basic Auth";
           Assert.assertEquals(h3expect, h3actual);
           logger.info("test passed");
       }
    @After
    public void cleanup(){
            driver.close();
    }

}
