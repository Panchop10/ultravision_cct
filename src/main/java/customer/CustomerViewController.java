package customer;

import utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CustomerViewController implements ActionListener {
    ArrayList<Customer> customers;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);

    }

    String[][] getCustomersTable(){
        customers = CustomerModel.findAll();
        ArrayList<String[]> customerTable = new ArrayList<String[]>();

        for (Customer customer: customers) {
            String[] custRow = new String[7];
            custRow[0] = String.valueOf(customer.getCustomerID());
            custRow[1] = customer.getFirstName();
            custRow[2] = customer.getLastName();
            custRow[3] = customer.getEmail();
            custRow[4] = customer.getPhone();
            custRow[5] = customer.getSubscription().getMaskifiedCardNumber();
            custRow[6] = customer.getSubscription().getStatus();

            customerTable.add(custRow);
        }

        String[][] arrayTable = customerTable.toArray(new String[customerTable.size()][]);
        return arrayTable;

    }


    KeyAdapter filterCustomerListener(final CustomerView view){
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel model = new DefaultTableModel(view.getCustomerColumns(), 0);

                for (String[] customer:view.getCustomerData()) {
                    if(customer[1].toLowerCase().contains(view.getSearchString())){
                        model.addRow(customer);
                    }


                }
                view.getCustomerTable().setModel(model);
                //set column preferred size in our table
                view.setColumnsSize();
            }
        };

    }


    /**
     *
     * @param customerTable receives table with the data
     * @return ListSelectionListener which handles task of fill the customer information in the forms of the view
     */
    ListSelectionListener getCustomerTableListener(final JTable customerTable, final CustomerView view){
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                //get selected row
                int selectedRow = customerTable.getSelectedRow();

                if (!event.getValueIsAdjusting() && selectedRow > -1) {
                    //get id from first column of the table
                    String stringID = customerTable.getValueAt(selectedRow, 0).toString();
                    int customerID = Integer.parseInt(stringID);

                    //get customer from the customer ArrayList queried at the creation of the JTable
                    Customer customer = customers.get(selectedRow);

                    //fill customer data in the left form
                    view.fillLeftForm(customer);
                }
            }
        };
    }

    /**
     *
     * @return get array of string with the name of the subscription type
     */
    String[] getSubscriptionFullName(){
        ArrayList<ArrayList> subType = CustomerModel.getSubscriptionType();
        String[] subTypeArray = new String[subType.size()];

        for (int i=0; i < subType.size(); i++){
            subTypeArray[i] = subType.get(i).get(2).toString() ;
        }

        return subTypeArray;
    }

}
