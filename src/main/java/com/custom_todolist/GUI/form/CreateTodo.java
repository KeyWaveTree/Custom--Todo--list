package com.custom_todolist.GUI.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTodo extends JFrame {

    private JPanel createTodo;
    private  JPanel editerText;
    private JButton newCreate;
    private JButton cancel;
    private JTextField editerName;
    private JTextField taskEditer;

    public CreateTodo()
    {
        //여기에서는 블록을 새롭게 만들 수 있다. - 실질적인 블록이 아닌 블록을 만드는 단계
        //db하고 연동
        createTodo.setPreferredSize(new Dimension(550, 185));
        editerText.setPreferredSize(new Dimension(550, 185));
        setSize(550, 185);

        setContentPane(createTodo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton getCreateButton()
    {
        return this.newCreate;
    }

    public JButton getCancilButton()
    {
        return this.cancel;
    }

    public String getEditerText()
    {
        return this.editerName.getText();
    }

    public String getTaskText()
    {
        return this.taskEditer.getText();
    }
}
