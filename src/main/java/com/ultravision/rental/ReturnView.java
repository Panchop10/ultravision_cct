package com.ultravision.rental;

import com.ultravision.MainView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReturnView {
    private ReturnViewController returnListener;
    private JPanel containerPanel;
    private MainView parentFrame;
    private JTextField searchBarReturnForm;
    private JButton returnItemButton;
    private JTextField rentIdForm;
    private JComboBox itemStatusForm;
    private JTable returnTable;
    private String[] returnColumns = {"ID", "ITEM TITLE", "CUSTOMER MEMBERSHIP", "RENTAL DATE", "STATUS RENTED", "PRICE"};
    private String[][] returnData;
    private Rental rentalSelected;

    public ReturnView(MainView mainView){
        //Store parent frame
        parentFrame = mainView;

        //Add Listener to the items table
        ListSelectionModel itemTableModel = returnTable.getSelectionModel();
        itemTableModel.addListSelectionListener(returnListener.getItemTableListener());

        //Add listener to search bar
        searchBarReturnForm.addKeyListener(returnListener.filterReturnListener());

        //Add listener to rent button
        returnItemButton.addActionListener(returnListener);
        returnItemButton.setActionCommand("return");

    }

    /**
     * Set width of the columns in the return table
     */
    void setColumnsSize() {
        returnTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        returnTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        returnTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        returnTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        returnTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        returnTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    }

    /**
     *
     * @return return container panel that contains all the visual parts of the screen but the menu
     */
    public JPanel getContainerPanel(){
        return this.containerPanel;
    }

    private void createUIComponents() {
        returnListener = new ReturnViewController(this);

        //Get all customers in array format to display into JTable
        returnData = returnListener.getReturnTable();
        returnTable = new JTable(returnData, returnColumns);

        setColumnsSize();

        //turn off table editable
        returnTable.setDefaultEditor(Object.class, null);

        //Populate JComboBox
        //ITEM TYPE
        String[] optionsComboBox = returnListener.getItemStatus();
        //noinspection unchecked
        itemStatusForm = new JComboBox(optionsComboBox);
        itemStatusForm.addActionListener(returnListener);
    }

    public void updateCustomerTable(){
        returnData = returnListener.getReturnTable();
        DefaultTableModel modelCustomer = new DefaultTableModel(returnColumns, 0);

        //create rows in the model
        for (String[] customer:returnData) {
            modelCustomer.addRow(customer);
        }
        returnTable.setModel(modelCustomer);
        setColumnsSize();
    }

    /**
     *
     * @return array with columns name of the table
     */
    String[] getReturnColumns() {
        return returnColumns;
    }

    /**
     *
     * @return array with data in the table
     */
    String[][] getReturnData() {
        return returnData;
    }

    /**
     *
     * @return text written in the search bar
     */
    String getSearchString(){
        return searchBarReturnForm.getText().toLowerCase();
    }

    /**
     *
     * @return return table as JTable
     */
    JTable getReturnTable() {
        return returnTable;
    }

    /**
     *
     * @return JTextField of the return id
     */
    public JTextField getIdFormReturn() {
        return rentIdForm;
    }

    /**
     *
     * @return JComboBox with subscription type
     */
    public JComboBox getStatusReturnedItem() {
        return itemStatusForm;
    }

    public MainView getParentFrame() {
        return parentFrame;
    }

    public Rental getRentalSelected() { return rentalSelected; }


    /**
     *
     * @param rental receives rental that fills the data in the left form
     */
    void fillRentalDetails(Rental rental){
        //Store rental in a temporary object
        rentalSelected = rental;

        //Form
        rentIdForm.setText(String.valueOf(rental.getRentalID()));
        itemStatusForm.setSelectedItem(rental.getStatusRented());

    }

}
