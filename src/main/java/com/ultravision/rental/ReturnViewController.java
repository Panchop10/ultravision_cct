package com.ultravision.rental;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ReturnViewController implements ActionListener {
    private ArrayList<Rental> rentals;
    private ReturnView view;

    public ReturnViewController(ReturnView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("return")){
            this.returnTitle();
        }
    }

    //Calculate days between 2 dates, returns and int
    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    private void returnTitle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                view.getParentFrame().setLoading(true);

                Rental rental = view.getRentalSelected();
                int fine = 0;
                boolean fineLateness = false;

                //Calculate fine for lateness
                SimpleDateFormat formatter =new SimpleDateFormat("yyyy/MM/dd");
                try {
                    Date dateRental = formatter.parse(rental.getRentalDate());
                    int daysBetween = daysBetween(dateRental, new Date());

                    if(daysBetween > 3){
                        fineLateness = true;
                    }

                } catch (Exception e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }

                ArrayList<ArrayList> data = RentalModel.getStatusId(rental.getStatusRented());
                int statusId = Integer.parseInt(data.get(0).get(0).toString());

                //if status returned is worst than rented, charge a fine.
                int statusDifference = (5-statusId) - (4-view.getStatusReturnedItem().getSelectedIndex());

                //create message to return the item
                String message = "Item returned successfully";

                if (statusDifference>0){
                    message += ", fine for damage = €"+statusDifference*5;
                }

                if(fineLateness){
                    fine += 10;
                    message += ", fine for lateness = €10.";
                }

                fine += statusDifference*5;


                RentalModel.returnTitle(rental.getRentalID(), (view.getStatusReturnedItem().getSelectedIndex()+1), fine);

                //update table
                view.updateCustomerTable();
                view.getIdFormReturn().setText("");
                view.getStatusReturnedItem().setSelectedIndex(0);

                JOptionPane.showMessageDialog(null,
                        message,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                view.getParentFrame().setLoading(false);
            }
        }).start();
    }

    /**
     *
     * @return ListSelectionListener which handles task of fill the item information in the forms of the view
     */
    ListSelectionListener getItemTableListener(){
        final JTable returnTable = view.getReturnTable();
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                //get selected row
                int selectedRow = returnTable.getSelectedRow();

                if (!event.getValueIsAdjusting() && selectedRow > -1) {
                    //get id from first column of the table
                    String stringID = returnTable.getValueAt(selectedRow, 0).toString();

                    //get item from the item ArrayList queried at the creation of the JTable
                    Rental rental = rentals.get(selectedRow);

                    //fill item data in the left form
                    final Rental rentalForThread = rental;
                    new Thread(new Runnable() {
                        Rental rental = rentalForThread;
                        @Override
                        public void run() {
                            view.fillRentalDetails(rental);
                        }
                    }).start();
                }
            }
        };
    }

    String[][] getReturnTable(){
        rentals = RentalModel.findNotReturned();
        ArrayList<String[]> rentalTable = new ArrayList<String[]>();

        for (Rental rental: rentals) {
            String[] itemRow = new String[6];
            itemRow[0] = String.valueOf(rental.getRentalID());
            itemRow[1] = rental.getItemTitle();
            itemRow[2] = rental.getCustomerID();
            itemRow[3] = rental.getRentalDate();
            itemRow[4] = rental.getStatusRented();
            itemRow[5] = "€"+rental.getPrice();

            rentalTable.add(itemRow);
        }


        String[][] arrayTable = rentalTable.toArray(new String[rentalTable.size()][]);
        return arrayTable;

    }

    /**
     *
     * @return get array of string with the name of the rental status
     */
    String[] getItemStatus(){
        ArrayList<ArrayList> subType = RentalModel.getItemStatus();
        String[] subTypeArray = new String[subType.size()];

        for (int i=0; i < subType.size(); i++){
            subTypeArray[i] = subType.get(i).get(1).toString() ;
        }

        return subTypeArray;
    }

    public KeyListener filterReturnListener() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel model = new DefaultTableModel(view.getReturnColumns(), 0);

                for (String[] customer:view.getReturnData()) {
                    //search id
                    if(customer[0].toLowerCase().contains(view.getSearchString())){
                        model.addRow(customer);
                    }
                    //search title
                    else if(customer[1].toLowerCase().contains(view.getSearchString())){
                        model.addRow(customer);
                    }
                    //search customer
                    else if(customer[2].toLowerCase().contains(view.getSearchString())){
                        model.addRow(customer);
                    }
                }
                view.getReturnTable().setModel(model);
                //set column preferred size in our table
                view.setColumnsSize();
            }
        };


    }
}
