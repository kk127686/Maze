package com.smx.view;

import com.smx.model.ChargeOnRoad;
import com.smx.model.MazeByRandom;
import com.smx.model.Point;

import javax.swing.*;
import java.awt.*;

public class IntegrationView extends JFrame {

    public JMenuBar jMenuBar;
    public JMenu menu_file,menu_other;
    public JMenuItem menu_open,menu_change,menu_about,menu_setting;
    Point[][] points;
    JTabbedPane tabbedPane;

    public IntegrationView(){
        initMenu();
        setLayout(new BorderLayout());
        tabbedPane=new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.validate();
        add(tabbedPane, BorderLayout.CENTER);
        setCenter(1500,720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    public void  addMazeVew(String cardName,MazeView mazeView){
        tabbedPane.add(cardName,mazeView);
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
