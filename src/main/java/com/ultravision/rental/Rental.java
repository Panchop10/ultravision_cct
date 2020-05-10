package com.ultravision.rental;

public class Rental {
    private int rentalID;
    private String itemID;
    private String itemTitle;
    private String customerID;
    private String loyaltyTransaction;
    private String rentalDate;
    private String returnDate;
    private String statusRented;
    private String statusReturned;
    private int fine;
    private int price;

    public Rental(int rentalID, String itemID, String itemTitle, String customerID, String loyaltyTransaction, String rentalDate,
                  String returnDate, String statusRented, String statusReturned, int fine, int price){

        this.rentalID = rentalID;
        this.itemID = itemID;
        this.itemTitle = itemTitle;
        this.customerID = customerID;
        this.loyaltyTransaction = loyaltyTransaction;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.statusRented = statusRented;
        this.statusReturned = statusReturned;
        this.fine = fine;
        this.price = price;
    }


    public int getRentalID() {
        return rentalID;
    }

    public String getItemID() {
        return itemID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getLoyaltyTransaction() {
        return loyaltyTransaction;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStatusRented() {
        return statusRented;
    }

    public String getStatusReturned() {
        return statusReturned;
    }

    public int getFine() {
        return fine;
    }

    public int getPrice() {
        return price;
    }

    public String getItemTitle() {
        return itemTitle;
    }
}
