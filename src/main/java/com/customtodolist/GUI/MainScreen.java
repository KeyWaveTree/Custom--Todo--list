package com.customtodolist.GUI;

import com.customtodolist.DB.DBManager;
import com.customtodolist.GUI.drawer.DrawerBuilder;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("BoundFieldAssignment")
public class MainScreen extends JFrame{

    private JPanel Main;

    //menu 버튼
    private JButton showMenuButton;

    private MainScreen()
    {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1920, 1080);

        //객체 생성 구간
        Container contentPane = getContentPane();
        showMenuButton = new JButton();

        //반영 구간
        GlassPanePopup.install(this);//현재 jFrame을 제공하는 라이브러리에 반영한다.
        Drawer.getInstance().setDrawerBuilder(new DrawerBuilder()); //만들어진 판을 대상으로 설정하여 빌드하겠다.(대상 요소)

        //배치 구간
        contentPane.add(showMenuButton);

        //이벤트 구간
        showMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //메뉴 화면을 보여준다.
                Drawer.getInstance().showDrawer();
            }
        });

    }
    public static void main(String[] args) {
        DBManager.selectTasks();
        //화면 설정
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("dashboard.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));
        FlatMacDarkLaf.setup();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }


}
