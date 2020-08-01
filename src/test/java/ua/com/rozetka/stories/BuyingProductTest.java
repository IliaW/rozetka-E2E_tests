package ua.com.rozetka.stories;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.com.rozetka.Launcher;
import ua.com.rozetka.models.*;
import ua.com.rozetka.pages.CheckoutPage;
import ua.com.rozetka.pages.MainPage;
import ua.com.rozetka.pages.ProductPage;
import ua.com.rozetka.pages.ProductsPage;
import ua.com.rozetka.pages.modal.windows.LoginMW;
import ua.com.rozetka.pages.modal.windows.ShoppingCartMW;
import ua.com.rozetka.pages.objects.HeaderBottomLine;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyingProductTest extends Launcher {

   private MainPage mainPage;
   private ProductPage productPage;
   private ProductsPage productsPage;
   private CheckoutPage checkoutPage;
   private LoginMW loginMW;
   private ShoppingCartMW shoppingCartMW;
   private HeaderBottomLine headerBottomLine;

   private User user;

   @BeforeClass
   public void setUpBeforeClass() {
      mainPage = new MainPage(app.getDriver());
      productPage = new ProductPage(app.getDriver());
      productsPage = new ProductsPage(app.getDriver());
      checkoutPage = new CheckoutPage(app.getDriver());
      loginMW = new LoginMW(app.getDriver());
      shoppingCartMW = new ShoppingCartMW(app.getDriver());
      headerBottomLine = new HeaderBottomLine(app.getDriver());

      user = new UserBuilder("qatest.vovk@gmail.com", "Testing123", "Илья")
              .setSurname("Вовк")
              .setPhoneNumber("0630000000")
              .setAddress(new Address("Киев", "Крещатик", "1", "1"))
              .setCard(new Card("0000000000000000", "08", "25", "111"))
              .create();
   }

   @AfterMethod(alwaysRun = true)
   public void screenshot() {
      app.screen.takeScreenshot(app.getDriver());
   }

   @Test
   @Feature("Authorization")
   @Description(value = "Login with valid credential")
   @Severity(SeverityLevel.NORMAL)
   public void loginWithValidCredentialTest() {
      mainPage.openByURL();
      mainPage.openLoginForm();
      loginMW.enterLogin(user.getLogin())
              .enterPassword(user.getPassword())
              .login();
      assertThat(mainPage.getUserName()).isEqualTo(user.getName() + " " + user.getSurname());
      assertThat(mainPage.isLoaded()).isTrue();
   }

   @Test(dependsOnMethods = "loginWithValidCredentialTest")
   @Feature("Shopping Cart")
   @Description(value = "Adding random product to shopping cart")
   @Severity(SeverityLevel.CRITICAL)
   public void addItemToCartTest() {
      mainPage.openRandomCategory();
      productsPage.openRandomProductOnThePage();
      Product product = new Product(productPage.getProductName());
      productPage.clickBuyButton();
      shoppingCartMW.open();
      assertThat(shoppingCartMW.getListOfProducts()).contains(product.getName());
   }

   @Test(dependsOnMethods = "addItemToCartTest")
   @Feature("Checkout")
   @Description(value = "Filling out order form with user data")
   @Severity(SeverityLevel.CRITICAL)
   public void fillOrderFormTest() {
      mainPage.openByURL();
      headerBottomLine.openShoppingCart();
      shoppingCartMW.clickOrderButton();
      checkoutPage.setNameAndSurname(user.getName() + " " + user.getSurname())
              .setCity(user.getAddress().getCity())
              .setPhoneNumber(user.getPhoneNumber())
              .clickNextButton();
      checkoutPage.setAddress(user.getAddress().getStreet(),
              user.getAddress().getHouse(),
              user.getAddress().getFlat());
      //checkoutPage.clickOrderButton();
      assertThat(checkoutPage.addressFieldIsLoaded()).isTrue();
   }
}