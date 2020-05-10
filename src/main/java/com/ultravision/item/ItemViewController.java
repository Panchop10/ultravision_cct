package com.ultravision.item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ItemViewController implements ActionListener {
    private ArrayList<Item> items;
    private ItemView view;

    public ItemViewController(ItemView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "clear":
                view.clearForm();
                break;
            case "update":
                if(this.validateFormController("update")){
                    this.updateItem();
                }
                break;
            case "create":
                if(this.validateFormController("create")){
                    this.createItem();
                }
                break;
            default:
                break;
        }
    }

    /**
     * handles customer update
     */
    private void updateItem(){
        final String idItemThread = view.getIdFormItem();
        final int itemType = view.getItemTypeFormItem().getSelectedIndex();
        new Thread(new Runnable() {
            String itemID = idItemThread;
            @Override
            public void run() {
                view.getParentFrame().setLoading(true);
                Item item = ItemModel.findItem(Integer.parseInt(itemID));

                int price = Integer.parseInt(view.getPriceFormItem());
                int year = Integer.parseInt(view.getYearFormItem());
                String director = view.getDirectorFormItem();
                String title = view.getTitleFormItem();
                String genre = view.getGenreFormItem();
                String diskType = view.getDiskTypeFormItem().getSelectedItem().toString();

                //update item info
                item.update(price, year, director, title, genre, diskType, itemType);

                //update item table
                view.updateItemTable();

                //fill form with new data
                item = ItemModel.findItem(Integer.parseInt(itemID));
                view.fillItemDetails(item);

                JOptionPane.showMessageDialog(null,
                        "Item Updated",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                view.getParentFrame().setLoading(false);
            }
        }).start();
    }

    /**
     * handles customer creation
     */
    private void createItem(){
        final int itemTypeThread = view.getItemTypeFormItem().getSelectedIndex();
        new Thread(new Runnable() {
            int itemType = itemTypeThread;
            @SuppressWarnings("Duplicates")
            @Override
            public void run() {
                view.getParentFrame().setLoading(true);

                int price = Integer.parseInt(view.getPriceFormItem());
                int year = Integer.parseInt(view.getYearFormItem());
                String director = view.getDirectorFormItem();
                String title = view.getTitleFormItem();
                String genre = view.getGenreFormItem();
                String diskType = view.getDiskTypeFormItem().getSelectedItem().toString();

                Item newItem = new Item(price, year, director, title, genre, diskType, itemType);
                int newID = newItem.getItemID();

                //update item info
                newItem = ItemModel.findItem(newID);

                //update item table
                view.updateItemTable();

                //fill form with new data
                view.fillItemDetails(newItem);

                JOptionPane.showMessageDialog(null,
                        "Item Created",
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
        String idItem = view.getIdFormItem();

        //REGEX VALIDATORS
        String regexName = "^[a-zA-Z0-9,.':|!_()\\- ]{0,80}$";
        String regexYear = "^\\d{4}$";
        String regexPrice = "^\\d{1,10}$";

        if(idItem.isEmpty() && action.equals("update")){
            JOptionPane.showMessageDialog(null,
                    "Please select an item.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getPriceFormItem().matches(regexPrice)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid price",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getYearFormItem().matches(regexYear)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid year (E.g. 2020)",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getDirectorFormItem().matches(regexName)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid Director/Band name",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getTitleFormItem().matches(regexName)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid title for the item",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if(!view.getGenreFormItem().matches(regexName)){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid genre",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
            String message;
            if (action.equals("update")){
                message = "Would you like to update this item?";
            }
            else if(action.equals("create")){
                message = "Would you like to create this new item?";
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
                    if(item[0].toLowerCase().contains(view.getSearchString())){
                        model.addRow(item);
                    }
                    //search title
                    else if(item[1].toLowerCase().contains(view.getSearchString())){
                        model.addRow(item);
                    }
                    //search genre
                    else if(item[4].toLowerCase().contains(view.getSearchString())){
                        model.addRow(item);
                    }
                    //search director/band
                    else if(item[5].toLowerCase().contains(view.getSearchString())){
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
     * @return ListSelectionListener which handles task of fill the item information in the forms of the view
     */
    ListSelectionListener getItemTableListener(){
        final JTable itemTable = view.getItemTable();
        return new ListSelectionListener() {
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

    String[][] getItemsTable(){
        items = ItemModel.findAll();
        ArrayList<String[]> itemTable = new ArrayList<String[]>();

        for (Item item: items) {
            String[] itemRow = new String[7];
            itemRow[0] = String.valueOf(item.getItemID());
            itemRow[1] = item.getTitle();
            itemRow[2] = item.getUID();
            itemRow[3] = "€"+item.getRentalPrice();
            itemRow[4] = item.getGenre();
            itemRow[5] = item.getDirectorOrBand();
            itemRow[6] = item.getItemType();

            itemTable.add(itemRow);
        }

        String[][] arrayTable = itemTable.toArray(new String[itemTable.size()][]);
        return arrayTable;

    }

    /**
     *
     * @return get array of string with the name of the items type
     */
    String[] getItemType(){
        ArrayList<ArrayList> subType = ItemModel.getItemType();
        String[] subTypeArray = new String[subType.size()];

        for (int i=0; i < subType.size(); i++){
            subTypeArray[i] = subType.get(i).get(1).toString() ;
        }

        return subTypeArray;
    }
}
