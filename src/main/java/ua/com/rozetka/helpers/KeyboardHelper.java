package ua.com.rozetka.helpers;

import com.sun.glass.events.KeyEvent;

import java.awt.*;

public class KeyboardHelper {

   private Robot robot;

   public KeyboardHelper() {
      try {
         robot = new Robot();
         robot.setAutoDelay(500);
      } catch (AWTException e) {
         e.printStackTrace();
      }
   }

   public void pressTAB() {
      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_TAB);
   }
}