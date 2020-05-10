package com.ultravision;

import com.ultravision.customer.CustomerView;
import com.ultravision.item.ItemView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    MainView view;

    public MainController(MainView view){
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "customerMenu":
                view.setActiveMenu("customerMenu");
                view.setLoading(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        view.setContentPanel(new CustomerView(view).getContainerPanel());
                        view.setLoading(false);
                    }
                }).start();
                break;
            case "itemsMenu":
                view.setActiveMenu("itemsMenu");
                view.setLoading(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        view.setContentPanel(new ItemView(view).getContainerPanel());
                        view.setLoading(false);
                    }
                }).start();
                break;
            case "rentalMenu":
                view.setActiveMenu("rentalMenu");
                break;
            case "returnMenu":
                view.setActiveMenu("returnMenu");
                break;
            default:
                break;
        }
    }
}
