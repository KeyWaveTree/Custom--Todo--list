package com.custom_todolist.GUI.drawer;

import com.custom_todolist.GUI.form.TodoBlock;
import com.custom_todolist.GUI.tabbed.WindowsTabbed;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;

public class DrawerBuilder extends SimpleDrawerBuilder {
    //구성요소를 쉽게 빌드 하기 위하여 외부 open source를 사용
    //링크: https://github.com/DJ-Raven/raven-dashboard/tree/main/library

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        //계정 data 경로를 가져와서 세팅하는 class

        //아바타 아이콘을 가져와서 이미지 세팅 database 이용
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource("/com/custom_todolist/GUI/image/profile.png"), 60, 60, 999))
                .setTitle("Name")
                .setDescription("email");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        //menu setting
        String[][] menus={
                {"~MAIN~"},
                {"Dashboard"},

        };



        return new SimpleMenuOption()
                .setMenus(menus)
                .setBaseIconPath("com/custom_todolist/GUI/drawer/icons/*")
                .setIconScale(0.5f)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction menuAction, int index, int i1) {
                        //index 0 click event
                        if(index == 0)
                        {
                            TodoBlock todoBlock = new TodoBlock();
                            WindowsTabbed.getInstance().addTab(todoBlock.getProjectNameText(),todoBlock);
                        }
                        System.out.println("Menu selected"+index+" "+i1);
                    }
                })
                .setMenuValidation(new MenuValidation()
                {
                    @Override
                    public boolean menuValidation(int index, int subIndex)
                    {
//                        if(index==0){return false;}
//                        else if(index==3){return false;}

                        return true;
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }
}
