package customer;

import javax.swing.*;

public class CustomerView {
    private CustomerViewController customerListener;
    private JTable customerTable;
    private JTextField searchTextField;
    private JPanel containerPanel;
    private JPanel leftPanel;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JTextField idFormCustomer;
    private JTextField lastNameFormCustomer;
    private JTextField firstNameFormCustomer;
    private JTabbedPane tabbedPane1;
    private JTextField emailFormCustomer;
    private JTextField phoneFormCustomer;
    private JComboBox subTypeFormCustomer;
    private JTextField cardNumberFormCustomer;
    private JButton createButton;
    private JButton updateButton;
    private JButton clearButton;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JButton renewSubscriptionButton;
    private JTable tableItemsRented;
    private JTable tablePayments;
    private String[] customerColumns = {"ID", "FIRST NAME", "LAST NAME", "EMAIL", "PHONE", "CARD NUMBER", "SUBSCRIPTION"};
    private String[][] customerData;

    public CustomerView() {
        //Add Listener to the customer table
        ListSelectionModel customerTableModel = customerTable.getSelectionModel();
        customerTableModel.addListSelectionListener(customerListener.getCustomerTableListener(customerTable, this));


        //Add listener to search bar
        searchTextField.addKeyListener(customerListener.filterCustomerListener(this));
    }

    /**
     * Set width of the columns in the customer table
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

        //Get all customers in array format to display into JTable
        customerListener = new CustomerViewController();
        customerData = customerListener.getCustomersTable();

        customerTable = new JTable(customerData, customerColumns);

        //set column preferred size in our table
        setColumnsSize();

        //turn off table editable
        customerTable.setDefaultEditor(Object.class, null);


        //RENTAL ITEMS TABLE
        String[][] dataItems = {
                {"Star Wars I", "01/05/2020", "04/05/2020", "€10"},
                {"Star Wars II", "01/05/2020", "04/05/2020", "€10"},
                {"Star Wars III", "01/05/2020", "04/05/2020", "€10"}
        };
        String[] columnItems = {"TITLE", "RENTAL DATE", "EXPECTED RETURN DATE", "PRICE"};

        tableItemsRented = new JTable(dataItems, columnItems);



        //PAYMENTS TABLE
        String[][] dataPayments = {
                {"Rental", "Start Wars I", "01/05/2020", "€10"},
                {"Subscription", "Movie Lovers", "01/05/2020", "€10"},
                {"Fine", "Star Wars II", "01/05/2020", "€10"}
        };
        String[] columnPayments = {"ITEM", "DETAIL", "PAYMENT DATE", "AMOUNT PAID"};

        tablePayments = new JTable(dataPayments, columnPayments);

        //Populate JComboBox
        String[] optionsComboBox = customerListener.getSubscriptionFullName();
        //noinspection unchecked
        subTypeFormCustomer = new JComboBox(optionsComboBox);
        subTypeFormCustomer.addActionListener(customerListener);

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
      * @return customer table as JTable
     */
    JTable getCustomerTable() {
        return customerTable;
    }

    /**
     *
     * @param customer receives customer that fills the data in the left form
     */
    void fillLeftForm(Customer customer){
        idFormCustomer.setText(String.valueOf(customer.getCustomerID()));
        firstNameFormCustomer.setText(customer.getFirstName());
        lastNameFormCustomer.setText(customer.getLastName());
        emailFormCustomer.setText(customer.getEmail());
        phoneFormCustomer.setText(customer.getPhone());
        cardNumberFormCustomer.setText(customer.getSubscription().getCardNumber());
        subTypeFormCustomer.setSelectedItem(customer.getSubscription().typeFull);
    }
}
