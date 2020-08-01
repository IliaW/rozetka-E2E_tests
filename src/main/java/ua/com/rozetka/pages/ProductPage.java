package ua.com.rozetka.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ua.com.rozetka.Driver;

public class ProductPage extends Driver implements WebPage {

   private final String BUY_BUTTON = "//button[contains(@class,'button_size_large') and contains(@aria-label,'Купить')]";
   private final String ITEM_IN_THE_CART_BUTTON = "//button[contains(@class,'button_size_large')]//span[contains(text(), 'Товар уже в корзине')]";
   private final String PRODUCT_NAME = "//div[@class = 'product__heading']/h1";
   private final String RATING = "//div[contains(@class, 'product__rating')]";

   private final String SELECT_CITY_POPUP = "//h3[contains(text(), ' Выберите свой город ')]";
   private final String CLOSE_POPUP = "//button[@class = 'modal__close']";

   public ProductPage(WebDriver wd) {
      super(wd);
   }

   @Override
   public boolean isLoaded() {
      return checkThat.isDisplayed(PRODUCT_NAME, RATING);
   }

   private void closeSelectCityPopup() {
      setExplicitlyWait(3);
      if (checkThat.isDisplayed(SELECT_CITY_POPUP)) {
         click(CLOSE_POPUP);
      }
      setExplicitlyWait(DEFAULT_EXPLICIT_WAIT_TIME);
   }

   @Step("Click [Buy]")
   public ProductPage clickBuyButton() {
      if (checkThat.isDisplayed(BUY_BUTTON)) {
         clickJSE(BUY_BUTTON);
         closeSelectCityPopup();
         checkThat.isDisplayed(ITEM_IN_THE_CART_BUTTON);
      }
      return this;
   }

   @Step("Click [The item is already in the cart]")
   public void clickItemInTheCartButton() {
      click(ITEM_IN_THE_CART_BUTTON);
   }

   public String getProductName() {
      return find(PRODUCT_NAME).getText();
   }
}