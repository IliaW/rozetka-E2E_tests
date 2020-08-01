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

   private final WebDriverWait wait;

   public Checks(WebDriver wd, long timeOut) {
      wait = new WebDriverWait(wd, timeOut);
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

   public boolean isClickable(String locator) {
      try {
         wait.until(elementToBeClickable(xpath(locator)));
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

   public void setExplicitWaitBySeconds(long seconds) {
      wait.withTimeout(Duration.ofSeconds(seconds));
   }
}