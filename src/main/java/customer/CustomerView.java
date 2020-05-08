package customer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomerView {
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
    private JComboBox comboBox1;
    private JTextField textField7;
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
    private String[] column = {"ID", "FIRST NAME", "LAST NAME", "EMAIL", "PHONE", "CREDIT CARD", "SUBSCRIPTION"};
    private String[][] data = {{"1", "Francisco", "Olivares", "francisco@cct.ie", "0838271885", "XXXX-XXXX-XXXX-0987", "Active"},
            {"2", "Jose", "Najera", "jose@cct.ie", "0837481123", "XXXX-XXXX-XXXX-3452", "Active"},
            {"3", "Valentina", "Quiroga", "valentina@cct.ie", "0837277491", "XXXX-XXXX-XXXX-5143", "Inactive"}};

    public CustomerView() {

        customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && customerTable.getSelectedRow() > -1) {
                    // print first column value from selected row
                    idFormCustomer.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 0).toString());
                    firstNameFormCustomer.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 1).toString());
                    lastNameFormCustomer.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 2).toString());
                    emailFormCustomer.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 3).toString());
                    phoneFormCustomer.setText(customerTable.getValueAt(customerTable.getSelectedRow(), 4).toString());
                }
            }
        });
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel model = new DefaultTableModel(column, 0);

                for (String[] customer:data) {
                    if(customer[1].toLowerCase().contains(searchTextField.getText().toLowerCase())){
                        model.addRow(customer);
                    }


                }
                customerTable.setModel(model);
                //set column preferred size in our table
                setColumnsSize();
            }
        });
    }

    private void setColumnsSize() {
        customerTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        customerTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        customerTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        customerTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        customerTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        customerTable.getColumnModel().getColumn(5).setPreferredWidth(180);
        customerTable.getColumnModel().getColumn(6).setPreferredWidth(90);
    }

    public JPanel getContainerPanel(){
        return this.containerPanel;
    }

    private void createUIComponents() {

        customerTable = new JTable(data, column);

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

    }
}
