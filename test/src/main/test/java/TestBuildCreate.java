/**
 * Created by Bublik on 04.06.2016.
 */


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestBuildCreate {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

   // @Test
    public void testCreateBuild() throws Exception {
        driver.get(baseUrl + "testTask/login");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("q");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("q");
        driver.findElement(By.name("submit")).click();
        //падает на этом
        driver.findElement(By.cssSelector("#gwt-uid-11 > button.gwt-Button")).click();
        driver.findElement(By.name("Название")).clear();
        driver.findElement(By.name("Название")).sendKeys("Дом");
        driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
        driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Адрес");
        driver.findElement(By.cssSelector("input.gwt-DateBox")).clear();
        driver.findElement(By.cssSelector("input.gwt-DateBox")).sendKeys("02-06-2016");
        new Select(driver.findElement(By.cssSelector("select.gwt-ListBox"))).selectByVisibleText("бревно");
        driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
        driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("2");
        driver.findElement(By.cssSelector("td > button.gwt-Button")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
