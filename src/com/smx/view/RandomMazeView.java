package com.smx.view;

import com.smx.model.ChargeOnRoad;
import com.smx.model.MazeByRandom;
import com.smx.model.Point;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomMazeView extends MazeView implements ActionListener {
    JButton renew;
    public RandomMazeView(Point[][] points){
        super(points);
        renew=new JButton("换迷宫");
        add(renew);
        renew.setSize(80,30);
        renew.setLocation(1,this.leftY);
        renew.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int i = points.length;
        int j = points[0].length;
        MazeByRandom mazeByRandom = new MazeByRandom(i, j);
        points = mazeByRandom.initMaze();
        initPointXY();
        initView();
        ChargeOnRoad chargeOnRoad = new ChargeOnRoad();
        points = chargeOnRoad.setChargeOnRoad(points, 20);
        handleMove.recordTime.stop();
        handleMove.spendTime = 0;
        handleMove.showTime.setText("0");
        handleMove.isLeave = false;
        personWalker.cleanMoney();
        repaint();
        personWalker.requestFocusInWindow();
    }
}
