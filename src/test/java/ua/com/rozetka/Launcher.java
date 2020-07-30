package ua.com.rozetka;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class Launcher {

   public App app;

   @Parameters("browser")
   @BeforeSuite
   public void setUp(String browser) {
      app = new App();
      app.browser = browser;
   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() {
      app.stop();
   }
}