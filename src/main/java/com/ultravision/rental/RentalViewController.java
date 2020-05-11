package com.ultravision.rental;

import com.ultravision.customer.Customer;
import com.ultravision.customer.CustomerModel;
import com.ultravision.item.Item;
import com.ultravision.item.ItemModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class RentalViewController implements ActionListener {
    private ArrayList<Item> items;
    private ArrayList<Customer> customers;
    private RentalView view;

    public RentalViewController(RentalView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("rent")){
            this.rentTitle();
        }
    }

    private void rentTitle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                view.getParentFrame().setLoading(true);

                Customer customer = CustomerModel.findCustomer(Integer.parseInt(view.getIdCustomerForm().getText()));
                Item item = ItemModel.findItem(Integer.parseInt(view.getIdItemForm().getText()));
                boolean isEntitledToRent = true;

                if(customer.getSubscription().getStatus().equals("Expired")){
                    JOptionPane.showMessageDialog(null,
                            "Customer subscription is expired.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    isEntitledToRent = false;
                }
                else if(customer.getSubscription().getTypeFull().equals("Music Lovers")){
                    if(item.getItemType().equals("Movies") || item.getItemType().equals("TV Box Set")){
                        JOptionPane.showMessageDialog(null,
                                "Customer subscription does not include this title.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                        isEntitledToRent = false;
                    }
                }
                else if(customer.getSubscription().getTypeFull().equals("Video Lovers")){
                    if(!item.getItemType().equals("Movies")){
                        JOptionPane.showMessageDialog(null,
                                "Customer subscription does not include this title.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                        isEntitledToRent = false;
                    }
                }
                else if(customer.getSubscription().getTypeFull().equals("TV Lovers")){
                    if(!item.getItemType().equals("TV Box Set")){
                        JOptionPane.showMessageDialog(null,
                                "Customer subscription does not include this title.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                        isEntitledToRent = false;
                    }
                }

                if(isEntitledToRent){
                    if(Integer.parseInt(customer.getLoyaltyPoints())>=100){
                        RentalModel.rentTitle(item.getItemID(), customer.getCustomerID(), -100, 0);

                        JOptionPane.showMessageDialog(null,
                                "Item Rented for free with 100 loyalty points",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        RentalModel.rentTitle(item.getItemID(), customer.getCustomerID(), 10, item.getRentalPrice());
                        JOptionPane.showMessageDialog(null,
                                "Item Rented for €"+item.getRentalPrice()+", Loyalty Points +10",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }



                }





                view.getParentFrame().setLoading(false);
            }
        }).start();

    }

    /**
     *
     * @return ListSelectionListener which handles task of fill the item information in the forms of the view
     */
    @SuppressWarnings("Duplicates")
    ListSelectionListener getItemTableListener(){
        final JTable itemTable = view.getItemTable();
        return new ListSelectionListener() {
            @SuppressWarnings("Duplicates")
            @Override
            public void valueChanged(ListSelectionEvent event) {
                //get selected row
                int selectedRow = itemTable.getSelectedRow();

                if (!event.getValueIsAdjusting() && selectedRow > -1) {
                    //get id from first column of the table
                    String stringID = itemTable.getValueAt(selectedRow, 0).toString();

                    //get item from the item ArrayList queried at the creation of the JTable
                    Item item = items.get(selectedRow);

                    //fill item data in the left form
                    final Item itemForThread = item;
                    new Thread(new Runnable() {
                        Item item = itemForThread;
                        @Override
                        public void run() {
                            view.fillItemDetails(item);
                        }
                    }).start();
                }
            }
        };
    }

    /**
     *
     * @return filters the customer table depending on the text written in the search bar
     */
    KeyAdapter filterCustomerListener(){
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel model = new DefaultTableModel(view.getCustomerColumns(), 0);

                for (String[] customer:view.getCustomerData()) {
                    //search id
                    if(customer[0].toLowerCase().contains(view.getSearchStringCustomer())){
                        model.addRow(customer);
                    }
                    //search first name
                    else if(customer[1].toLowerCase().contains(view.getSearchStringCustomer())){
                        model.addRow(customer);
                    }
                    //search last name
                    else if(customer[2].toLowerCase().contains(view.getSearchStringCustomer())){
                        model.addRow(customer);
                    }
                    //search email
                    else if(customer[3].toLowerCase().contains(view.getSearchStringCustomer())){
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
     * @return filters the item table depending on the text written in the search bar
     */
    KeyAdapter filterItemListener(){
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel model = new DefaultTableModel(view.getItemColumns(), 0);

                for (String[] item:view.getItemData()) {
                    //search id
                    if(item[0].toLowerCase().contains(view.getSearchStringItem())){
                        model.addRow(item);
                    }
                    //search title
                    else if(item[1].toLowerCase().contains(view.getSearchStringItem())){
                        model.addRow(item);
                    }
                    //search uid
                    else if(item[2].toLowerCase().contains(view.getSearchStringItem())){
                        model.addRow(item);
                    }
                }
                view.getItemTable().setModel(model);
                //set column preferred size in our table
                view.setColumnsSize();
            }
        };

    }

    /**
     *
     * @return ListSelectionListener which handles task of fill the customer information in the forms of the view
     */
    @SuppressWarnings("Duplicates")
    ListSelectionListener getCustomerTableListener(){
        final JTable customerTable = view.getCustomerTable();
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                //get selected row
                int selectedRow = customerTable.getSelectedRow();

                if (!event.getValueIsAdjusting() && selectedRow > -1) {
                    //get id from first column of the table
                    String stringID = customerTable.getValueAt(selectedRow, 0).toString();

                    //get customer from the customer ArrayList queried at the creation of the JTable
                    Customer customer = customers.get(selectedRow);

                    //fill customer data in the left form
                    final Customer customerForThread = customer;
                    new Thread(new Runnable() {
                        Customer cust = customerForThread;
                        @Override
                        public void run() {
                            view.fillCustomerDetails(cust);
                        }
                    }).start();
                }
            }
        };
    }

    String[][] getItemsTable(){
        items = ItemModel.findAll();
        ArrayList<String[]> itemTable = new ArrayList<String[]>();

        for (Item item: items) {
            String[] itemRow = new String[4];
            itemRow[0] = String.valueOf(item.getItemID());
            itemRow[1] = item.getTitle();
            itemRow[2] = item.getUID();
            itemRow[3] = "€"+item.getRentalPrice();

            itemTable.add(itemRow);
        }

        String[][] arrayTable = itemTable.toArray(new String[itemTable.size()][]);
        return arrayTable;

    }

    String[][] getCustomersTable(){
        customers = CustomerModel.findAll();
        ArrayList<String[]> customerTable = new ArrayList<String[]>();

        for (Customer customer: customers) {
            String[] custRow = new String[4];
            custRow[0] = String.valueOf(customer.getCustomerID());
            custRow[1] = customer.getFirstName();
            custRow[2] = customer.getLastName();
            custRow[3] = customer.getEmail();

            customerTable.add(custRow);
        }

        String[][] arrayTable = customerTable.toArray(new String[customerTable.size()][]);
        return arrayTable;

    }
}