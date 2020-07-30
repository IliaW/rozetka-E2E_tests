package ua.com.rozetka.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.com.rozetka.Driver;

import java.util.List;

public class ProductsPage extends Driver implements WebPage {

   private final String PRODUCT_LIST_ON_PAGE = "//ul[@class = 'catalog-grid']//li//span[@class = 'goods-tile__title']/..";
   private final String BREAD_CRUMBS = "//ul[@class = 'breadcrumbs']";
   private final String SORT_SELECTOR = "//select[contains(@class, 'select-css')]";

   public ProductsPage(WebDriver wd) {
      super(wd);
   }

   @Override
   public boolean isLoaded() {
      return checkThat.isDisplayed(BREAD_CRUMBS, SORT_SELECTOR);
   }

   @Step("Open random product on the page")
   public void openRandomProductOnThePage() {
      if (isLoaded()) {
         List<WebElement> products = findAll(PRODUCT_LIST_ON_PAGE);
         if (products.size() != 0) {
            WebElement product = products.get((int) (Math.random() * products.size()));
            open(product.getAttribute("href"));
         }
      }
   }
}