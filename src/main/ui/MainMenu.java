package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainMenu extends PiggyBankGUI implements ActionListener {

    protected JMenu menu;

    public MainMenu() {
        super();
        paneSetUp();
        menu = new JMenu();
        JLabel label = new JLabel("Testing");
        add(label);
    }
}
