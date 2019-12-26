package com.smx.view;

import javax.swing.*;
import java.awt.*;

public class IntegrationView extends JFrame {

    private JMenuBar jMenuBar;
    private JMenu menu_file,menu_other;
    private JMenuItem menu_open,menu_change,menu_about,menu_setting;

    JTabbedPane tabbedPane;
    public IntegrationView(){
        initMenu();
        setLayout(new BorderLayout());
        tabbedPane=new JTabbedPane(2);
        tabbedPane.validate();
        add(tabbedPane, BorderLayout.CENTER);
        setCenter(1500,720);
        setDefaultCloseOperation(2);
        setVisible(true);
    }

    /**
     * 创建菜单
     */
    private void initMenu() {
        jMenuBar=new JMenuBar();
        menu_file=new JMenu("文件");
        jMenuBar.add(menu_file);

        menu_open=new JMenuItem("打开");
        menu_open.setToolTipText("打开预设迷宫地图");
        menu_change=new JMenuItem("换迷宫");
        menu_change.setToolTipText("刷新地图");
        menu_file.add(menu_open);
        menu_file.add(menu_change);

        menu_other=new JMenu("其他");
        menu_setting=new JMenuItem("设置");
        menu_setting.setToolTipText("软件设置");
        menu_about=new JMenuItem("关于");
        menu_about.setToolTipText("关于");
        menu_other.add(menu_setting);
        menu_other.add(menu_about);
        jMenuBar.add(menu_other);
        setJMenuBar(jMenuBar);
    }
    public void  addMazeVew(String str1,MazeView mazeView){
        tabbedPane.add(str1,mazeView);
        validate();
    }
    private void setCenter(int width,int height){
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int screemWidth=(int)dimension.getWidth();
        int screenHeight=(int)dimension.getHeight();
        int xPos=(screemWidth-width)/2;
        int yPos=(screenHeight-height)/2;
        setBounds(xPos,yPos,width,height);
    }
}
