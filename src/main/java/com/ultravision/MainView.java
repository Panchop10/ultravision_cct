package com.ultravision;

import com.ultravision.MainController;
import com.ultravision.customer.CustomerView;
import com.ultravision.item.ItemView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private MainController mainController;
    private JPanel mainPanel;
    private JButton customersMenu;
    private JButton itemsMenu;
    private JButton rentalsMenu;
    private JButton returnMenu;
    private JLabel customer_label;
    private JPanel menuPanel;
    private JPanel contentPanel;
    private JProgressBar loadingBar;

    public MainView(){
        this.setTitle("UltraVision");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create customer.main controller
        mainController = new MainController(this);

        //Put Customer view as default view for the system
        contentPanel.add(new CustomerView(this).getContainerPanel());

        this.pack();
        this.setVisible(true);

        //LISTENER
        //Add listener to menu buttons
        customersMenu.addActionListener(mainController);
        customersMenu.setActionCommand("customerMenu");

        itemsMenu.addActionListener(mainController);
        itemsMenu.setActionCommand("itemsMenu");

        rentalsMenu.addActionListener(mainController);
        rentalsMenu.setActionCommand("rentalMenu");

        returnMenu.addActionListener(mainController);
        returnMenu.setActionCommand("returnMenu");
    }

    public void setLoading(boolean value){
        loadingBar.setVisible(value);
    }

    public void setContentPanel(JPanel newPanel) {
        contentPanel.removeAll();
        contentPanel.add(newPanel);
        contentPanel.revalidate();
    }

    public void setActiveMenu(String menu){
        Color defaultBackground = new Color(187, 187,187);
        Color defaultForeground = new Color(60, 63,65);

        Color activeForeground = new Color(254, 169, 18);
        Color activeBackground = new Color(14, 63, 169);


        //Put all the buttons in normal colour
        customersMenu.setForeground(defaultForeground);
        customersMenu.setBackground(defaultBackground);

        itemsMenu.setForeground(defaultForeground);
        itemsMenu.setBackground(defaultBackground);

        rentalsMenu.setForeground(defaultForeground);
        rentalsMenu.setBackground(defaultBackground);

        returnMenu.setForeground(defaultForeground);
        returnMenu.setBackground(defaultBackground);

        //Put the selected one in active colour
        switch (menu){
            case "customerMenu":
                customersMenu.setForeground(activeForeground);
                customersMenu.setBackground(activeBackground);
                break;
            case "itemsMenu":
                itemsMenu.setForeground(activeForeground);
                itemsMenu.setBackground(activeBackground);
                break;
            case "rentalMenu":
                rentalsMenu.setForeground(activeForeground);
                rentalsMenu.setBackground(activeBackground);
                break;
            case "returnMenu":
                returnMenu.setForeground(activeForeground);
                returnMenu.setBackground(activeBackground);
                break;
            default:
                break;
        }
    }
}
