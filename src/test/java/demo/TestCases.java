package demo;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        WebDriver driver;

        @FindBy(xpath = "//div[@id=\"guide-links-primary\"]//a[text()='About']")
        private WebElement about;

        @FindBy(css = "section:nth-child(1) > p:nth-child(2)")
        private  WebElement abouttext;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        @Test
        public void testCase01(){
                System.out.println("Starting with testCase 01");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


                driver.get("https://www.youtube.com/");

                SoftAssert SA = new SoftAssert();
                SA.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/","URL Did Not Match!");
                try{
                        Wrappers.click(driver, about);

                        wait.until(ExpectedConditions.visibilityOf(abouttext));
                        String aboutsectiontext = abouttext.getText();
                        System.out.println("Message on About us Page : " +aboutsectiontext);


                }catch (Exception e){
                        e.printStackTrace();
                }

                SA.assertAll();

        }

        @Test
        public void testCase02(){

        }

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
                PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
        }

        @AfterTest
        public void endTest() {
//                driver.close();
//                driver.quit();

        }
}