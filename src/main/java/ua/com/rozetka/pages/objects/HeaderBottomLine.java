package ua.com.rozetka.pages.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ua.com.rozetka.Driver;

public class HeaderBottomLine extends Driver {

   private final String SHOPPING_CART_BUTTON = "//li[contains(@class, 'item_type_cart')]";

   public HeaderBottomLine(WebDriver wd) {
      super(wd);
   }

   @Step("Open shopping card")
   public void openShoppingCart() {
      click(SHOPPING_CART_BUTTON);
   }
}
