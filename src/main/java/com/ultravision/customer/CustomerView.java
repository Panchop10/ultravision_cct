package com.ultravision.customer;

import com.ultravision.payment.Payment;
import com.ultravision.MainView;
import com.ultravision.rental.Rental;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomerView {
    private CustomerViewController customerListener;
    private JTextField searchTextField;
    private JPanel containerPanel;
    private JTextField idFormCustomer;
    private JTextField lastNameFormCustomer;
    private JTextField firstNameFormCustomer;
    private JTextField emailFormCustomer;
    private JTextField phoneFormCustomer;
    private JComboBox subTypeFormCustomer;
    private JTextField cardNumberFormCustomer;
    private JButton createButton;
    private JButton updateButton;
    private JButton clearButton;
    private JTextField subTab_startDate;
    private JTextField subTab_finishDate;
    private JTextField subTab_amountPaid;
    private JTextField subTab_loyaltyPoints;
    private JTextField subTab_currentlyRented;
    private JButton renewSubscriptionButton;
    private JTable customerTable;
    private JTable tableItemsRented;
    private JTable tablePayments;
    private String[] customerColumns = {"ID", "FIRST NAME", "LAST NAME", "EMAIL", "PHONE", "CARD NUMBER", "SUBSCRIPTION"};
    private String[] paymentColumns = {"ITEM", "DETAIL", "PAYMENT DATE", "AMOUNT PAID"};
    private String[] rentalColumns = {"TITLE", "RENTAL DATE", "RETURN DATE", "PRICE"};
    private String[][] customerData;
    private MainView parentFrame;

    public CustomerView(MainView mainView) {
        //Store parent frame
        parentFrame = mainView;

        //Add Listener to the customer table
        ListSelectionModel customerTableModel = customerTable.getSelectionModel();
        customerTableModel.addListSelectionListener(customerListener.getCustomerTableListener());


        //Add listener to search bar
        searchTextField.addKeyListener(customerListener.filterCustomerListener());

        //Add listener to clear button
        clearButton.addActionListener(customerListener);
        clearButton.setActionCommand("clear");

        //Add listener to subscription button
        renewSubscriptionButton.addActionListener(customerListener);
        renewSubscriptionButton.setActionCommand("renew");

        //Add listener to update button
        updateButton.addActionListener(customerListener);
        updateButton.setActionCommand("update");

        //Add listener to createButton button
        createButton.addActionListener(customerListener);
        createButton.setActionCommand("create");

    }

    /**
     * Set width of the columns in the com.ultravision table
     */
    void setColumnsSize() {
        customerTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        customerTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        customerTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        customerTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        customerTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        customerTable.getColumnModel().getColumn(5).setPreferredWidth(180);
        customerTable.getColumnModel().getColumn(6).setPreferredWidth(90);
    }

    /**
     *
     * @return return container panel that contains all the visual parts of the screen but the menu
     */
    public JPanel getContainerPanel(){
        return this.containerPanel;
    }

    private void createUIComponents() {
        customerListener = new CustomerViewController(this);

        //Get all customers in array format to display into JTable
        customerData = customerListener.getCustomersTable();
        customerTable = new JTable(customerData, customerColumns);

        //set column preferred size in our table
        setColumnsSize();

        //turn off table editable
        customerTable.setDefaultEditor(Object.class, null);


        //RENTAL ITEMS TABLE
        String[][] dataItems = new String[0][0];
        tableItemsRented = new JTable(dataItems, rentalColumns);


        //PAYMENTS TABLE
        String[][] dataPayments = new String[0][0];
        tablePayments = new JTable(dataPayments, paymentColumns);

        //Populate JComboBox
        String[] optionsComboBox = customerListener.getSubscriptionFullName();
        //noinspection unchecked
        subTypeFormCustomer = new JComboBox(optionsComboBox);
        subTypeFormCustomer.addActionListener(customerListener);

    }

    public void updateCustomerTable(){
        customerData = customerListener.getCustomersTable();
        DefaultTableModel modelCustomer = new DefaultTableModel(customerColumns, 0);

        //create rows in the model
        for (String[] customer:customerData) {
            modelCustomer.addRow(customer);
        }
        customerTable.setModel(modelCustomer);
        setColumnsSize();
    }

    /**
     *
     * @return array with columns name of the table
     */
    String[] getCustomerColumns() {
        return customerColumns;
    }

    /**
     *
      * @return array with data in the table
     */
    String[][] getCustomerData() {
        return customerData;
    }

    /**
     *
     * @return text written in the search bar
     */
    String getSearchString(){
        return searchTextField.getText().toLowerCase();
    }

    /**
     *
      * @return com.ultravision table as JTable
     */
    JTable getCustomerTable() {
        return customerTable;
    }

    /**
     *
     * @return JTextField of the com.ultravision
     */
    public JTextField getIdFormCustomer() {
        return idFormCustomer;
    }

    /**
     *
     * @return JComboBox with subscription type
     */
    public JComboBox getSubTypeFormCustomer() {
        return subTypeFormCustomer;
    }


    public String getFirstNameFormCustomer() {
        return firstNameFormCustomer.getText();
    }

    public String getLastNameFormCustomer() {
        return lastNameFormCustomer.getText();
    }

    public String getEmailFormCustomer() {
        return emailFormCustomer.getText();
    }

    public String getPhoneFormCustomer() {
        return phoneFormCustomer.getText();
    }

    public String getCardNumberFormCustomer() {
        return cardNumberFormCustomer.getText();
    }

    public MainView getParentFrame() {
        return parentFrame;
    }



    /**
     *
     * @param customer receives customer that fills the data in the left form
     */
    void fillCustomerDetails(Customer customer){
        Customer.Subscription custSubscription = customer.getSubscription();

        //Form
        idFormCustomer.setText(String.valueOf(customer.getCustomerID()));
        firstNameFormCustomer.setText(customer.getFirstName());
        lastNameFormCustomer.setText(customer.getLastName());
        emailFormCustomer.setText(customer.getEmail());
        phoneFormCustomer.setText(customer.getPhone());
        cardNumberFormCustomer.setText(custSubscription.getCardNumber());
        subTypeFormCustomer.setSelectedItem(custSubscription.getTypeFull());

        //Subscription Tab
        subTab_startDate.setText(custSubscription.getStartDate());
        subTab_finishDate.setText(custSubscription.getFinishDate());
        subTab_amountPaid.setText("€"+custSubscription.getAmountPaid());
        subTab_loyaltyPoints.setText(customer.getLoyaltyPoints());
        subTab_currentlyRented.setText(customer.getCurrentlyRented());



        //Create loading form for rentals while the data is retrieved from the database.
        DefaultTableModel modelRentalLoading = new DefaultTableModel(rentalColumns, 0);
        String[] rowLoading = {"Loading...","Loading...","Loading...","Loading..."};
        modelRentalLoading.addRow(rowLoading);
        tableItemsRented.setModel(modelRentalLoading);
        //Create loading form for payments while the data is retrieved from the database.
        DefaultTableModel modelPaymentsLoading = new DefaultTableModel(paymentColumns, 0);
        String[] rowLoading2 = {"Loading...","Loading...","Loading...","Loading..."};
        modelPaymentsLoading.addRow(rowLoading2);
        tablePayments.setModel(modelPaymentsLoading);

        //Items Rented tab
        DefaultTableModel modelRentals = new DefaultTableModel(rentalColumns, 0);

        //create rows in the model
        for (Rental rental:customer.getTitlesRented()) {
            String[] row = new String[4];
            row[0] = rental.getItemTitle();
            row[1] = rental.getRentalDate();
            row[2] = rental.getReturnDate();
            row[3] = "€"+rental.getPrice();

            modelRentals.addRow(row);

        }
        tableItemsRented.setModel(modelRentals);

        //Payments tab
        DefaultTableModel modelPayments = new DefaultTableModel(paymentColumns, 0);

        //create rows in the model
        for (Payment payment:customer.getPayments()) {
            String[] row = new String[4];
            row[0] = payment.getItem();
            row[1] = payment.getDetail();
            row[2] = payment.getDate();
            row[3] = "€"+payment.getTotalPaid();

            modelPayments.addRow(row);

        }
        tablePayments.setModel(modelPayments);

    }

    /**
     * Clears com.ultravision form
     */
    public void clearForm(){
        //FORM
        idFormCustomer.setText("");
        firstNameFormCustomer.setText("");
        lastNameFormCustomer.setText("");
        emailFormCustomer.setText("");
        phoneFormCustomer.setText("");
        cardNumberFormCustomer.setText("");
        subTypeFormCustomer.setSelectedIndex(0);

        //SUBSCRIPTION TAB
        subTab_startDate.setText("");
        subTab_finishDate.setText("");
        subTab_amountPaid.setText("");
        subTab_loyaltyPoints.setText("");
        subTab_currentlyRented.setText("");
    }
}
