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

import java.time.Duration;


/** problem statement
 * we want to navigate to Drag And Drop Elements page from herokuapp page and will see if
 * the drag and drop action works porperly or not
 * to do so,
 * 1. we will navigate to that page
 * 2.we will drag element A and release it on element B.
 * 3. we will check if the A element has been moved to element B and vice versa.

 */

public class DragAndDrop {
    public static Logger logger = LogManager.getLogger(CheckBoxesPageTest.class);
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
    public void dragAndDropTest() {
        //go to Add/remove  page by clicking add/remove hyperlik from the homepage.
        driver.findElement(By.linkText("Drag and Drop")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Drag and Drop";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        //Actions class method to drag and drop
        Actions builder = new Actions(driver);

        //find element that will be dragged from and dragged to
        WebElement from = driver.findElement(By.xpath("//*[@id=\"column-a\"]"));
        WebElement to = driver.findElement(By.xpath("//*[@id=\"column-b\"]"));


        int x = from.getLocation().x;
        int y = to.getLocation().y;

        Actions actions = new Actions(driver);
        actions.moveToElement(from)
                .pause(Duration.ofSeconds(1))
                .clickAndHold(from)
                .pause(Duration.ofSeconds(1))
                .moveByOffset(x, y)
                .moveToElement(to)
                .moveByOffset(x,y)
                .pause(Duration.ofSeconds(1))
                .release().build().perform();

        //Perform drag and drop

        //builder.dragAndDrop(from,to).build().perform();
        String textTo = driver.findElement(By.xpath("//*[@id=\"column-b\"]/header")).getText();
        logger.info(textTo);

    }

    /*@After
    public void addRemoveTest() {
        driver.close();

    }*/

}
