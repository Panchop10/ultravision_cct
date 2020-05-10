package com.ultravision.item;

import com.ultravision.utils.ServiceUtil;

import java.util.ArrayList;
import java.util.UUID;

public class ItemModel {

    /**
     *
     * @return ArrayList of Customers in the database.
     */
    public static ArrayList<Item> findAll(){
        ArrayList<Item> listCustomers = new ArrayList<Item>();

        //query to bring items
        String query = "SELECT \n" +
                "    i.id,\n" +
                "    i.title,\n" +
                "    i.product_uid,\n" +
                "    i.rental_price,\n" +
                "    i.year_released,\n" +
                "    i.genre,\n" +
                "    i.disk_type,\n" +
                "    i.director_or_band,\n" +
                "    t.type,\n" +
                "    s.name\n" +
                "FROM\n" +
                "    item AS i\n" +
                "        INNER JOIN\n" +
                "    item_type AS t ON i.item_type = t.id\n" +
                "        INNER JOIN\n" +
                "    item_status AS s ON i.item_status = s.id\n" +
                "    ORDER BY i.id DESC;";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //Iterate data to get items rows (cr)
        for (@SuppressWarnings("unchecked") ArrayList<String> cr: data) {
            //convert ArrayList to Array and pass values as varargs to customers
            Item item = new Item((cr.toArray(new String[0])));
            listCustomers.add(item);
        }

        return listCustomers;
    }

    /**
     *
     * @return ArrayList of Customers in the database.
     */
    public static Item findItem(int itemID){
        Item item = null;

        //query to bring item data
        String query = "SELECT \n" +
                "    i.id,\n" +
                "    i.title,\n" +
                "    i.product_uid,\n" +
                "    i.rental_price,\n" +
                "    i.year_released,\n" +
                "    i.genre,\n" +
                "    i.disk_type,\n" +
                "    i.director_or_band,\n" +
                "    t.type,\n" +
                "    s.name\n" +
                "FROM\n" +
                "    item AS i\n" +
                "        INNER JOIN\n" +
                "    item_type AS t ON i.item_type = t.id\n" +
                "        INNER JOIN\n" +
                "    item_status AS s ON i.item_status = s.id\n" +
                "WHERE i.id = "+ itemID +";";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //Iterate data to get customer rows (cr)
        for (@SuppressWarnings("unchecked") ArrayList<String> cr: data) {
            //convert ArrayList to Array and pass values as varargs to item
            item = new Item((cr.toArray(new String[0])));
        }

        return item;
    }

    public static void updateItem(int itemID, int price, int year, String director, String title,
                                  String genre, String diskType, int itemType){
        //query to update item data
        String query = "UPDATE item\n" +
                "SET \n" +
                "    title = '"+title+"',\n" +
                "    rental_price = "+price+",\n" +
                "    year_released = "+year+",\n" +
                "    genre = '"+genre+"',\n" +
                "    disk_type = '"+diskType+"',\n" +
                "    director_or_band = '"+director+"',\n" +
                "    item_type = "+(itemType+1)+"\n" +
                "WHERE\n" +
                "    (id = "+itemID+");";

        //execute query with service util static method
        ServiceUtil.databaseUpdate(query);
    }

    /**
     *
     * @return All the item type from the database
     */
    public static ArrayList<ArrayList> getItemType(){
        //query to bring all the item type
        String query = "SELECT * FROM item_type;";

        //execute query with service util static method
        ArrayList<ArrayList> data = ServiceUtil.databaseSelect(query);

        //return data response of the query
        return data;
    }


    public static int createItem(String title, int price, int year, String genre, String diskType,
                                 String director, int itemType){

        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        //query to create new item
        String query = "INSERT INTO item\n" +
                "(title,\n" +
                "product_uid,\n" +
                "store_id,\n" +
                "rental_price,\n" +
                "year_released,\n" +
                "genre,\n" +
                "disk_type,\n" +
                "director_or_band,\n" +
                "item_type,\n" +
                "item_status,\n" +
                "is_active)\n" +
                "VALUES\n" +
                "('"+title+"',\n" +
                "'"+randomUUIDString+"',\n" +
                "1,\n" +
                ""+price+",\n" +
                ""+year+",\n" +
                "'"+genre+"',\n" +
                "'"+diskType+"',\n" +
                "'"+director+"',\n" +
                ""+(itemType+1)+",\n" +
                "1,\n" +
                "1);";

        //execute query and get id created back
        int itemId = ServiceUtil.databaseCreate(query);
        return itemId;
    }
}
