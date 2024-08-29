package demo;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        WebDriver driver;

        @FindBy(xpath = "//div[@id='guide-links-primary']//a[text()='About']")
        private WebElement about;

//        @FindBy(css = "section:nth-child(1) > p:nth-child(2)")
        @FindBy(xpath = "//section[@class='ytabout__content']/h1")
        private  WebElement abouttext;

        @FindBy(xpath = "//yt-formatted-string[normalize-space()='Movies']")
        private WebElement movies;

        @FindBy(xpath = "//span[normalize-space()='Movies']")
        private WebElement Moviesheader;

        @FindBy(xpath = "(//div[@id='contents'])[3]")
        private WebElement topsellingSection;

        @FindBy(xpath = "//span[contains(text(),'Top selling')]/ancestor::div[@id='dismissible']//button[@aria-label='Next']")
        private WebElement rightarrowfortopselling;

        @FindBy(xpath = "//span[contains(text(),'Top selling')]/ancestor::div[@id='dismissible']//ytd-grid-movie-renderer[last()]")
        private WebElement lastmovie;

        @FindBy(xpath = "//span[contains(text(),\"India's Biggest Hits\")]/ancestor::div[@id='dismissible']")
        private WebElement BestMusic;

        @FindBy(xpath = "//yt-formatted-string[normalize-space()='Music']")
        private WebElement Music;

    @FindBy(xpath = "//h1/yt-formatted-string[normalize-space()='Music']")
    private WebElement MusicHeader;

    @FindBy(xpath = "//yt-formatted-string[normalize-space()='News']")
    private WebElement News;

    @FindBy(xpath = "//span[text()='News']")
    private WebElement Newsheader;

    @FindBy(xpath = "//span[text()='Latest news posts']/ancestor::div[@id='dismissible']")
    private WebElement LatestNews;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement searchfield;

//    @FindBy(xpath = )


    @FindBy(xpath = "//span[text()='Filters']")
    private WebElement Filters;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

    @Test(enabled = true)
    public void testCase01() {
        System.out.println("Starting with testCase 01");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.youtube.com/");

        SoftAssert SA = new SoftAssert();
        SA.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/", "URL Did Not Match!");

        try {
            Wrappers.click(driver, about);
            wait.until(ExpectedConditions.visibilityOf(abouttext));

            if (abouttext.isDisplayed()) {
                String aboutsectiontext = abouttext.getText();
                System.out.println("Message on About us Page : " + aboutsectiontext);
                // Add an assertion for the content of the about section
                SA.assertTrue(!aboutsectiontext.isEmpty(), "About section text is empty!");
            } else {
                System.out.println("About section text not visible!");
                SA.fail("About section text is not visible.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            SA.fail("An exception occurred during the test: " + e.getMessage());
        }

        SA.assertAll(); // This is crucial to trigger the evaluation of all assertions
    }

        @Test(enabled = true)
        public void testCase02(){

                System.out.println("Starting with testCase 02");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.get("https://www.youtube.com/");

                try{

                        wait.until(ExpectedConditions.elementToBeClickable(movies));
                        movies.click();
                        System.out.println("Movies tab has been clicked");

                        wait.until(ExpectedConditions.visibilityOf(Moviesheader));
                        if(Moviesheader.isDisplayed()) {
                                System.out.println("Redirect to movies tab");
                        }
                        else{
                                System.out.println("Not redirect to movies tab");

                        }

//                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topsellingSection);
                        wait.until(ExpectedConditions.elementToBeClickable(rightarrowfortopselling));
                        System.out.println("Right arrow is displayed");
                        while (rightarrowfortopselling.isDisplayed()) {
                        Wrappers.click(driver, rightarrowfortopselling);
                    }
                    SoftAssert SA = new SoftAssert();
                    WebElement Maturity = lastmovie.findElement(By.xpath(".//div[contains(@class,'badge-style-type-simple')]//p[contains(@class,'style-scope ytd-badge-supported-renderer')]"));
                    String MaturiyText = Maturity.getText();
                    System.out.println("Maturity Rating: "+MaturiyText);
                    SA.assertTrue(MaturiyText.contains("A"),"Maturity rating is not 'A' for Mature.");

                    WebElement MovieCategory = lastmovie.findElement(By.xpath(".//a[contains(@class,'yt-simple-endpoint')]//span[contains(@class,'grid-movie-renderer-metadata')]"));
                    String MovieCategoryText = MovieCategory.getText().split(" ")[0].trim();
                    System.out.println("MovieCategory:" +MovieCategoryText);
                    SA.assertTrue(MovieCategoryText.matches("Comedy|Animation|Drama"),"Movie category does not match expected values.");

                    //SA.assertAll();







                }catch (Exception e){
                        e.printStackTrace();
                }

        }

    @Test(enabled = true)
    public void testCase03() {

        System.out.println("Starting with testCase 03");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.youtube.com/");
        try {
            //  //span[contains(text(),"India's Biggest Hits")]/ancestor::div[@id='dismissible']//button[@aria-label="Next"]
            wait.until(ExpectedConditions.elementToBeClickable(Music));
            Music.click();
            System.out.println("Music tab has been clicked");

            wait.until(ExpectedConditions.visibilityOf(MusicHeader));
            if(MusicHeader.isDisplayed()) {
                System.out.println("Redirect to music tab");
            }
            else{
                System.out.println("Not redirect to music tab");

            }

            WebElement RightArrow = BestMusic.findElement(By.xpath(".//button[@aria-label='Next']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", BestMusic);

            while ((RightArrow.isDisplayed())){
                Wrappers.click(driver,RightArrow);
            }

            SoftAssert SA = new SoftAssert();

            WebElement lastMusicalbum = BestMusic.findElement(By.xpath(".//ytd-compact-station-renderer[last()]"));

            WebElement Playlist = lastMusicalbum.findElement((By.xpath(".//h3")));

            String Playlistname = Playlist.getText();

            System.out.println("Playlist Name: "+Playlistname);

            WebElement videocount = lastMusicalbum.findElement(By.xpath(".//p[@id='video-count-text']"));

            String videocounttext = videocount.getText().replaceAll("[^0-9]","");

            int count = Integer.parseInt(videocounttext);

            System.out.println("Track count :" +count);
            SA.assertTrue(count<=50,"Number of track count is greater than 50");
            SA.assertAll();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test(enabled = true)
    public void testCase04() {

        System.out.println("Starting with testCase 04");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.youtube.com/");
        try {
            //  //span[contains(text(),"India's Biggest Hits")]/ancestor::div[@id='dismissible']//button[@aria-label="Next"]
            wait.until(ExpectedConditions.elementToBeClickable(News));
            News.click();
            System.out.println("Movies tab has been clicked");

            wait.until(ExpectedConditions.visibilityOf(Newsheader));
            if(Newsheader.isDisplayed()) {
                System.out.println("Redirect to news tab");
            }
            else{
                System.out.println("Not redirect to news tab");

            }

            int totallikes = 0;

            List<WebElement> LatesNewslist = LatestNews.findElements(By.xpath(".//ytd-rich-item-renderer/div[@id='content']"));
            for(int i=0;i<Math.min(3,LatesNewslist.size());i++){
                WebElement NewsSection = LatesNewslist.get(i);

                WebElement titleElement = NewsSection.findElement(By.xpath(".//div[@id='author']"));
                System.out.println("Title"+(i+1)+" :"+titleElement.getText());


                WebElement bodyElement = NewsSection.findElement(By.xpath(".//yt-formatted-string[@id='home-content-text']"));
                System.out.println("Body"+(i+1)+" :"+bodyElement.getText());

                WebElement LikeELement = NewsSection.findElement(By.xpath(".//span[@id='vote-count-middle']"));
                String LikesText = LikeELement.getText();
                int like = Wrappers.convertLikesToNumber(LikesText);
                totallikes += like;

            }

            System.out.println("Total Likes Count for 1st 3 Latest News Post : " + totallikes);




        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test(enabled = true, dataProvider = "excelData")
    public void testCase05(String data) throws InterruptedException {

        System.out.println("Starting with testCase 05");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.youtube.com/");

        Wrappers.sendkeys(driver, searchfield, data);
        searchfield.sendKeys(Keys.RETURN);

        wait.until(ExpectedConditions.visibilityOf(Filters));

        Thread.sleep(2000);

        int totalviews =0;
        int viewsthreashold = 10000000;
        while (totalviews<viewsthreashold){
            List<WebElement> VideoElements = driver.findElements(By.xpath("//ytd-video-renderer"));

            for (WebElement elem : VideoElements) {

                WebElement viewselement = elem.findElement(By.xpath(".//span[contains(text(),'views')]"));
                String viewtext = viewselement.getText();
                int views = Wrappers.convertLikesToNumber(viewtext);

                totalviews += views;
                if (totalviews >= viewsthreashold) {
                    break;
                }
            }
                if(totalviews<viewsthreashold){
                    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
                    Thread.sleep(2000);


            }
        }
        System.out.println("Total views for search item " +data+ ":" +totalviews);



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
                driver.close();
                driver.quit();

        }
}