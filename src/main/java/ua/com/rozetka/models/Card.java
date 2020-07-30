package ua.com.rozetka.models;

public class Card {

   private String numberOfCard;
   private String month;
   private String year;
   private String CVV;

   public Card(String numberOfCard, String month, String year, String CVV) {
      this.numberOfCard = numberOfCard;
      this.month = month;
      this.year = year;
      this.CVV = CVV;
   }

   public String getNumberOfCard() {
      return numberOfCard;
   }

   public String getMonth() {
      return month;
   }

   public String getYear() {
      return year;
   }

   public String getCVV() {
      return CVV;
   }
}