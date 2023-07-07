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
 * we want to navigate to dynamic content  page from herokuapp page and will see
 * if we locate the dynamic element from there
 * to do so,
 * 1. we will navigate to that page
 * 2.we will locate the a. image element and b. the text that will change in every refresh
 * 3.we will get the src attribute and the text
 * 4. we will click here button to refresh the page
 * 5. we will repeat step 2 and 3
 * we will compare the two src and text to verify that the elements got changed after every refresh.
 */

public class DynamicContentTest {
    public static Logger logger = LogManager.getLogger(DynamicContentTest.class);
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
    public void dynamicContentTest() throws InterruptedException {
        //go to Add/remove  page by clicking add/remove hyperlink from the homepage.
        driver.findElement(By.linkText("Dynamic Content")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3actual = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3actual);
        String h3expected = "Dynamic Content";
        Assert.assertEquals(h3expected, h3actual);
        logger.info("test passed");

        //finding the 3rd image and text
        WebElement img1= driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/img"));

        //get the src attribute
        String src1= img1.getAttribute("src");
        logger.info(src1);

        //get the text of the 3rd image
        String text1= driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]")).getText();

        logger.info(text1);

        //click the "click here" button to refresh the page

        driver.findElement(By.linkText("click here")).click();
        driver.findElement(By.linkText("click here")).click();

        //implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //finding the 3rd image and text
        WebElement img2= driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/img"));

        //get the src attribute
        String src2= img2.getAttribute("src");
        logger.info(src2);

        //get the text of the 3rd image
        String text2= driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]")).getText();

        logger.info(text1);

        // now verify by comparing src1 and src2

        if (src2!=src1 || text2!=text1){
            System.out.println("image or text or both got changed" + "   test past");
        }
        else {
            System.out.println("image or text not changed and test passed");
        }

    }

    @After
    public void addRemoveTest() {
        driver.close();

    }

    }
