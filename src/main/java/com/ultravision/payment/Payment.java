package com.ultravision.payment;

public class Payment {
    private String item;
    private String detail;
    private String date;
    private String totalPaid;

    public Payment(String item, String detail, String date, String totalPaid){
        this.item = item;
        this.detail = detail;
        this.date = date;
        this.totalPaid = totalPaid;
    }

    /**
     * @return name of the com.ultravision.payment
     */
    public String getItem() {
        return item;
    }

    /**
     * @return detail of the com.ultravision.payment, e.g. title of the item, subscription plan, etc.
     */
    public String getDetail() {
        return detail;
    }


    /**
     * @return date of the com.ultravision.payment
     */
    public String getDate() {
        return date;
    }

    /**
     * @return amount paid
     */
    public String getTotalPaid() {
        return totalPaid;
    }
}
