package utils;

import java.sql.*;
import java.util.ArrayList;

public class ServiceUtil {

    /**
     *
     * @return URL for MYSQL JBDC connection based on enum Database.
     */
    public static String createSQLUrl(){
        return "jdbc:mysql://" +
                Database.HOST.get() +
                ":" +
                Database.PORT.get() +
                "/" +
                Database.SCHEMA.get() +
                "?useSSL=false"
                ;
    }

    /**
     *
     * @param query receives the string that is going to be queried into the database
     * @return ArrayList with all the data of the query
     */
    public static ArrayList<ArrayList> databaseSelect(String query){
        //database credentials
        String url = ServiceUtil.createSQLUrl();
        String user = Database.USER.get();
        String password = Database.PASSWORD.get();

        //ArrayList to store data
        ArrayList<ArrayList> data = new ArrayList<ArrayList>();

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            //Create connection and execute query
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            //Stores properties of a ResultSet object, including column count
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            while (rs.next()){
                ArrayList<String> row = new ArrayList<String>();

                //go over every column in the row
                for (int i = 1; i <= columnCount; i++){
                    row.add(rs.getString(i));
                }

                //add row to the array list data
                data.add(row);
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getSQLState());
        }

        //return data array list with all the rows
        return data;
    }
}
