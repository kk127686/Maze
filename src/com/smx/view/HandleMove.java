package com.smx.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.smx.model.Point;

public class HandleMove extends JPanel implements KeyListener, ActionListener {
    Point[][] points;
    public int spendTime=0;
    public Timer recordTime;
    public JTextField showTime;
    public Toolkit toolkit;
    public PersonInMaze person;
    public boolean isLeave=false;
    public HandleMove(){
        recordTime=new Timer(1000,this);
        showTime=new JTextField("0",5);
        toolkit=getToolkit();
        showTime.setEditable(false);
        showTime.setHorizontalAlignment(JTextField.CENTER);
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
        spendTime++;
        showTime.setText("用时:"+spendTime+"秒");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        recordTime.start();
        person=(PersonInMaze) e.getSource();
        int m=-1;
        int n=-1;
        Point start_point=person.getAtMazePoint();
        for(int i=0;i<points.length;i++){
            for(int j=0;j<points[i].length;j++){
                if(start_point.equals(points[i][j])){
                    m=i;
                    n=j;
                    break;
                }
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_UP){
            int k=Math.max(m-1,0);
            if(points[k][n].isRoad()){
                toolkit.beep();
                person.setAtMazePoint(points[k][n]);
                person.setLocation(points[k][n].getX(),points[k][n].getY());
                if(points[k][n].isCharge()){
                    chargeMoney(points[k][n]);
                }
            }
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            int k=Math.min(m+1,points.length-1);
            if(points[k][n].isRoad()){
                toolkit.beep();
                person.setAtMazePoint(points[k][n]);
                person.setLocation(points[k][n].getX(),points[k][n].getY());
                if(points[k][n].isCharge()){
                    chargeMoney(points[k][n]);
                }
            }
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            int k=Math.max(n-1,0);
            if(points[m][k].isRoad()){
                toolkit.beep();
                person.setAtMazePoint(points[m][k]);
                person.setLocation(points[m][k].getX(),points[m][k].getY());
                if(points[m][k].isCharge()){
                    chargeMoney(points[m][k]);
                }
            }
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            int k=Math.min(n+1,points[0].length-1);
            if(points[m][k].isRoad()){
                toolkit.beep();
                person.setAtMazePoint(points[m][k]);
                person.setLocation(points[m][k].getX(),points[m][k].getY());
                if(points[m][k].isCharge()){
                    chargeMoney(points[m][k]);
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
