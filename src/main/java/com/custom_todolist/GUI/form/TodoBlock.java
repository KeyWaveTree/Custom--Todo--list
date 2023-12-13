package com.custom_todolist.GUI.form;

import com.custom_todolist.Component.CommonConstants;
import com.custom_todolist.Component.TaskComponent;
import com.custom_todolist.GUI.tabbed.TabbedForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoBlock extends TabbedForm
{
    private JPanel blockPanel;
    private JButton todoCreate;
    private JTextField projectName;
    private JPanel taskPanel;
    private JScrollPane scrollPane;

    //todo 검색 기능
    //블록 프로젝트 저장
    //create todo button
    //블록 조합 기능
    //블록 묶음 저장 기능

    //1단계 블록 생성
    //2단계 블록 드래그 & 드랍
    //3단계 블록끼리 서로 연결 가능
    public TodoBlock()
    {
        CreateTodo createTodo = new CreateTodo();
        JButton todoButton = createTodo.getCreateButton();
        JButton cancilButton = createTodo.getCancilButton();

        add(blockPanel);
        // taskcomponentpanel
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

        // add scrolling to the task panel
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        //이벤트
        todoCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createTodo.setVisible(true);
            }
        });

        todoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskComponent taskComponent = new TaskComponent(taskPanel);
                taskComponent.setTaskField(createTodo.getEditerText());
                taskPanel.add(taskComponent);


                if(taskPanel.getComponentCount() > 1){
                    TaskComponent previousTask = (TaskComponent) taskPanel.getComponent(
                            taskPanel.getComponentCount() - 2);
                    previousTask.getTaskField().setBackground(null);
                }
                // make the task field request focus after creation
                taskComponent.getTaskField().requestFocus();
                repaint();
                revalidate();
            }
        });

        cancilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTodo.dispose(); //메모리 적으로 효율적인 penal 종료
            }
        });
    }

    public String getProjectNameText()
    {
        return this.projectName.getText();
    }
}
