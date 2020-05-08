import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

            UIManager.getLookAndFeelDefaults()
                    .put("defaultFont", new Font("Monospaced", Font.BOLD, 14));
        } catch(Exception ignored){}

        new MainView();

    }


}


