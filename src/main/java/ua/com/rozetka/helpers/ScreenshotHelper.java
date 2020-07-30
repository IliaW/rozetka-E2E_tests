package ua.com.rozetka.helpers;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {

   public void takeScreenshot(WebDriver wd) {
      File tempFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
      File screen = new File("src/main/resources/screenshots", "Screen_" + System.currentTimeMillis() + ".png");
      try {
         Files.copy(tempFile, screen);
         saveScreenshot(screen);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   // Adding screenshots to allure report.
   @Attachment(value = "Page screenshot", type = "image/png")
   private static byte[] saveScreenshot(File file) throws IOException {
      return Files.toByteArray(file);
   }
}