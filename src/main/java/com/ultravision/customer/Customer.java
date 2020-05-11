package com.ultravision.customer;

import com.ultravision.payment.Payment;
import com.ultravision.payment.PaymentModel;
import com.ultravision.rental.Rental;
import com.ultravision.rental.RentalModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String loyaltyPoints;
    private String currentlyRented;
    private Subscription subscription;

    //Constructor for customer that comes from the database.
    public Customer(String... values){
    this.customerID = Integer.parseInt(values[0]);
    this.firstName = values[1];
    this.lastName = values[2];
    this.email = values[3];
    this.phone = values[4];
    this.address = values[5];
    this.loyaltyPoints = values[14];
    this.currentlyRented = values[15];

    //instantiate subscription inner class.
    this.subscription = new Subscription(values[6], values[7], values[8], values[9], Integer.parseInt(values[10]),
            values[11], values[12], Integer.parseInt(values[13]));
    }

    //Constructor for new customers, includes Database creation.
    public Customer(String firstName, String lastName, String email, String phone, String creditCard, int subscriptionType){
        this.customerID = CustomerModel.createCustomer(firstName, lastName, email, phone);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = "";
        this.loyaltyPoints = "0";
        this.currentlyRented = "0";

        //Create subscription
        this.subscription = new Subscription(creditCard);
        this.subscription.renew(subscriptionType);
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public String getLoyaltyPoints() {
        if(loyaltyPoints == null){
            return "0";
        }
        return loyaltyPoints;
    }

    public String getCurrentlyRented() {
        if(currentlyRented == null){
            return "0";
        }
        return currentlyRented;
    }

    //returns a list of payments made by the com.ultravision
    public ArrayList<Payment> getPayments(){
            return PaymentModel.getPayments(this.customerID);
    }

    //returns a list of titles rented by the com.ultravision
    public ArrayList<Rental> getTitlesRented(){
        return RentalModel.findPerCustomer(this.customerID);
    }

    //update data
    public void update(String firstName, String lastName, String email, String phone){
        CustomerModel.updateCustomer(this.customerID, firstName, lastName, email, phone);
    }

    /**
     * Inner class that handles the subscription of the com.ultravision.
     */
    public class Subscription{
        private String typeShort;
        private String typeFull;
        private String startDate;
        private String finishDate;
        private int amountPaid;
        private String cardNumber;
        private String cardType;
        private int itemLimit;
        private String status;

        private Subscription(String typeShort, String typeFull, String startDate, String finishDate, int amountPaid,
                     String cardNumber, String cardType, int itemLimit){

            this.typeShort = typeShort;
            this.typeFull = typeFull;
            this.startDate = startDate;
            this.finishDate = finishDate;
            this.amountPaid = amountPaid;
            this.cardNumber = cardNumber;
            this.cardType = cardType;
            this.itemLimit = itemLimit;
            this.setStatus();

        }

        private Subscription(String cardNumber){
            this.cardNumber = cardNumber;
            this.cardType = "Master Card";
        }

        public String getTypeShort() {
            return typeShort;
        }

        public String getTypeFull() {
            return typeFull;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getFinishDate() {
            return finishDate;
        }

        public int getAmountPaid() {
            return amountPaid;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String getMaskifiedCardNumber() {
            return "XXXX-XXXX-XXXX-"+cardNumber.substring(15);
        }

        public String getCardType() {
            return cardType;
        }

        public int getItemLimit() {
            return itemLimit;
        }

        //create a new subscription
        public void renew(int subscriptionType){
            CustomerModel.renewSubscription(subscriptionType, this.cardNumber, this.cardType, Customer.this.customerID);
        }

        /**
         * Set status of the subscription
         */
        private void setStatus(){
            Date today = new Date();

            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date sFinishDate=formatDate.parse(this.finishDate);

                if(today.before(sFinishDate)){
                    this.status = "Active";
                }
                else {
                    this.status = "Expired";
                }
            } catch (ParseException e) {
                e.printStackTrace();
                this.status = "On Hold";
            }
        }

        public String getStatus() {
            return status;
        }
    }
}
