package com.ultravision.rental;

import com.ultravision.utils.ServiceUtil;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class RentalModel {

    /**
     *
     * @return ArrayList of Rentals in the database.
     */
    public static ArrayList<Rental> findAll(){
        ArrayList<Rental> listRentals = new ArrayList<Rental>();

        //query to bring customers and its subscription data
        String query = "SELECT \n" +
                "    r.id,\n" +
                "    r.item_id,\n" +
                "    i.title,\n" +
                "    r.customer_id,\n" +
                "    r.loyalty_transaction,\n" +
                "    DATE_FORMAT(r.rental_date, '%Y/%m/%d') AS rental_date,\n" +
                "    DATE_FORMAT(r.return_date, '%Y/%m/%d') AS return_date,\n" +
                "    rs.name,\n" +
                "    rr.name,\n" +
                "    r.fine,\n" +
                "    r.price\n" +
                "FROM\n" +
                "    rental as r\n" +
                "INNER JOIN rental_status as rs ON r.status_rented = rs.id\n" +
                "INNER JOIN rental_status as rr ON r.status_returned = rr.id\n" +
                "INNER JOIN item as i ON r.item_id = i.id\n" +
                "ORDER BY rental_date DESC;";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //Iterate data to get rental rows (cr)
        for (@SuppressWarnings("unchecked") ArrayList<String> cr: data) {
            Rental rental = new Rental(Integer.parseInt(cr.get(0)), cr.get(1), cr.get(2), cr.get(3), cr.get(4),
                    cr.get(5), cr.get(6), cr.get(7), cr.get(8), Integer.parseInt(cr.get(9)), Integer.parseInt(cr.get(10)));

            listRentals.add(rental);
        }

        return listRentals;
    }

    public static void rentTitle(int itemID, int customerID, int loyalty, int rentalPrice){
        //query to insert rental item data
        String query = "INSERT INTO rental\n" +
                "(item_id,\n" +
                "customer_id,\n" +
                "loyalty_transaction,\n" +
                "rental_date,\n" +
                "status_rented,\n" +
                "fine,\n" +
                "price)\n" +
                "VALUES\n" +
                "(\n" +
                ""+itemID+",\n" +
                ""+customerID+",\n" +
                ""+loyalty+",\n" +
                "NOW(),\n" +
                "(SELECT item_status FROM item WHERE id = "+itemID+"),\n" +
                "0,\n" +
                ""+rentalPrice+");";

        //execute query with service util static method
        ServiceUtil.databaseUpdate(query);
    }

    /**
     *
     * @return ArrayList of Rentals in the database.
     */
    public static ArrayList<Rental> findPerCustomer(int customerID){
        ArrayList<Rental> listRentals = new ArrayList<Rental>();

        //query to bring customers and its subscription data
        String query = "SELECT \n" +
                "    r.id,\n" +
                "    r.item_id,\n" +
                "    i.title,\n" +
                "    r.customer_id,\n" +
                "    r.loyalty_transaction,\n" +
                "    DATE_FORMAT(r.rental_date, '%Y/%m/%d') AS rental_date,\n" +
                "    DATE_FORMAT(r.return_date, '%Y/%m/%d') AS return_date,\n" +
                "    rs.name,\n" +
                "    rr.name,\n" +
                "    r.fine,\n" +
                "    r.price\n" +
                "FROM\n" +
                "    rental as r\n" +
                "INNER JOIN rental_status as rs ON r.status_rented = rs.id\n" +
                "LEFT JOIN rental_status as rr ON r.status_returned = rr.id\n" +
                "INNER JOIN item as i ON r.item_id = i.id\n" +
                "WHERE r.customer_id = "+customerID+"\n" +
                "ORDER BY rental_date DESC;";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //Iterate data to get rental rows (cr)
        for (@SuppressWarnings("unchecked") ArrayList<String> cr: data) {
            Rental rental = new Rental(Integer.parseInt(cr.get(0)), cr.get(1), cr.get(2), cr.get(3), cr.get(4),
                    cr.get(5), cr.get(6), cr.get(7), cr.get(8), Integer.parseInt(cr.get(9)), Integer.parseInt(cr.get(10)));

            listRentals.add(rental);
        }

        return listRentals;
    }
}
