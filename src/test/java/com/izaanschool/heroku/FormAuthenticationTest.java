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

import java.util.concurrent.TimeUnit;

/** problem statement
 * we will navigate to the form authentication page
 * we will pass username and password
 * and see if I can login or not
 */

public class FormAuthenticationTest {
    public static Logger logger = LogManager.getLogger(FormAuthenticationTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest() {
        String driverPath = System.getProperty("user.dir") + "/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        // implicit wait is the maximum time set between two steps/commands of the automation script.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void formAuthenticationTest() throws InterruptedException {
        //go to Add/remove  page by clicking add/remove hyperlik from the homepage.
        driver.findElement(By.linkText("Form Authentication")).click();

        //verify if we landed on the page or not by comparing the heading
        String h2 = driver.findElement(By.tagName("h2")).getText();
        logger.info(h2);
        String h3actual = "Login Page";
        Assert.assertEquals(h3actual, h2);
        logger.info("test passed");

        //https://www.browserstack.com/guide/login-automation-using-selenium-webdriver


        // Right click the button to launch right click menu options
        Actions action = new Actions(driver);

        //find the username box
        WebElement username= driver.findElement(By.xpath("//*[@id=\"username\"]"));
        WebElement password= driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement login=driver.findElement(By.xpath("//*[@id=\"login\"]/button/i"));
        Thread.sleep(3000);
        username.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword!");
        login.click();
        Thread.sleep(5000);


        //verify you are looged in
        boolean actualStatus=driver.findElement(By.id("flash")).isDisplayed();

        Assert.assertEquals(true,actualStatus);

        String flashMessage= driver.findElement(By.id("flash")).getText();
        logger.info( "Flash Message says  :"+ flashMessage);


    }
    @After
    public void cleanup(){

        driver.close();
    }

}
