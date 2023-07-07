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

import java.io.File;
import java.util.concurrent.TimeUnit;


/** problem statement
 * we want to navigate to file Upload Elements page from herokuapp page and
 * will see if we upload a file from our local machine
 * to do so,
 * 1. we will navigate to that page
 * 2.we will choose a file from my local machine and upload
 * 3. we will check if the file has been uploaded or not

 */


public class FileUploadTest {
    public static Logger logger = LogManager.getLogger(FileUploadTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest() {
        String driverPath = System.getProperty("user.dir") + "/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();

    }

    @Test
    public void fileUploadTest(){

        //go to upload file  page by clicking  hyperlik from the homepage.
        driver.findElement(By.linkText("File Upload")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "File Uploader";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        //find the upload button
        WebElement fileUpload =driver.findElement(By.xpath("//*[@id=\"file-upload\"]"));

       //choose file from local my local machine
        File file = new File("wp3.jpg");
        //System.out.println(file.getAbsolutePath());
        fileUpload.sendKeys(file.getAbsolutePath());

        //find the click button and click
        driver.findElement(By.xpath("//*[@id=\"file-submit\"]")).click();

        //assertion that upload is completed
        String heading = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String headingActual ="File Uploaded!";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");


    }
    /*@After
    public void cleanup(){
        driver.close();
    }*/

}
