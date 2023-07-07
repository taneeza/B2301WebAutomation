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

import java.util.concurrent.TimeUnit;


/** problem statement
 * we want to navigate to the  Notification messages page from herokuapp page and will see if
 * we can see the notification message on
 * and click on  the "click here" button and see if
 * and the mmessage get changed or not.

 */

public class NotificationMessagesTest {
    public static Logger logger = LogManager.getLogger(NotificationMessagesTest.class);
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
    public void notificationMessageTest() {
        //go to the  page by clicking  hyperlink from the homepage.
        driver.findElement(By.linkText("Notification Messages")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Notification Message";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        // find the notification message

        WebElement ele= driver.findElement(By.xpath("//*[@id=\"flash\"]"));
        String actualMessage= ele.getText();
        System.out.println( actualMessage );

        // click the "click here" button

        WebElement ele2= driver.findElement(By.linkText("Click here"));
        ele2.click();

       // to see if the message is changed or not.
        WebElement ele3= driver.findElement(By.xpath("//*[@id=\"flash\"]"));
        System.out.println( ele3);


    }
    @After
    public void cleanup(){

        driver.close();
    }


}
