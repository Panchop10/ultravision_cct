package customer.payment;

import utils.ServiceUtil;

import java.util.ArrayList;

public class PaymentModel {

    public static ArrayList<Payment> getPayments(int customerID){
        ArrayList<Payment> listPayments = new ArrayList<Payment>();

        String query = "SELECT \n" +
                "    'Subscription' AS item,\n" +
                "    t.full_name AS detail,\n" +
                "    DATE_FORMAT(s.start_date, \"%Y/%m/%d\") AS date,\n" +
                "    s.amount_paid AS paid\n" +
                "FROM\n" +
                "    subscription AS s\n" +
                "        INNER JOIN\n" +
                "    subscription_type AS t ON s.type = t.id\n" +
                "WHERE\n" +
                "    s.customer_id = "+customerID+" \n" +
                "UNION SELECT \n" +
                "    'Rental' AS item,\n" +
                "    i.title AS detail,\n" +
                "    DATE_FORMAT(r.rental_date, \"%Y/%m/%d\") AS date,\n" +
                "    r.price AS paid\n" +
                "FROM\n" +
                "    rental AS r\n" +
                "        INNER JOIN\n" +
                "    item AS i ON r.item_id = i.id\n" +
                "WHERE\n" +
                "    r.customer_id = "+customerID+" \n" +
                "UNION SELECT \n" +
                "    'Fine' AS item,\n" +
                "    i.title AS detail,\n" +
                "    DATE_FORMAT(r.rental_date, \"%Y/%m/%d\") AS date,\n" +
                "    r.fine AS paid\n" +
                "FROM\n" +
                "    rental AS r\n" +
                "        INNER JOIN\n" +
                "    item AS i ON r.item_id = i.id\n" +
                "WHERE\n" +
                "    r.fine != 0 AND r.customer_id = "+customerID+"\n" +
                "ORDER BY date DESC;";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //Iterate data to get payments rows (cr)
        for (@SuppressWarnings("unchecked") ArrayList<String> pRow: data) {
            //Create payment objects and store them into the ArrayList
            Payment payment = new Payment(pRow.get(0), pRow.get(1), pRow.get(2), pRow.get(3));
            listPayments.add(payment);
        }

        return listPayments;

    }
}
