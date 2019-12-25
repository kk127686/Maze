package com.smx.view;

import com.smx.model.Point;

import javax.swing.*;
import java.awt.*;

public class PersonInMaze extends JTextField {
    Point point;
    Toolkit toolkit;
    int money;
    public PersonInMaze(){
        toolkit=getToolkit();
        setEditable(false);
        setBorder(null);
        setOpaque(false);
        setToolTipText("点击我，然后按键盘方向键移动");
        requestFocusInWindow();
    }
    public void setAtMazePoint(Point point){
        this.point=point;
    }
    public Point getAtMazePoint(){
        return this.point;
    }
    public void setMoney(int money){
        this.money+=money;
    }
    public void cleanMoney(){
        this.money=0;
    }
    public int getMoney(){
        return this.money;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i=getBounds().width;
        int j=getBounds().height;
        Image image=toolkit.getImage("");
    }
}
