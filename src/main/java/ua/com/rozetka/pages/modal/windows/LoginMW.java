package ua.com.rozetka.pages.modal.windows;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ua.com.rozetka.Driver;

public class LoginMW extends Driver implements ModalWindow {

   private final String LOGIN_HEADER = "//h3[@class = 'modal__heading' and contains(text(),'Вход')]";
   private final String LOGIN_FIELD = "//input[@id = 'auth_email']";
   private final String PASSWORD_FIELD = "//input[@id = 'auth_pass']";
   private final String LOGIN_BUTTON = "//button[contains(text(), ' Войти ')]";
   private final String CLOSE_BUTTON = "//button[@class = 'modal__close']";

   public LoginMW(WebDriver wd) {
      super(wd);
   }

   @Override
   public boolean isLoaded() {
      return checkThat.isDisplayed(LOGIN_HEADER);
   }

   @Override
   @Step("Close login modal window")
   public void close() {
      click(CLOSE_BUTTON);
   }

   @Step("Enter login {login}")
   public LoginMW enterLogin(String login) {
      enterText(login, LOGIN_FIELD);
      return this;
   }

   @Step("Enter password {pass}")
   public LoginMW enterPassword(String pass) {
      enterText(pass, PASSWORD_FIELD);
      return this;
   }

   @Step("Click [Login]")
   public void login() {
      click(LOGIN_BUTTON);
      checkThat.isStaleness(find(LOGIN_HEADER));
   }
}