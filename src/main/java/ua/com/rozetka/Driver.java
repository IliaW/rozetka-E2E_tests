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
   private JavascriptExecutor jse;
   protected final int DEFAULT_IMPLICIT_WAIT_TIME = 10;
   protected final int DEFAULT_EXPLICIT_WAIT_TIME = 10;

   public Driver(WebDriver wd) {
      this.wd = wd;
      checkThat = new Checks(wd, DEFAULT_EXPLICIT_WAIT_TIME);
      action = new Actions(wd);
      keyboard = new KeyboardHelper();
      jse = (JavascriptExecutor) this.wd;
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

   public void clickJSE(String locator) {
      jse.executeScript("arguments[0].click();", find(locator));
   }

   public void enterText(String text, String fieldLocator) {
      WebElement field = find(fieldLocator);
      field.clear();
      field.sendKeys(text);
   }

   public void enterTextJSE(String text, String fieldLocator) {
      String arg = String.format("arguments[0].value='%s';", text);
      jse.executeScript(arg, find(fieldLocator));
   }

   public void scrollToElement(String locator) {
      jse.executeScript("arguments[0].scrollIntoView();", find(locator));

   }

   public void setImplicitWait(long seconds) {
      wd.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
   }

   public void setExplicitlyWait(long seconds) {
      checkThat.setExplicitWaitBySeconds(seconds);
   }


   public void sleep(long ms) {
      try {
         Thread.sleep(ms);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}