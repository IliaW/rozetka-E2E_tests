package ua.com.rozetka;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.com.rozetka.helpers.ScreenshotHelper;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class App {

   public WebDriver wd;
   public String browser;
   public ScreenshotHelper screen;

   public WebDriver getDriver() {
      if (wd == null) {
         if (FIREFOX.equals(browser)) {
            wd = new FirefoxDriver();
         } else {
            wd = new ChromeDriver();
         }
         wd.manage().window().maximize();
         screen = new ScreenshotHelper();
      }
      return wd;
   }

   public void stop() {
      if (wd != null) {
         wd.quit();
         wd = null;
      }
   }
}