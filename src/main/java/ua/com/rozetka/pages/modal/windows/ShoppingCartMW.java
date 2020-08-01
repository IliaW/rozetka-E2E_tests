package ua.com.rozetka.pages.modal.windows;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.com.rozetka.Driver;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartMW extends Driver implements ModalWindow {

   private final String CART_HEADER = "//h3[@class = 'modal__heading' and contains(text(),'Корзина')]";
   private final String CART_BUTTON = "//li[contains(@class,'item_type_cart')]";
   private final String PRODUCT_TITLES = "//a[@class = 'cart-product__title']";
   private final String CLOSE_BUTTON = "//button[@class = 'modal__close']";
   private final String CHECKOUT_BUTTON = "//a[contains(text(),'Оформить заказ')]";

   public ShoppingCartMW(WebDriver wd) {
      super(wd);
   }

   @Override
   public boolean isLoaded() {
      return checkThat.isDisplayed(CART_HEADER);
   }

   @Override
   @Step("Close cart modal window")
   public void close() {
      click(CLOSE_BUTTON);
   }

   @Step("CLick [Cart] on the header")
   public void open() {
      click(CART_BUTTON);
      isLoaded();
   }

   @Step("Click [Order]")
   public void clickOrderButton() {
      setExplicitlyWait(15);
      if (checkThat.isDisplayed(CHECKOUT_BUTTON)) {
         checkThat.isClickable(CHECKOUT_BUTTON);
         click(CHECKOUT_BUTTON);
         sleep(1000); // ElementClickInterceptedException occurred
      }
      setExplicitlyWait(DEFAULT_EXPLICIT_WAIT_TIME);
   }

   public List<String> getListOfProducts() {
      List<String> productTitles = new ArrayList<>();
      if (isLoaded()) {
         List<WebElement> productList = findAll(PRODUCT_TITLES);
         for (WebElement element : productList) {
            productTitles.add(element.getText());
         }
      }
      return productTitles;
   }
}