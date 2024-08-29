package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    WebDriver driver;

    public static boolean click(WebDriver driver, WebElement element){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Actions act = new Actions(driver);
            act.moveToElement(element);
            Thread.sleep(5000);
            act.click(element).perform();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            try{
                element.click();
                return true;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }


    public static boolean sendkeys(WebDriver driver, WebElement inputbox, String keystosend){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(inputbox));
        try {
            inputbox.sendKeys(keystosend);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }


    public static int convertLikesToNumber(String likesText) {
        likesText = likesText.toLowerCase().replaceAll("[^0-9kmb.]", "");

        if (likesText.endsWith("k")) {
            return (int) (Double.parseDouble(likesText.replace("k", "")) * 1000);
        } else if (likesText.endsWith("m")) {
            return (int) (Double.parseDouble(likesText.replace("m", "")) * 1000000);
        } else if (likesText.endsWith("b")) {
            return (int) (Double.parseDouble(likesText.replace("b", "")) * 1000000000);
        } else {
            return Integer.parseInt(likesText);
        }
    }
}
