package com.ultravision.customer;

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
    private ArrayList<Customer> customers;
    private CustomerView view;

    public CustomerViewController(CustomerView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "clear":
                view.clearForm();
                break;
            case "renew":
                this.renewController();
                break;
            case "update":
                if(this.validateFormController("update")){
                    this.updateCustomer();
                }
                break;
            case "create":
                if(this.validateFormController("create")){
                    this.createCustomer();
                }
                break;
            default:
                break;
        }

    }

    /**
     * Validate if the data present in the form is valid in order to renew the subscription
     */
    private void renewController() {
        boolean validation = false;
        String idCustomer = view.getIdFormCustomer().getText();

        if(idCustomer.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Please select a com.ultravision.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (
                    null,
                    "Would you like to renew "
                            +view.getSubTypeFormCustomer().getSelectedItem()
                            +" subscription for 30 days more?",
                    "Warning",
                    dialogButton
            );
            validation = dialogResult == JOptionPane.YES_OPTION;
        }

        //if all the validations were pass renew the subscription
        if (validation){
            final String idCustomerThread = idCustomer;
            final int subscriptionTypeThread = view.getSubTypeFormCustomer().getSelectedIndex();
            new Thread(new Runnable() {
                String custID = idCustomerThread;
                int subscriptionType = subscriptionTypeThread;
                @Override
                public void run() {
                    view.getParentFrame().setLoading(true);
                    Customer customer = CustomerModel.findCustomer(Integer.parseInt(custID));
                    customer.getSubscription().renew(subscriptionType);
                    //update com.ultravision info
                    customer = CustomerModel.findCustomer(Integer.parseInt(custID));

                    //update com.ultravision table
                    view.updateCustomerTable();
                    //fill form with new data
                    view.fillCustomerDetails(customer);

                    JOptionPane.showMessageDialog(null,
                            "Subscription renewed.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    view.getParentFrame().setLoading(false);
                }
            }).start();
        }
    }

    /**
     * handles customer update
     */
    private void updateCustomer(){
        final String idCustomerThread = view.getIdFormCustomer().getText();
        final int subscriptionTypeThread = view.getSubTypeFormCustomer().getSelectedIndex();
        new Thread(new Runnable() {
            String custID = idCustomerThread;
            @Override
            public void run() {
                view.getParentFrame().setLoading(true);
                Customer customer = CustomerModel.findCustomer(Integer.parseInt(custID));
                String firstName = view.getFirstNameFormCustomer();
                String lastName = view.getLastNameFormCustomer();
                String email = view.getEmailFormCustomer();
                String phone = view.getPhoneFormCustomer();

                customer.update(firstName, lastName, email, phone);
                //update customer info
                customer = CustomerModel.findCustomer(Integer.parseInt(custID));

                //update customer table
                view.updateCustomerTable();

                //fill form with new data
                view.fillCustomerDetails(customer);

                JOptionPane.showMessageDialog(null,
                        "Customer Updated",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                view.getParentFrame().setLoading(false);
            }
        }).start();
    }

    /**
     * handles customer creation
     */
    private void createCustomer(){
        final int subscriptionTypeThread = view.getSubTypeFormCustomer().getSelectedIndex();
        new Thread(new Runnable() {
            int subscriptionType = subscriptionTypeThread;
            @Override
            public void run() {
                view.getParentFrame().setLoading(true);
                String firstName = view.getFirstNameFormCustomer();
                String lastName = view.getLastNameFormCustomer();
                String email = view.getEmailFormCustomer();
                String phone = view.getPhoneFormCustomer();
                String creditCard = view.getCardNumberFormCustomer();

                Customer newCustomer = new Customer(firstName, lastName, email, phone, creditCard, subscriptionType);
                int newID = newCustomer.getCustomerID();

                //update customer info
                newCustomer = CustomerModel.findCustomer(newID);

                //update customer table
                view.updateCustomerTable();

                //fill form with new data
                view.fillCustomerDetails(newCustomer);

                JOptionPane.showMessageDialog(null,
                        "Customer Created",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                view.getParentFrame().setLoading(false);
            }
        }).start();
    }

    /**
     * Validate if the data present in the form is valid in order to update the com.ultravision
     */
    private boolean validateFormController(String action) {
        boolean validation = false;
        String idCustomer = view.getIdFormCustomer().getText();

        //REGEX VALIDATORS
        String regexName = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
        String regexEmail = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\." +
                "[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi" +
                "|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";
        String regexPhone = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        String regexCreditCard = "^((4\\d{3})|(5[1-5]\\d{2})|(6011))-?\\d{4}-?\\d{4}-?\\d{4}|3[4,7]\\d{13}$";

        if(idCustomer.isEmpty() && action.equals("update")){
            JOptionPane.showMessageDialog(null,
                    "Please select a customer.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getFirstNameFormCustomer().matches(regexName)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid first name",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getLastNameFormCustomer().matches(regexName)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid last name",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getEmailFormCustomer().matches(regexEmail)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid email",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getPhoneFormCustomer().matches(regexPhone)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid phone number (min 9 digits, max 12 digits)",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getCardNumberFormCustomer().matches(regexCreditCard)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid credit card",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
            String message;
            if (action.equals("update")){
                message = "Would you like to update this customer?";
            }
            else if(action.equals("create")){
                message = "Would you like to create this new customer?";
            }
            else{
                message = "Unexpected action";
            }
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (
                    null,
                    message,
                    "Warning",
                    dialogButton
            );
            validation = dialogResult == JOptionPane.YES_OPTION;
        }

        return validation;
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
                    if(customer[0].toLowerCase().contains(view.getSearchString())){
                        model.addRow(customer);
                    }
                    //search first name
                    else if(customer[1].toLowerCase().contains(view.getSearchString())){
                        model.addRow(customer);
                    }
                    //search last name
                    else if(customer[2].toLowerCase().contains(view.getSearchString())){
                        model.addRow(customer);
                    }
                    //search email
                    else if(customer[3].toLowerCase().contains(view.getSearchString())){
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
     * @return ListSelectionListener which handles task of fill the customer information in the forms of the view
     */
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
