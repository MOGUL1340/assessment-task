import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AssessmentTask {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //----Prerequisites----
        //This test case uses Chrome as a browser.
        //To make this test work, you need to specify correct folder for appropriate version of chromedriver file
        //Chromedriver version depends on a version of your Chrome browser
        //You can download the chromedriver here https://chromedriver.chromium.org/home
        //To use account credentials from values below you need to have your IP in range from '109.68.40.0' to '109.68.47.255'

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void assessmentTask() throws InterruptedException {

        //First part of the task
        //Open login page
        driver.get("https://login.salesforce.com/");

        //Wait until login button visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/input[2]")));

        //Input login/pass and press Login button
        driver.findElement(By.name("username")).sendKeys("antonioua1340+trial_forse_1-jkkq@force.com");
        driver.findElement(By.name("pw")).sendKeys("xmhY2g2O-1");
        driver.findElement(By.name("Login")).click();

        //Wait until log-in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[3]/span/div[2]/ul/li[9]" +
                "/span/button/div/span[1]/div/span")));

        //Verify that user logged-in
        Assert.assertTrue(driver.findElement(By.xpath("//header/div[3]/span/div[2]/ul/li[9]" +
                "/span/button/div/span[1]/div/span")).isDisplayed());

        //Second part of the task
        //Open "Chatter" tab
        driver.get("https://eu31.lightning.force.com/lightning/page/chatter");

        //Wait until "Chatter" page displayed (bt visibility of "Top posts" element)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[1]/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/ul/li[3]/a")));

        //Click on "Poll" sub-tub
        driver.findElement(By.xpath("//div/div[1]/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/ul/li[3]/a")).click();

        //Wait until "Poll" page visible (by visibility of "Question" text field element)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section/div/div/div/div/div/div[1]/div/textarea")));

        //Input poll title text
        driver.findElement(By.xpath("//section/div/div/div/div/div/div[1]/div/textarea")).sendKeys("Poll Title");
        //Input Choice#1 text message
        driver.findElement(By.xpath("//section/div/ul/li[1]/div/div/input")).sendKeys("Choice #1");
        //Input Choice#2 text message
        driver.findElement(By.xpath("//section/div/ul/li[2]/div/div/input")).sendKeys("Choice #2");

        //Press on "Ask" button to create a poll
        driver.findElement(By.xpath("//section[2]/div/div[3]/div/div/div/div[2]/div[2]/button")).click();
    }

    @After
    public void stop() {
        driver.quit();
    }
}
