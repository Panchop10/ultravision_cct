package com.ultravision.item;

import com.ultravision.MainView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ItemView {

    private JPanel containerPanel;
    private ItemViewController itemListener;
    private JTextField searchTextField;
    private JTextField idFormItem;
    private JTextField titleFormItem;
    private JTextField priceFormItem;
    private JTextField genreFormItem;
    private JTextField yearFormItem;
    private JTextField directorFormItem;
    private JComboBox itemTypeFormItem;
    private JComboBox diskTypeFormItem;
    private JButton createButton;
    private JButton updateButton;
    private JButton clearButton;
    private MainView parentFrame;
    private JTable itemTable;
    private String[] itemColumns = {"ID", "TITLE", "UID", "PRICE", "GENRE", "DIRECTOR/BAND", "TYPE"};
    private String[][] itemData;

    public ItemView(MainView mainView){
        //Store parent frame
        parentFrame = mainView;

        //Add Listener to the items table
        ListSelectionModel itemTableModel = itemTable.getSelectionModel();
        itemTableModel.addListSelectionListener(itemListener.getItemTableListener());


        //Add listener to search bar
        searchTextField.addKeyListener(itemListener.filterItemListener());

        //Add listener to clear button
        clearButton.addActionListener(itemListener);
        clearButton.setActionCommand("clear");

        //Add listener to update button
        updateButton.addActionListener(itemListener);
        updateButton.setActionCommand("update");

        //Add listener to createButton button
        createButton.addActionListener(itemListener);
        createButton.setActionCommand("create");
    }

    /**
     *
     * @return return container panel that contains all the visual parts of the screen but the menu
     */
    public JPanel getContainerPanel(){
        return this.containerPanel;
    }

    public JTable getItemTable() {
        return itemTable;
    }

    public String[] getItemColumns() {
        return itemColumns;
    }

    public String[][] getItemData() {
        return itemData;
    }

    public String getIdFormItem() {
        return idFormItem.getText();
    }

    public String getTitleFormItem() {
        return titleFormItem.getText();
    }

    public String getPriceFormItem() {
        return priceFormItem.getText();
    }

    public String getGenreFormItem() {
        return genreFormItem.getText();
    }

    public String getYearFormItem() {
        return yearFormItem.getText();
    }

    public String getDirectorFormItem() {
        return directorFormItem.getText();
    }

    public JComboBox getItemTypeFormItem() {
        return itemTypeFormItem;
    }

    public JComboBox getDiskTypeFormItem() {
        return diskTypeFormItem;
    }

    public MainView getParentFrame() {
        return parentFrame;
    }



    /**
     *
     * @return text written in the search bar
     */
    String getSearchString(){
        return searchTextField.getText().toLowerCase();
    }

    /**
     * Update table with new data from the database
     */
    public void updateItemTable(){
        itemData = itemListener.getItemsTable();
        DefaultTableModel modelCustomer = new DefaultTableModel(itemColumns, 0);

        //create rows in the model
        for (String[] item:itemData) {
            modelCustomer.addRow(item);
        }
        itemTable.setModel(modelCustomer);
        setColumnsSize();
    }

    /**
     * Set width of the columns in the com.ultravision table
     */
    void setColumnsSize() {
        itemTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        itemTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        itemTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        itemTable.getColumnModel().getColumn(3).setPreferredWidth(15);
        itemTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        itemTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        itemTable.getColumnModel().getColumn(6).setPreferredWidth(90);
    }

    private void createUIComponents() {
        itemListener = new ItemViewController(this);

        //Get all items in array format to display into JTable
        itemData = itemListener.getItemsTable();
        itemTable = new JTable(itemData, itemColumns);

        //set column preferred size in our table
        setColumnsSize();

        //turn off table editable
        itemTable.setDefaultEditor(Object.class, null);

        //Populate JComboBox
        //ITEM TYPE
        String[] optionsComboBox = itemListener.getItemType();
        //noinspection unchecked
        itemTypeFormItem = new JComboBox(optionsComboBox);
        itemTypeFormItem.addActionListener(itemListener);

        //DISK TYPE
        DiskType[] diskTypes = DiskType.values();
        String[] diskTypesString = new String[diskTypes.length];
        for(int i=0; i<diskTypes.length; i++){
            diskTypesString[i] = diskTypes[i].toString();
        }
        //noinspection unchecked
        diskTypeFormItem = new JComboBox(diskTypesString);
        diskTypeFormItem.addActionListener(itemListener);
    }

    /**
     *
     * @param item receives item that fills the data in the left form
     */
    void fillItemDetails(Item item){
        //Form
        idFormItem.setText(String.valueOf(item.getItemID()));
        titleFormItem.setText(item.getTitle());
        priceFormItem.setText(String.valueOf(item.getRentalPrice()));
        genreFormItem.setText(item.getGenre());
        yearFormItem.setText(String.valueOf(item.getYearReleased()));
        directorFormItem.setText(item.getDirectorOrBand());

        //fill ComboBox
        diskTypeFormItem.setSelectedItem(item.getDiskType());
        itemTypeFormItem.setSelectedItem(item.getItemType());


    }

    /**
     * Clears com.ultravision form
     */
    public void clearForm(){
        //FORM
        idFormItem.setText("");
        titleFormItem.setText("");
        priceFormItem.setText("");
        genreFormItem.setText("");
        yearFormItem.setText("");
        directorFormItem.setText("");
        diskTypeFormItem.setSelectedIndex(0);
        itemTypeFormItem.setSelectedIndex(0);
    }
}
