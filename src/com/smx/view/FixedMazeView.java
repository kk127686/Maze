package com.smx.view;

import com.smx.model.ChargeOnRoad;
import com.smx.model.MazeByFile;
import com.smx.model.MazeMaker;
import com.smx.model.Point;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FixedMazeView extends MazeView implements ActionListener {
    JButton again;
    public FixedMazeView(Point[][] points) {
        super(points);
        again=new JButton("重走");
        add(again);
        again.setSize(80,30);
        again.setLocation(1,leftY);
        again.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i=points.length;
        int j=points[0].length;
        MazeMaker mazeByFile=new MazeByFile(new File("E:\\09-28-2018-学习\\面向对象程序设计\\课程设计\\smx\\Maze\\软件结果\\迷宫文件\\蜀道迷宫.txt"));
        points=mazeByFile.initMaze();
        initPointXY();
        initView();
        ChargeOnRoad chargeOnRoad=new ChargeOnRoad();
        chargeOnRoad.setMAXMoney(10);
        points=chargeOnRoad.setChargeOnRoad(points,6);
        handleMove.recordTime.stop();
        handleMove.spendTime=0;
        handleMove.isLeave=false;
        personWalker.cleanMoney();
        repaint();
        personWalker.requestFocusInWindow();
    }
}
