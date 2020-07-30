package ua.com.rozetka;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ua.com.rozetka.helpers.Checks;
import ua.com.rozetka.helpers.KeyboardHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

public abstract class Driver {

   protected WebDriver wd;
   protected Checks checkThat;
   protected Actions action;
   protected KeyboardHelper keyboard;
   protected final int DEFAULT_IMPLICIT_WAIT_TIME = 10;

   public Driver(WebDriver wd) {
      this.wd = wd;
      checkThat = new Checks(wd);
      action = new Actions(wd);
      keyboard = new KeyboardHelper();
      wd.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
   }

   public void open(String URL) {
      wd.get(URL);
   }

   public WebElement find(String locator) {
      return wd.findElement(xpath(locator));
   }

   public List<WebElement> findAll(String locator) {
      return wd.findElements(xpath(locator));
   }

   public void click(String locator) {
      find(locator).click();
   }

   public void enterText(String text, String fieldLocator) {
      WebElement field = find(fieldLocator);
      field.clear();
      field.sendKeys(text);
   }

   public void setImplicitWaitBySeconds(int seconds) {
      wd.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
   }

   public void clickJSE(String locator) {
      WebElement element = find(locator);
      JavascriptExecutor executor = (JavascriptExecutor) wd;
      executor.executeScript("arguments[0].click();", element);
   }

   public void sleep(long ms) {
      try {
         Thread.sleep(ms);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}