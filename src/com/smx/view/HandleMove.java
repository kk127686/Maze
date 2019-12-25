package com.smx.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.smx.model.Point;
import javafx.scene.input.KeyCode;

public class HandleMove extends JPanel implements KeyListener, ActionListener {
    Point[][] points;
    int spendTime=0;
    Timer recordTime;
    JTextField showTime;
    Toolkit toolkit;
    PersonInMaze person;
    boolean isLeave=false;
    int out_i;
    int out_j;

    public HandleMove(){
        recordTime=new Timer(1000,this);
        showTime=new JTextField("0",5);
        toolkit=getToolkit();
        showTime.setEditable(false);
        showTime.setHorizontalAlignment(0);
        add(new JLabel("计时器:"));
        add(showTime);
        setBackground(Color.cyan);
    }
    public void setMazePoint(Point[][] points){
        this.points=points;
    }
    public void initSpendTime(){
        recordTime.stop();
        spendTime=0;
        showTime.setText(null);
    }
    public void chargeMoney(Point point){
        int money=point.getChargeMoney();
        person.setMoney(money);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        spendTime+=1;
        showTime.setText("用时:"+spendTime+"秒");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        recordTime.start();
        person=(PersonInMaze) e.getSource();
        int i=-1;
        int j=-1;
        Point point=person.getAtMazePoint();
        for(int k=0;k<points.length;k++){
            for(int m=0;m<points[k].length;m++){
                if(point.equals(points[k][m])){
                    i=k;
                    j=m;
                    break;
                }
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_UP){
            int k=Math.max(i-1,0);
            if(points[k][j].isRoad()){
                toolkit.beep();
                person.setAtMazePoint(points[k][j]);
                person.setLocation(points[k][j].getX(),points[k][j].getY());
                if(points[k][j].isCharge()){
                    chargeMoney(points[k][j]);
                }
            }
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            int k=Math.max(i+1,points.length-1);
            if(points[k][j].isRoad()){
                toolkit.beep();
                person.setAtMazePoint(points[k][j]);
                person.setLocation(points[k][j].getX(),points[k][j].getY());
                if(points[k][j].isCharge()){
                    chargeMoney(points[k][j]);
                }
            }
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            int k=Math.max(j+1,0);
            if(points[k][j].isRoad()){
                toolkit.beep();
                person.setAtMazePoint(points[k][j]);
                person.setLocation(points[k][j].getX(),points[k][j].getY());
                if(points[k][j].isCharge()){
                    chargeMoney(points[k][j]);
                }
            }
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            int k=Math.max(j+1,points[0].length-1);
            if(points[k][j].isRoad()){
                toolkit.beep();
                person.setAtMazePoint(points[k][j]);
                person.setLocation(points[k][j].getX(),points[k][j].getY());
                if(points[k][j].isCharge()){
                    chargeMoney(points[k][j]);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(isLeave){
            return;
        }
        PersonInMaze personInMaze=(PersonInMaze) e.getSource();
        int i=-1;
        int j=-1;
        Point point=personInMaze.getAtMazePoint();
        if(point.isOut()){
            String str = JOptionPane.showInputDialog(this, "输入您的路费（数字）", "收费站出口", -1);
            int k=0;
            try {
                k=Integer.parseInt(str.trim());
            }catch (Exception e1){
                JOptionPane.showMessageDialog(this, "您费用不对，请重新进入出口", "消息框", 1);
            }
            if(k==point.getChargeMoney()){
                recordTime.stop();
                JOptionPane.showMessageDialog(this, "您可以离开出口", "消息框", 1);
                int m=points[points.length-1][points[0].length-1].getX()+personInMaze.getBounds().width;
                int n=points[points.length-1][points[0].length-1].getY()+personInMaze.getBounds().height;
                personInMaze.setLocation(m,n);
                isLeave=true;
                personInMaze.cleanMoney();
            }else{
                JOptionPane.showMessageDialog(this, "您费用不对，请重新进入出口", "消息框", 1);
            }
        }
    }
}
