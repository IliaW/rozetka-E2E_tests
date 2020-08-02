package ua.com.rozetka.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ua.com.rozetka.Driver;

public class CheckoutPage extends Driver implements WebPage {

   private final String CHECKOUT_HEADER = "//h2[contains(text(),'Оформление заказа')]";
   // First step locators
   private final String NAME_SURNAME_FIELD = "//input[@id = 'reciever_name']";
   private final String CITY_FIELD = "//input[@id = 'suggest_locality']";
   private final String MOBILE_FIELD = "//input[@id = 'reciever_phone']";
   private final String NEXT_STEP_BUTTON = "//button[contains(text(), 'Далее')]";
   private final String SECOND_STEP_FORM = "//div[@class = 'clearfix check-step-content']";
   // Second step locators
   private final String STREET_FIELD = "//input[@id = 'reciever_street_1']";
   private final String HOUSE_FIELD = "//input[@id = 'reciever_house_1']";
   private final String FLAT_FIELD = "//input[@id = 'reciever_flat_1']";
   private final String CARD_FRAME = "//div[@name = '4528_user_tokens']//iframe";
   private final String CARD_NUMBER_FIELD = "//input[@class = 'common-input-field' and @id='cardNumber']";
   private final String MONTH_FIELD = "//input[@id = 'month']";
   private final String YEAR_FIELD = "//input[@id = 'year']";
   private final String CVV_FIELD = "//input[@id = 'cvv']";
   private final String COURIER_CHECKBOX = "//label[contains(@name, 'couriers')]/div";
   private final String CARD_CHECKBOX = "//div[@name = 'payment_method' ]//li[@name = 'card']";
   private final String ONLINE_CARD_CHECKBOX = "//label[@name = 'card_4528']";
   private final String RECIPIENT_CHECKBOX = "//div[contains(text(), 'Кто получатель')]/..//div[text() = 'Я']";

   private final String OLD_VERSION_PAGE_BUTTON = "//button[text()=' Перейти на старую версию ']";

   public CheckoutPage(WebDriver wd) {
      super(wd);
   }

   @Override
   public boolean isLoaded() {
      setExplicitlyWait(2);
      if (checkThat.isDisplayed(OLD_VERSION_PAGE_BUTTON)) {
         clickJSE(OLD_VERSION_PAGE_BUTTON); // Open old version of the checkout page
      }
      setExplicitlyWait(DEFAULT_EXPLICIT_WAIT_TIME);
      return checkThat.isDisplayed(CHECKOUT_HEADER, NAME_SURNAME_FIELD);
   }

   @Step("Set '{nameAndSurname}' in Name field]")
   public CheckoutPage setNameAndSurname(String nameAndSurname) {
      if (isLoaded()) {
         enterText(nameAndSurname, NAME_SURNAME_FIELD);
      }
      return this;
   }

   @Step("Set '{city}' in the City field]")
   public CheckoutPage setCity(String city) {
      enterText(city, CITY_FIELD);
      sleep(2000);
      keyboard.pressTAB(); //For hide dropdown list
      return this;
   }

   @Step("Set '{mobile}' in name the Mobile Phone field")
   public CheckoutPage setPhoneNumber(String mobile) {
      enterText(mobile, MOBILE_FIELD);
      sleep(2000);
      return this;
   }

   @Step("Click [Next]")
   public void clickNextButton() {
      clickJSE(NEXT_STEP_BUTTON);
   }

   private boolean nextStepButtonIsEnable() {
      String statusLocator = find("//button[contains(text(), 'Далее')]/parent::span").getAttribute("class");
      return !statusLocator.contains("disabled");
   }

   @Step("Set Street: '{street}', House: '{house}', Flat: '{flat}' in the Address fields")
   public CheckoutPage setAddress(String street, String house, String flat) {
      if (checkThat.isDisplayed(COURIER_CHECKBOX)) {
         scrollToElement(COURIER_CHECKBOX);
         clickJSE(COURIER_CHECKBOX);
         if (checkThat.isDisplayed(STREET_FIELD)) {
            enterText(street, STREET_FIELD);
            enterText(house, HOUSE_FIELD);
            enterText(flat, FLAT_FIELD);
         }
      }
      return this;
   }

   @Step("Enter card credential: '{cardNumber}', '{month}', '{year}', '{cvv}'")
   public CheckoutPage payByCard(String cardNumber, String month, String year, String cvv) {
      if (checkThat.isDisplayed(CARD_CHECKBOX)) {
         scrollToElement(CARD_CHECKBOX);
         clickJSE(CARD_CHECKBOX);
         clickJSE(ONLINE_CARD_CHECKBOX);
         setExplicitlyWait(20);
         switchToFrame(CARD_FRAME);
         if (checkThat.isDisplayed(CARD_NUMBER_FIELD)) {
            enterText(cardNumber, CARD_NUMBER_FIELD);
            enterText(month, MONTH_FIELD);
            enterText(year, YEAR_FIELD);
            enterText(cvv, CVV_FIELD);
         }
         switchToDefaultContent();
         setExplicitlyWait(DEFAULT_EXPLICIT_WAIT_TIME);
      }
      return this;
   }

   public boolean addressFieldIsLoaded() {
      return checkThat.isDisplayed(STREET_FIELD, HOUSE_FIELD, FLAT_FIELD);
   }
}