package payment;

import utils.Database;
import utils.ServiceUtil;

import java.sql.*;
import java.util.ArrayList;

public class PaymentModel {

    public static void getPayments(int customerID){
        String url = ServiceUtil.createSQLUrl();
        String user = Database.USER.get();
        String password = Database.PASSWORD.get();

        String query = "SELECT * FROM staff";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            //Create connection and execute query
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            //Stores properties of a ResultSet object, including column count
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            while (rs.next()) {
                //System.out.println(rs.getString(1));
                for (int i = 1; i <= columnCount; i++){
                    System.out.println(rs.getString(i));
                }
                System.out.println("-------------------");
                //System.out.println(rs.getString(2));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getSQLState());
        }
    }
}
