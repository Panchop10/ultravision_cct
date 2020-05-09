package customer;

import utils.ServiceUtil;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerModel {

    /**
     *
     * @return ArrayList of Customers in the database.
     */
    public static ArrayList<Customer> findAll(){
        ArrayList<Customer> listCustomers = new ArrayList<Customer>();

        //query to bring customers and its subscription data
        String query = "SELECT \n" +
                "    c.id,\n" +
                "    c.first_name,\n" +
                "    c.last_name,\n" +
                "    c.email,\n" +
                "    c.phone,\n" +
                "    c.address,\n" +
                "    t.short_name,\n" +
                "    t.full_name,\n" +
                "    s.start_date,\n" +
                "    s.finish_date,\n" +
                "    s.amount_paid,\n" +
                "    s.card_number,\n" +
                "    s.card_type,\n" +
                "    t.item_limit\n" +
                "FROM\n" +
                "    customer AS c\n" +
                "        INNER JOIN\n" +
                "    subscription AS s ON s.id = (SELECT \n" +
                "            id\n" +
                "        FROM\n" +
                "            subscription AS s2\n" +
                "        WHERE\n" +
                "            s2.customer_id = c.id\n" +
                "        ORDER BY s2.id DESC\n" +
                "        LIMIT 1)\n" +
                "        INNER JOIN\n" +
                "    subscription_type AS t ON s.type = t.id\n" +
                "    ORDER BY c.id ASC;";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //Iterate data to get customer rows (cr)
        for (@SuppressWarnings("unchecked") ArrayList<String> cr: data) {
            //convert ArrayList to Array and pass values as varargs to customer
            Customer customer = new Customer((cr.toArray(new String[0])));
            listCustomers.add(customer);
        }

        return listCustomers;
    }

    /**
     *
     * @return All the subscription type from the database
     */
    public static ArrayList<ArrayList> getSubscriptionType(){
        //query to bring all the subscription type
        String query = "SELECT * FROM subscription_type;";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //return data response of the query
        return data;
    }
}
