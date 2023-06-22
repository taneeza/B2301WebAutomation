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

import java.util.List;


/** problem statement
 * we want to navigate to Add/Remove Elements page from herokuapp page and will see if the add
 * element buttonr working properly or not
 * to do so,
 * 1. we will navigate to that page
 * 2.we will add element
 * 3. we will check if the element has been added or not
 * 4. we will delete the element
 * 5. and then check if it was deleted or not.
 */

public class AddRemoveElementsTest {
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
    public void addRemoveElementTest() {
        //go to Add/remove  page by clicking add/remove hyperlik from the homepage.
        driver.findElement(By.linkText("Add/Remove Elements")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Add/Remove Elements";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");

        //click on add element
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();

        //verify if delete button appeared or not
        //isDisplayed() method returns boolean value either True or False
        Boolean display = driver.findElement(By.xpath("//*[@id=\"elements\"]/button")).isDisplayed();
        Assert.assertEquals(true, display);
        //driver.findElement(By.className("added-manually"));

        //remove delete button
        driver.findElement(By.className("added-manually")).click();

        //verify if the delete button is removed or not by using the same xpath
        //https://www.tutorialspoint.com/how-do-i-verify-that-an-element-does-not-exist-in-selenium-2#:~:text=We%20can%20verify%20if%20an,text%20of%20the%20element%20exists.

        List<WebElement> m = driver.findElements(By.xpath("//*[@id=\"elements\"]/button"));
        // verify size
        if (m.size() > 0) {
            System.out.println("Delete button: " + " is present. ");
        } else {
            System.out.println("Text: " + " is not present. ");
        }

    }
    /*@After
    public void addRemoveTest() {
        driver.close();

    }*/
}




