//package com.customtodolist.GUI;
//
//import com.customtodolist.DB.DBManager;
//import com.customtodolist.GUI.drawer.DrawerBuilder;
//import com.formdev.flatlaf.FlatLaf;
//import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
//import com.formdev.flatlaf.themes.FlatMacDarkLaf;
//import raven.drawer.Drawer;
//import raven.popup.GlassPanePopup;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionAdapter;
//import java.util.List;
//import java.util.Map;
//
//@SuppressWarnings("BoundFieldAssignment")
//public class MainScreen extends JFrame{
//
//    private JPanel Main;
//
//    //menu 버튼
//    private JButton showMenuButton;
//
//    private MainScreen()
//    {
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setSize(1920, 1080);
//        setLayout(null);
//
//        //객체 생성 구간
//        Container contentPane = getContentPane();
//        showMenuButton = new JButton();
//
//        //반영 구간
//        GlassPanePopup.install(this);//현재 jFrame을 제공하는 라이브러리에 반영한다.
//        Drawer.getInstance().setDrawerBuilder(new DrawerBuilder()); //만들어진 판을 대상으로 설정하여 빌드하겠다.(대상 요소)
//
//        //배치 구간
//        contentPane.add(showMenuButton);
//
//        displayTodos();
//
//        //이벤트 구간
//        showMenuButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //메뉴 화면을 보여준다.
//                Drawer.getInstance().showDrawer();
//            }
//        });
//
//    }
//
//    private void displayTodos() {
//        List<Map<String, Object>> todoItems = new DBManager().getTodoItems(); // Fetch data from DB
//        int x = 10, y = 10; // Starting coordinates for the first block
//        for (Map<String, Object> item : todoItems) {
//            TodoBlock block = new TodoBlock(item);
//            block.setBounds(x, y, 100, 100); // Set position and size
//            add(block);
//
//            // Update x and y for the next block
//            x += 110; // Move right for the next block
//            if (x + 100 > getWidth()) { // Move to next line if end of frame reached
//                x = 10;
//                y += 110;
//            }
//        }
//
//        // Update the UI
//        revalidate();
//        repaint();
//    }
//
//    private class TodoBlock extends JPanel {
//        private int posX = 0, posY = 0;
//
//        public TodoBlock(Map<String, Object> data) {
//            setLayout(new FlowLayout());
//            setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            setPreferredSize(new Dimension(100, 100));
//            setBackground(Color.LIGHT_GRAY);
//
//            if (data.containsKey("task_name")) { // Adjust "task_name" to match your database column
//                add(new JLabel(data.get("task_name").toString()));
//            }
//
//            addMouseListener(new MouseAdapter() {
//                public void mousePressed(MouseEvent e) {
//                    posX = e.getX();
//                    posY = e.getY();
//                }
//            });
//
//            addMouseMotionListener(new MouseMotionAdapter() {
//                public void mouseDragged(MouseEvent evt) {
//                    Point currCoords = getLocation();
//                    setLocation(currCoords.x + evt.getX() - posX, currCoords.y + evt.getY() - posY);
//                }
//            });
//        }
//    }
//
//    public static void main(String[] args) {
//        //화면 설정
//        FlatRobotoFont.install();
//        FlatLaf.registerCustomDefaultsSource("dashboard.themes");
//        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));
//        FlatMacDarkLaf.setup();
//
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MainScreen().setVisible(true);
//            }
//        });
//    }
//
//
//}

package com.customtodolist.GUI;

// ... (other imports)
import com.customtodolist.DB.DBManager;
import com.customtodolist.GUI.drawer.DrawerBuilder;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MainScreen extends JFrame {
    private JPanel Main;
    private JButton showMenuButton;
    private JPopupMenu menuPopup;

    public MainScreen() throws SQLException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLayout(null);

        initializeComponents();
        createPopupMenu();
    }

    private void initializeComponents() {
        Container contentPane = getContentPane();
        showMenuButton = new JButton("Menu");
        showMenuButton.setBounds(10, 10, 80, 30); // Positioning the menu button

        GlassPanePopup.install(this);
        Drawer.getInstance().setDrawerBuilder(new DrawerBuilder());

        contentPane.add(showMenuButton);

        showMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Menu button clicked"); // Debugging statement
                try {
                    menuPopup.show(showMenuButton, showMenuButton.getWidth(), showMenuButton.getHeight());
                } catch (Exception ex) {
                    ex.printStackTrace(); // Print any exceptions to help diagnose issues
                }
            }
        });

        contentPane.add(showMenuButton);
    }

    private void createPopupMenu() {
        menuPopup = new JPopupMenu();

        try {
            List<Map<String, Object>> todoItems = new DBManager().getTodoItems();
            for (Map<String, Object> item : todoItems) {
                String taskName = new String(item.get("Task").toString().getBytes("ISO-8859-1"), "UTF-8");
                JMenuItem menuItem = new JMenuItem(taskName);
                menuItem.addActionListener(e -> createTodoBlock(item));
                menuPopup.add(menuItem);
            }
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createTodoBlock(Map<String, Object> data) {
        TodoBlock block = new TodoBlock(data);
        // You might want to change the initial position
        block.setBounds(100, 100, 150, 75);
        add(block);

        revalidate();
        repaint();
    }


    private class TodoBlock extends JPanel {
        private int posX = 0, posY = 0;

        public TodoBlock(Map<String, Object> data) {
            super(new BorderLayout()); // Use BorderLayout for component positioning
            int importance = (int) data.get("Importance");
            Color color;
            switch (importance) {
                case 1:
                    color = Color.RED;
                    break;
                case 2:
                    color = Color.ORANGE;
                    break;
                case 3:
                    color = Color.YELLOW;
                    break;
                case 4:
                    color = Color.BLUE;
                    break;
                default:
                    color = Color.LIGHT_GRAY; // Default color if no importance is set
            }
            setBackground(color);

            // Set the block size
            setPreferredSize(new Dimension(200, 100)); // 2:1 horizontal rectangle

            // Display the task name
            if (data.containsKey("Task")) {
                JLabel taskLabel = new JLabel(data.get("Task").toString(), SwingConstants.CENTER);
                taskLabel.setForeground(Color.BLACK); // Text color for visibility
                add(taskLabel, BorderLayout.CENTER); // Add label to the center
            }

            // Mouse listeners for dragging the block
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    posX = e.getX();
                    posY = e.getY();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    Point currCoords = getLocation();
                    setLocation(currCoords.x + evt.getX() - posX, currCoords.y + evt.getY() - posY);
                }
            });
        }
    }



    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("dashboard.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));
        FlatMacDarkLaf.setup();

        SwingUtilities.invokeLater(() -> {
            try {
                new MainScreen().setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
