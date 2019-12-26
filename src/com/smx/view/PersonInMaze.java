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
        return point;
    }
    public void setMoney(int money){
        this.money+=money;
    }
    public void cleanMoney(){
        money=0;
    }
    public int getMoney(){
        return money;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width=getBounds().width;
        int height=getBounds().height;
        Image image=toolkit.getImage("E:\\09-28-2018-学习\\面向对象程序设计\\课程设计\\smx\\Maze\\软件结果\\迷宫文件\\person.gif");
        g.drawImage(image,0,0,width,height,this);
    }
}
