package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainMenu extends PiggyBankGUI implements ActionListener {

    protected JMenu menu;

    public MainMenu() {
        super();
        addMenu();
        JLabel label = new JLabel("Testing");
        add(label);
    }

    public void addMenu() {
        // stub
    }
}
