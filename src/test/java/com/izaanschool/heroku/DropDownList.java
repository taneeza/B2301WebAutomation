package com.izaanschool.heroku;

import org.openqa.selenium.support.ui.Select;


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
 * we want to navigate to drop down list Elements page from herokuapp page and will see if we can select
 * an option from there.
 * to do so,
 * 1. we will navigate to that page
 * 2.we will select option1 from there
 * 3. we will verify if option 1 was selected or not

 */
// //https://www.guru99.com/select-option-dropdown-selenium-webdriver.html

public class DropDownList {

    public static Logger logger = LogManager.getLogger(DropDownList.class);
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
    public void dropDownTest() {
        //go to Add/remove  page by clicking add/remove hyperlik from the homepage.
        driver.findElement(By.linkText("Dropdown")).click();

        //verify if we landed on the page or not by comparing the heading
        String h3 = driver.findElement(By.tagName("h3")).getText();
        logger.info(h3);
        String h3actual = "Dropdown List";
        Assert.assertEquals(h3actual, h3);
        logger.info("test passed");


        //Declare the drop-down element as an instance of the Select class.
        Select drpDownList = new Select(driver.findElement(By.id("dropdown")));


        // choose option 1
        drpDownList.selectByVisibleText("Option 1");

        //select by index number
        //drpDownList.selectByIndex(0);

        //verify
        String actualValue=   drpDownList.getFirstSelectedOption().getText();
        Assert.assertEquals("Option 1", actualValue);

        logger.info("test passed");

    }
   @After
    public void addRemoveTest() {
        driver.close();

   }

}
