package com.custom_todolist.GUI.tabbed;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class PanelTabbed extends JPanel {

    private final ButtonGroup buttonGroup;

    public PanelTabbed() {
        setLayout(new MigLayout("filly,insets 3 10 3 10"));
        buttonGroup = new ButtonGroup();
    }

    public void addTab(JToggleButton item) {
        buttonGroup.add(item);
        add(item);
        repaint();
        revalidate();
    }
}

