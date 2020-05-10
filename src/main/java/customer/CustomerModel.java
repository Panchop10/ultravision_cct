package customer;

import utils.ServiceUtil;

import java.sql.ResultSet;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")
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
                "    t.item_limit,\n" +
                "    l.loyalty_points,\n" +
                "    r.items_rented\n" +
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
                "        LEFT JOIN\n" +
                "    loyalty_system AS l ON c.id = l.customer_id\n" +
                "        LEFT JOIN\n" +
                "    rented_per_customer AS r ON c.id = r.customer_id\n" +
                "ORDER BY c.id ASC;";

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
     * @return ArrayList of Customers in the database.
     */
    public static Customer findCustomer(int customerID){
        Customer customer = null;

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
                "    t.item_limit,\n" +
                "    l.loyalty_points,\n" +
                "    r.items_rented\n" +
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
                "        LEFT JOIN\n" +
                "    loyalty_system AS l ON c.id = l.customer_id\n" +
                "        LEFT JOIN\n" +
                "    rented_per_customer AS r ON c.id = r.customer_id\n" +
                "WHERE c.id = "+ customerID +";";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //Iterate data to get customer rows (cr)
        for (@SuppressWarnings("unchecked") ArrayList<String> cr: data) {
            //convert ArrayList to Array and pass values as varargs to customer
            customer = new Customer((cr.toArray(new String[0])));
        }

        return customer;
    }

    public static void renewSubscription(int subscriptionType, String cardNumber, String cardType, int customerID){
        ArrayList<ArrayList> subTypes = CustomerModel.getSubscriptionType();
        int price = Integer.parseInt(subTypes.get(subscriptionType).get(3).toString());

        //query that insert a new subscription
        String query = "INSERT INTO subscription\n" +
                "(type,\n" +
                "start_date,\n" +
                "finish_date,\n" +
                "amount_paid,\n" +
                "card_number,\n" +
                "card_type,\n" +
                "customer_id)\n" +
                "VALUES\n" +
                "("+(subscriptionType+1)+",\n" +
                "CURDATE(),\n" +
                "DATE_ADD(CURDATE(), INTERVAL 1 month ),\n" +
                ""+price+",\n" +
                "\""+cardNumber+"\",\n" +
                "\""+cardType+"\",\n" +
                ""+customerID+");";

        //execute query with service util static method
        ServiceUtil.databaseUpdate(query);
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

    /**
     * Updates customer details
     * @param customerID Customer ID that is going to be updated
     * @param firstName new first name
     * @param lastName new last name
     * @param email new email
     * @param phone new phone
     */
    public static void updateCustomer(int customerID, String firstName, String lastName, String email, String phone){
        //query to update customer data
        String query = "UPDATE customer\n" +
                "SET \n" +
                "    first_name = '"+firstName+"',\n" +
                "    last_name = '"+lastName+"',\n" +
                "    email = '"+email+"',\n" +
                "    phone = '"+phone+"'\n" +
                "WHERE\n" +
                "    (id = "+customerID+");";

        //execute query with service util static method
        ServiceUtil.databaseUpdate(query);
    }

    public static int createCustomer(String firstName, String lastName, String email, String phone){
        //query to create new customer
        String query = "INSERT INTO customer\n" +
                "(first_name,\n" +
                "last_name,\n" +
                "email,\n" +
                "phone,\n" +
                "is_active)\n" +
                "VALUES\n" +
                "('"+firstName+"',\n" +
                "'"+lastName+"',\n" +
                "'"+email+"',\n" +
                "'"+phone+"',\n" +
                "1);";

        //execute query and get id created back
        int custId = ServiceUtil.databaseCreate(query);
        return custId;
    }
}
