import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Utils;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutomateWebform {
    WebDriver driver;

    @BeforeAll
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName("Check if the webform is submitted successfully")
    @Test
    public void automateForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click(); //accept all cookies
        List<WebElement> formElement = driver.findElements(By.className("form-control"));
        formElement.get(0).sendKeys("Omar faruq"); //name
        formElement.get(1).sendKeys("01787197666"); //phone number
        driver.findElement(By.className("ui-checkboxradio-label")).click(); //age
        String currentDate = java.time.LocalDate.now().toString(); //fetch current date
        String s[];
        s = currentDate.split("-");
        driver.findElement(By.id("edit-date")).sendKeys(s[1]); //date
        driver.findElement(By.id("edit-date")).sendKeys(s[1]); //month
        driver.findElement(By.id("edit-date")).sendKeys(Keys.ARROW_RIGHT);
        driver.findElement(By.id("edit-date")).sendKeys(s[0]); //year
        Thread.sleep(1000);
        Utils.scroll(driver,0,600);
        Thread.sleep(3000);
        formElement.get(3).sendKeys("faruq@gmail.com"); //email
        formElement.get(4).sendKeys("I am a SQA Engineer in genex infosys limited"); //textbox
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir")+"/src/test/resources/faruq cv pic.jpg"); //upload document or image
        Thread.sleep(10000);
        driver.findElement(By.xpath("//input[@id='edit-age']")).click(); //checkbox
        driver.findElement(By.id("edit-submit")).click(); //submit button
        Thread.sleep(2000);
        String submitActualText = driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(submitActualText.contains("Thank you for your submission!"));
    }

    @AfterAll
    public void quitBrowser(){
        driver.quit();
    }

}
