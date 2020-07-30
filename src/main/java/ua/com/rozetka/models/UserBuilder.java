package ua.com.rozetka.models;

public class UserBuilder {

   private String login;
   private String password;
   private String name;
   private String surname;
   private String phoneNumber;
   private Address address;
   private Card card;

   public UserBuilder(String login, String password, String name) {
      this.login = login;
      this.password = password;
      this.name = name;
   }

   public UserBuilder setSurname(String surname) {
      this.surname = surname;
      return this;
   }

   public UserBuilder setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
   }

   public UserBuilder setAddress(Address address) {
      this.address = address;
      return this;
   }

   public UserBuilder setCard(Card card) {
      this.card = card;
      return this;
   }

   public User create() {
      return new User(login, password, name, surname, phoneNumber, address, card);
   }
}