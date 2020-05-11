package com.ultravision.rental;

import com.ultravision.MainView;
import com.ultravision.customer.Customer;
import com.ultravision.item.Item;

import javax.swing.*;

public class RentalView {
    private RentalViewController rentalListener;
    private JTable customerTable;
    private JTable itemTable;
    private JTextField searchCustomerTextField;
    private JTextField searchItemTextField;
    private JTextField idCustomerForm;
    private JTextField loyaltyPointsForm;
    private JTextField idItemForm;
    private JTextField titleItemForm;
    private JTextField subscriptionTypeForm;
    private JButton rentalButton;
    private MainView parentFrame;
    private JPanel containerPanel;
    private JTextField typeItemForm;
    private String[] itemColumns = {"ID", "TITLE", "UID", "PRICE"};
    private String[][] itemData;
    private String[] customerColumns = {"ID", "FIRST NAME", "LAST NAME", "EMAIL"};
    private String[][] customerData;


    public RentalView(MainView mainView){
        //Store parent frame
        parentFrame = mainView;

        //Add Listener to the items table
        ListSelectionModel itemTableModel = itemTable.getSelectionModel();
        itemTableModel.addListSelectionListener(rentalListener.getItemTableListener());

        //Add Listener to the customer table
        ListSelectionModel customerTableModel = customerTable.getSelectionModel();
        customerTableModel.addListSelectionListener(rentalListener.getCustomerTableListener());


        //Add listener to search bar
        searchCustomerTextField.addKeyListener(rentalListener.filterCustomerListener());
        searchItemTextField.addKeyListener(rentalListener.filterItemListener());

        //Add listener to rent button
        rentalButton.addActionListener(rentalListener);
        rentalButton.setActionCommand("rent");
    }

    /**
     * Set width of the columns in the com.ultravision table
     */
    void setColumnsSize() {
        customerTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        customerTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        customerTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        customerTable.getColumnModel().getColumn(3).setPreferredWidth(150);

        itemTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        itemTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        itemTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        itemTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    }

    /**
     *
     * @return return container panel that contains all the visual parts of the screen but the menu
     */
    public JPanel getContainerPanel(){
        return this.containerPanel;
    }

    private void createUIComponents() {
        rentalListener = new RentalViewController(this);

        //Get all customers in array format to display into JTable
        customerData = rentalListener.getCustomersTable();
        customerTable = new JTable(customerData, customerColumns);

        //turn off table editable
        customerTable.setDefaultEditor(Object.class, null);

        //Get all items in array format to display into JTable
        itemData = rentalListener.getItemsTable();
        itemTable = new JTable(itemData, itemColumns);

        //turn off table editable
        itemTable.setDefaultEditor(Object.class, null);

        //set correct size of the columns of both tables
        setColumnsSize();
    }

    public JTable getItemTable() {
        return itemTable;
    }

    public String[][] getItemData() {
        return itemData;
    }

    public JTable getCustomerTable() {
        return customerTable;
    }

    public String[][] getCustomerData() {
        return customerData;
    }

    public String[] getItemColumns() {
        return itemColumns;
    }

    public String[] getCustomerColumns() {
        return customerColumns;
    }

    public JTextField getIdCustomerForm() {
        return idCustomerForm;
    }

    public JTextField getLoyaltyPointsForm() {
        return loyaltyPointsForm;
    }

    public JTextField getIdItemForm() {
        return idItemForm;
    }

    public JTextField getTitleItemForm() {
        return titleItemForm;
    }

    public JTextField getSubscriptionTypeForm() {
        return subscriptionTypeForm;
    }

    public MainView getParentFrame() {
        return parentFrame;
    }



    /**
     *
     * @param item receives item that fills the data in the left form
     */
    void fillItemDetails(Item item){
        //Form
        idItemForm.setText(String.valueOf(item.getItemID()));
        titleItemForm.setText(item.getTitle());
        typeItemForm.setText(item.getItemType());

    }

    /**
     *
     * @param customer receives customer that fills the data in the left form
     */
    void fillCustomerDetails(Customer customer){
        //Form
        idCustomerForm.setText(String.valueOf(customer.getCustomerID()));
        subscriptionTypeForm.setText(customer.getSubscription().getTypeFull());
        loyaltyPointsForm.setText(customer.getLoyaltyPoints());

    }

    public String getSearchStringItem() {
        return searchItemTextField.getText();
    }

    public String getSearchStringCustomer() {
        return searchCustomerTextField.getText();
    }
}
