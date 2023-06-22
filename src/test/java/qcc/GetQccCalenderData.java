package qcc;

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

public class GetQccCalenderData {

    /**
     * 1. Go to Queens College website
     * 2. Get the calendar information
     * 3. Find out if there is any special event for today
     * 4. Notify Nazrul about the information via email
     * */
    private static final Logger logger = LogManager.getLogger(GetQccCalenderData.class);
    WebDriver driver;
    String url = "https://www.qcc.cuny.edu/academics/academic-calendars.html";

        // Get the current System Properties
        // String systemProperties = System.getProperties().toString();
        //logger.info(systemProperties);


        @Before
        public void beforeTest() {
            String driverPath = System.getProperty("user.dir") + "/driver/windows/chromedriver";

            // Let's show the application where is the driver object
            System.setProperty("webdriver.chrome.driver", driverPath);

            driver = new ChromeDriver(); // Chrome Browser Object


            // Open a new Chrome Window and browse to the website using URL
            driver.get(url);


            // Make my Chrome window little look good by maximizing the size
            driver.manage().window().maximize();

        }



        @Test
        public void getCalenderDataTest(){
            // At first find the h2 element location, and now give me value/tag-value from h2 tag
        String heading1=driver.findElement(By.tagName("h2")).getText();

            //String heading = driver.findElement(new By.ByTagName("h2")).getText();
        ;
        String expectedValue = "Spring 2023 (1/25/2023 – 5/23/2023)";
            // Hard Assertion
        Assert.assertEquals(expectedValue,heading1);
        logger.info("test passsed");
        }
        //Assert.assertEquals(expectedValue, heading1);
       /* String actualValue=
            Assert.*/
        @Test
        public void allH2HeadingTest(){
        List<WebElement> webElementList = driver.findElements(new By.ByTagName("h2"));

            String actualH2Value = webElementList.get(1).getText();
            logger.info(actualH2Value);
            String h2Value= "Summer 2023 (5/30/2023 – 8/18/2023)";
            Assert.assertEquals(actualH2Value,h2Value);
            logger.info("test passed");
        }

        // My Job is done. It's time to close the window
    @After
    public void cleanup() {
        driver.close();
    }
    }

