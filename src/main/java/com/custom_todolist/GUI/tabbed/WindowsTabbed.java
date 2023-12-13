package com.custom_todolist.GUI.tabbed;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import raven.drawer.Drawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowsTabbed  extends JToggleButton {

    private static WindowsTabbed instance;
    private JMenuBar menuBar;
    private PanelTabbed panelTabbed;
    private JPanel body;
    private TabbedForm temp;

    public static WindowsTabbed getInstance() {
        if (instance == null) {
            instance = new WindowsTabbed();
        }
        return instance;
    }

    public void install(JFrame frame, JPanel body) {
        this.body = body;
        menuBar = new JMenuBar();
        menuBar.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderColor:$TitlePane.background;"
                + "border:0,0,0,0");
        panelTabbed = new PanelTabbed();
        panelTabbed.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$TitlePane.background");
        menuBar.add(createDrawerButton());
        menuBar.add(createScroll(panelTabbed));
        frame.setJMenuBar(menuBar);
    }

    public void showTabbed(boolean show) {
        menuBar.setVisible(show);
        if(!show){
            Drawer.getInstance().closeDrawer();
        }
    }

    private JButton createDrawerButton() {
        JButton cmd = new JButton(new FlatSVGIcon("com/custom_todolist/GUI/svg/menu.svg", 0.8f));
        cmd.addActionListener((ae) -> {
            Drawer.getInstance().showDrawer();
        });
        cmd.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "arc:5");
        return cmd;
    }

    private JScrollPane createScroll(Component com) {
        JScrollPane scroll = new JScrollPane(com);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "width:0");
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0");
        return scroll;
    }

    public void addTab(String title, TabbedForm component) {
        TabbedItem item = new TabbedItem(title, component);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showForm(item.component);
            }
        });
        panelTabbed.addTab(item);
        showForm(component);
        item.setSelected(true);
    }

    public void removeTab(TabbedItem tab) {
        if (tab.component.formClose()) {
            if (tab.isSelected()) {
                body.removeAll();
                body.revalidate();
                body.repaint();
            }
            panelTabbed.remove(tab);
            panelTabbed.revalidate();
            panelTabbed.repaint();
        }
    }

    public void showForm(TabbedForm component) {
        body.removeAll();
        body.add(component);
        body.repaint();
        body.revalidate();
        panelTabbed.repaint();
        panelTabbed.revalidate();
        component.formOpen();
        temp = component;
    }
}
