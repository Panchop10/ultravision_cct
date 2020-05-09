package customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Subscription subscription;

    //Constructor for customer that comes from the database.
//    public Customer(int id, String firstName, String lastName, String email, String phone, String address,
//                    String sTypeShort, String sTypeFull, String sStartDate, String sFinishDate, int sAmountPaid,
//                    String sCardNumber, String sCardType, int sItemLimit){
//        this.customerID = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//
//        //instantiate subscription inner class.
//        this.subscription = new Subscription(sTypeShort, sTypeFull, sStartDate, sFinishDate, sAmountPaid,
//                sCardNumber, sCardType, sItemLimit);
//    }

        //Constructor for customer that comes from the database.
        public Customer(String... values){
        this.customerID = Integer.parseInt(values[0]);
        this.firstName = values[1];
        this.lastName = values[2];
        this.email = values[3];
        this.phone = values[4];
        this.address = values[5];

        //instantiate subscription inner class.
        this.subscription = new Subscription(values[6], values[7], values[8], values[9], Integer.parseInt(values[10]),
                values[11], values[12], Integer.parseInt(values[13]));
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

    /**
     * Inner class that handles the subscription of the customer.
     */
    class Subscription{
        String typeShort;
        String typeFull;
        String startDate;
        String finishDate;
        int amountPaid;
        String cardNumber;
        String cardType;
        int itemLimit;
        String status;

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
