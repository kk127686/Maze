package com.smx.view;

import com.smx.model.ChargeOnRoad;
import com.smx.model.MazeByRandom;
import com.smx.model.Point;
import com.sun.org.apache.regexp.internal.RE;

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
        int i = this.points.length;
        int j = this.points[0].length;
        MazeByRandom mazeByRandom = new MazeByRandom(i, j);
        this.points = mazeByRandom.initMaze();
        initPointXY();
        initView();
        ChargeOnRoad chargeOnRoad = new ChargeOnRoad();
        this.points = chargeOnRoad.setChargeOnRoad(points, 20);
        this.handleMove.recordTime.stop();
        this.handleMove.spendTime = 0;
        this.handleMove.showTime.setText("0");
        this.handleMove.isLeave = false;
        this.personWalker.cleanMoney();
        repaint();
        this.personWalker.requestFocusInWindow();
    }
}
