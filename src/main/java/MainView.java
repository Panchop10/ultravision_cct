import customer.CustomerView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JPanel mainPanel;
    private JButton itemsButton;
    private JButton rentalsButton;
    private JButton returnItemButton;
    private JLabel customer_label;
    private JButton customersButton;
    private JPanel menuPanel;
    private JPanel contentPanel;

    MainView(){
        this.setTitle("UltraVision");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        contentPanel.add(new CustomerView().getContainerPanel());

        this.pack();
        this.setVisible(true);
    }

}
