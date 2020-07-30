package ua.com.rozetka.helpers;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Checks {

   private WebDriverWait wait;
   public final int DEFAULT_EXPLICIT_WAIT_TIME = 10;

   public Checks(WebDriver wd) {
      wait = new WebDriverWait(wd, DEFAULT_EXPLICIT_WAIT_TIME);
   }

   public boolean isDisplayed(String... locator) {
      try {
         for (String l : locator) {
            wait.until(visibilityOfElementLocated(xpath(l)));
         }
         return true;
      } catch (ElementNotVisibleException | TimeoutException e) {
         e.printStackTrace();
         return false;
      }
   }

   public boolean isStaleness(WebElement... locator) {
      try {
         for (WebElement l : locator) {
            wait.until(stalenessOf(l));
         }
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   public boolean isClickable(WebElement locator) {
      try {
         wait.until(elementToBeClickable(locator));
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   public boolean presentOnTheDOM(String... locator) {
      try {
         for (String l : locator) {
            wait.until(presenceOfElementLocated(xpath(l)));
         }
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   public void setExplicitWaitBySeconds(int seconds) {
      wait.withTimeout(Duration.ofSeconds(seconds));
   }
}