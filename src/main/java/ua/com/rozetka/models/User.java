package ua.com.rozetka.models;

public class User {
   private String login;
   private String password;
   private String name;
   private String surname;
   private String phoneNumber;
   private Address address;
   private Card card;

   public User(String login, String password, String name, String surname,
               String phoneNumber, Address address, Card card) {
      this.login = login;
      this.password = password;
      this.name = name;
      this.surname = surname;
      this.phoneNumber = phoneNumber;
      this.address = address;
      this.card = card;
   }

   public String getLogin() {
      return login;
   }

   public String getPassword() {
      return password;
   }

   public String getName() {
      return name;
   }

   public String getSurname() {
      return surname;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public Address getAddress() {
      return address;
   }

   public Card getCard() {
      return card;
   }
}