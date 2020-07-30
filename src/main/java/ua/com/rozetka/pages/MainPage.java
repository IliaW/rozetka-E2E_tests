package ua.com.rozetka.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.com.rozetka.Driver;

import java.util.Arrays;
import java.util.List;

public class MainPage extends Driver implements WebPage {

   private final String URL = "https://rozetka.com.ua/";

   private final String MAIN_PAGE_TITLE = "//title[contains(text(), 'Интернет-магазин ROZETKA')]";
   private final String UNAUTHORIZED_LABEL = "//a[contains(text(),'войдите в личный кабинет')]";
   private final String SLIDER = "//div[@class = 'common-slider__content']";
   private final String USER_NAME = "//a[@class = 'header-topline__user-link link-dashed']";
   private final String CATALOG_BUTTON = "//button[@class = 'menu-toggler']";
   private final String LIST_OF_CATEGORIES = "//ul[@class = 'menu-categories']//li[ contains(@class,'menu-categories__item')]";
   private final String LIST_OF_SUBCATEGORIES = "//li[contains(@class,'item_state_hovered')]//a[@class = 'menu__link']";

   public MainPage(WebDriver wd) {
      super(wd);
   }

   @Override
   public boolean isLoaded() {
      return checkThat.isDisplayed(SLIDER) & checkThat.presentOnTheDOM(MAIN_PAGE_TITLE);
   }

   public String getUserName() {
      return find(USER_NAME).getText();
   }

   @Step("Open main page")
   public void openByURL() {
      open(URL);
      isLoaded();
   }

   @Step("Open random category with products")
   public void openRandomCategory() {
      List<String> stopCategoriesList = Arrays.asList(
              "Товары для бизнеса",
              "Услуги и сервисы",
              "Распродажа до -55%",
              "Туры и отдых");

      click(CATALOG_BUTTON);
      List<WebElement> categoriesList = findAll(LIST_OF_CATEGORIES);
      // Deleting categories with services
      for (String stopCategory : stopCategoriesList) {
         categoriesList.removeIf(c -> c.getText().equals(stopCategory));
      }
      //Selecting random category
      WebElement category = categoriesList.get((int) (Math.random() * categoriesList.size()));
      action.moveToElement(category).perform();
      //Selecting random subcategory
      List<WebElement> subcategories = findAll(LIST_OF_SUBCATEGORIES);
      WebElement subcategory = subcategories.get((int) (Math.random() * subcategories.size()));
      action.click(subcategory).perform();
   }

   @Step("Open login form")
   public void openLoginForm() {
      if (checkThat.isDisplayed(UNAUTHORIZED_LABEL)) {
         click(UNAUTHORIZED_LABEL);
      }
   }
}