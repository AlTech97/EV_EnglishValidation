package test;



import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ErrorFormatSurname {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", "geckodriver//geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testErrorFormatSurname() throws Exception {
    driver.get("http://localhost:8080/EnglishValidation/");
    driver.findElement(By.linkText("Registrati")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Andrea");
    driver.findElement(By.id("surname")).click();
    driver.findElement(By.id("surname")).clear();
    driver.findElement(By.id("surname")).sendKeys("Iannaccone1");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("z.prov@studenti.unisa.it");
    driver.findElement(By.id("verifyPassword")).click();
    driver.findElement(By.id("verifyPassword")).clear();
    driver.findElement(By.id("verifyPassword")).sendKeys("password");
    driver.findElement(By.name("sex")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='F'])[1]/following::button[1]")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
