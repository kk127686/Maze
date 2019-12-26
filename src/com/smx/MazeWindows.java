package com.smx;

import com.smx.model.ChargeOnRoad;
import com.smx.model.MazeByRandom;
import com.smx.model.MazeMaker;
import com.smx.model.Point;
import com.smx.view.MazeView;
import com.smx.view.RandomMazeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeWindows extends JFrame {

    MazeView mazeView;
    public JMenuBar jMenuBar;
    public JMenu menu_file,menu_other;
    public JMenuItem menu_open,menu_change,menu_about,menu_setting;
    Point[][] points;
    public MazeWindows(){
        MazeMaker mazeByRandom = new MazeByRandom(25, 50);
        points = mazeByRandom.initMaze();
        ChargeOnRoad chargeOnRoad = new ChargeOnRoad();
        points = chargeOnRoad.setChargeOnRoad(points, 5);

        initMenu();
        setLayout(new BorderLayout());
        setCenter(1500,720);

        mazeView = new RandomMazeView(points);
        add(mazeView,BorderLayout.CENTER);

        initMenuEvent();
        setVisible(true);
    }

    private void initMenuEvent() {
        menu_change.addActionListener(e -> {
            mazeView = new RandomMazeView(points);
            int i = points.length;
            int j = points[0].length;
            MazeByRandom mazeByRandom = new MazeByRandom(i, j);
            points = mazeByRandom.initMaze();
            mazeView.initPointXY();
            mazeView.initView();
            ChargeOnRoad chargeOnRoad = new ChargeOnRoad();
            points = chargeOnRoad.setChargeOnRoad(points, 20);
            mazeView.handleMove.recordTime.stop();
            mazeView.handleMove.spendTime = 0;
            mazeView.handleMove.showTime.setText("0");
            mazeView.handleMove.isLeave = false;
            mazeView.personWalker.cleanMoney();
            mazeView.repaint();
            mazeView.personWalker.requestFocusInWindow();

            add(mazeView,BorderLayout.CENTER);
        });
    }
    private void setCenter(int width,int height){
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int screemWidth=(int)dimension.getWidth();
        int screenHeight=(int)dimension.getHeight();
        int xPos=(screemWidth-width)/2;
        int yPos=(screenHeight-height)/2;
        setBounds(xPos,yPos,width,height);
    }
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
}
