import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Assignment {
    WebDriver driver;
    @BeforeAll
    public void setup(){

        driver = new ChromeDriver();
        driver.manage().window().minimize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void submit() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

         WebElement acceptCookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
      acceptCookies.click();
         driver.findElement(By.id("edit-name")).sendKeys("MdMithunAli");
       driver.findElement(By.id("edit-number")).sendKeys("01743875092");
        Utils.scroll(driver,0,250);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.findElements(By.className("ui-checkboxradio-label")).get(0).click();;
        WebElement date=   driver.findElement(By.id("edit-date"));
       driver.findElement(By.id("edit-email")).sendKeys("mithun1234@gamil.com");
        driver.findElement(By.cssSelector("textarea")).sendKeys("this is demo input");
        WebElement uploadFile=  driver.findElement(By.id("edit-uploadocument-upload"));
        uploadFile.sendKeys(System.getProperty("user.dir")+"./src/test/resources/img/ipts.jpg");
        Utils.scroll(driver,0,500);

        WebElement checkBox = driver.findElement(By.cssSelector("[type=checkbox]"));
        checkBox.click();
        WebElement submit = driver.findElement(By.id("edit-submit"));
        submit.click();
        Thread.sleep(200000);
        driver.navigate().refresh();


       date.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
       LocalDate currentDate = LocalDate.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
       String formattedDate = currentDate.format(formatter);
       date.sendKeys(formattedDate);
        date.sendKeys(Keys.ENTER);


  WebElement successMessage = driver.findElement(By.id("block-pagetitle-2"));
     assertEquals("Thank you for your submission!", successMessage.getText());
   }
    @AfterAll
    public void quitDriver(){
     // driver.quit();
   }
}
