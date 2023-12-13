package com.custom_todolist.GUI.tabbed;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TabbedItem  extends JToggleButton {

    protected final TabbedForm component;

    public TabbedItem(String name, TabbedForm component) {
        this.component = component;
        init(name);
    }

    private void init(String name) {
        setLayout(new MigLayout("", "[]10[]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "arc:10;"
                + "margin:2,8,2,5");
        JButton cmd = new JButton(new FlatSVGIcon("com/custom_todolist/GUI/svg/close.svg", 0.8f));
        cmd.addActionListener((actionEvent) -> {WindowsTabbed.getInstance().removeTab(this); });

        cmd.putClientProperty(FlatClientProperties.STYLE, ""
                + "margin:3,3,3,3;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "arc:999;");
        add(new JLabel(name));
        add(cmd, BorderLayout.EAST);
    }

    @Override
    public void paint(Graphics demographics) {
        super.paint(demographics);
        if (!isSelected() && getParent().getComponentZOrder(this) != getParent().getComponentCount() - 1) {
            Graphics2D g2 = (Graphics2D) demographics.create();
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(UIManager.getColor("Component.borderColor"));
            float m = UIScale.scale(5);
            float s = UIScale.scale(1);
            g2.fill(new Rectangle2D.Double(getWidth() - s, m, s, getHeight() - m * 2));
            g2.dispose();
        }
    }
}
