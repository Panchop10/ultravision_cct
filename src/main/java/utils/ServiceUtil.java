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

    /**
     *
     * @param query receives the string that is going to be updated/inserted into the database
     */
    public static void databaseUpdate(String query){
        //database credentials
        String url = ServiceUtil.createSQLUrl();
        String user = Database.USER.get();
        String password = Database.PASSWORD.get();

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            //Create connection and execute query
            Statement st = con.createStatement();
            st.executeUpdate(query);
        }
        catch (SQLException ex) {
            System.out.println(ex.getSQLState());
        }
    }

    /**
     *
     * @param query receives the string with the object that is going to be created into the database
     * @return int with the id of the object created
     */
    public static int databaseCreate(String query){
        //database credentials
        String url = ServiceUtil.createSQLUrl();
        String user = Database.USER.get();
        String password = Database.PASSWORD.get();

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            //Notify to the JDBC driver to return the keys.
            PreparedStatement statement = con.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object failed, no rows affected.");
            }

            //Get id key returned
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return (int) generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating object failed, no ID obtained.");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            return 0;
        }
    }
}
