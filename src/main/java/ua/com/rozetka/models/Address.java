package ua.com.rozetka.models;

public class Address {

   private String city;
   private String street;
   private String house;
   private String flat;

   public Address(String city, String street, String house, String flat) {
      this.city = city;
      this.street = street;
      this.house = house;
      this.flat = flat;
   }

   public String getCity() {
      return city;
   }

   public String getStreet() {
      return street;
   }

   public String getHouse() {
      return house;
   }

   public String getFlat() {
      return flat;
   }
}