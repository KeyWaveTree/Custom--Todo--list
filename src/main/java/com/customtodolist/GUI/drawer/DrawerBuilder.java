package com.customtodolist.GUI.drawer;

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
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource("/com/customtodolist/GUI/img/img.jpg"), 60, 60, 999)) //아바타 아이콘을 가져와서 이미지 세팅 database 이용
                .setTitle("Name")
                .setDescription("email or info");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        //menu setting
        String[][] menus={
                {"~MAIN~"},
                {"Dashboard"},
                {"~WEB APP~"},
                {"Email", "Inbox", "Read", "Compost"},
                {"Chat"},
                {"Calendar"},
                {"~COMPONENT~"},
                {"Advanced UI", "Cropper", "Owl Carousel", "Sweet Alert"},
                {"Forms", "Basic Elements", "Advanced Elements", "SEditors", "Wizard"},
                {"~OTHER~"},
                {"Charts", "Apex", "Flot", "Sparkline"},
                {"Icons", "Feather Icons", "Flag Icons", "Mdi Icons"},
                {"Special Pages", "Blank page", "Faq", "Invoice", "Profile", "Pricing", "Timeline"},
                {"Logout"}
        };

        String[] icons = {
                "dashboard.svg",
                "email.svg",
                "chat.svg",
                "calendar.svg",
                "ui.svg",
                "forms.svg",
                "chart.svg",
                "icon.svg",
                "page.svg",
                "logout.svg"
        };

        return new SimpleMenuOption()
                .setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("/com/customtodolist/GUI/drawer/icons")
                .setIconScale(0.45f).addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction menuAction, int index, int i1) {
                        System.out.println("Menu selected"+index+" "+i1);
                    }
                })
                .setMenuValidation(new MenuValidation()
                {
                    @Override
                    public boolean menuValidation(int index, int subIndex)
                    {
                        if(index==0){return false;}
                        else if(index==3){return false;}
                        return true;
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }
}
