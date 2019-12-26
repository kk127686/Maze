package com.smx.view;

import com.smx.model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MazeView extends JPanel {
    public Point[][] points;
    Rectangle2D[][] block;
    int width=22;
    int height=22;
    int leftX=80;
    int leftY=50;
    public  PersonInMaze personWalker;
    public HandleMove handleMove;
    public MazeView(Point[][] points){
        this.points=points;
        this.personWalker=new PersonInMaze();
        this.handleMove=new HandleMove();
        initPointXY();
        this.handleMove.setMazePoint(points);
        this.block=new Rectangle2D[points.length][points[0].length];
        setLayout(null);
        this.add(handleMove);
        this.add(personWalker);
        handleMove.setSize(120,30);
        handleMove.setLocation(leftX,leftY/3);
        personWalker.setSize(width,height);
        personWalker.setAtMazePoint(getEnterPoint(points));
        personWalker.setLocation(getEnterPoint(points).getX(),getEnterPoint(points).getY());
        initView();
        registerListener();
    }


    private void registerListener() {
        personWalker.addKeyListener(handleMove);
        handleMove.setMazePoint(points);
    }

    public void initPointXY() {
        for(int i=0;i<points.length;i++){
            for(int j=0;j<points[i].length;j++){
                points[i][j].setX(j*width+leftX);
                points[i][j].setY(i*height+leftY);
            }
        }
        personWalker.setAtMazePoint(getEnterPoint(points));
        personWalker.setLocation(getEnterPoint(points).getX(),getEnterPoint(points).getY());
        handleMove.setMazePoint(points);
    }

    public void initView() {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++)
            {
                int x = points[i][j].getX();
                int y = points[i][j].getY();
                block[i][j] = new Rectangle2D.Double(x, y, width, height);
            }
        }
        repaint();
        handleMove.showTime.setText("0");
        personWalker.requestFocusInWindow();
        validate();
    }

    private Point getEnterPoint(Point[][] points) {
        Point point=null;
        for(int i=0;i<points.length;i++){
            for(int j=0;j<points[i].length;j++){
                if(points[i][j].isEnter()){
                    point=points[i][j];
                    break;
                }
            }
        }
        return point;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        BasicStroke basicStroke = new BasicStroke(1.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (!points[i][j].isRoad()) {
                    graphics2D.setColor(Color.ORANGE);
                    graphics2D.setStroke(basicStroke);
                    graphics2D.draw(block[i][j]);
                }else{
                    graphics2D.setColor(Color.green);
                    graphics2D.fill(block[i][j]);
                    graphics2D.setColor(Color.blue);
                    graphics2D.setStroke(basicStroke);
                    graphics2D.draw(block[i][j]);
                    if(points[i][j].isCharge()) {
                        graphics2D.setColor(Color.blue);
                        graphics2D.fill(block[i][j]);
                        graphics2D.setColor(Color.white);
                        int k = points[i][j].getX();
                        int m = points[i][j].getY();
                        graphics2D.setFont(new Font("", Font.BOLD, 15));
                        graphics2D.drawString("" + points[i][j].getChargeMoney(), k + width / 8, m + 4 * height / 5);
                    }
                    if (points[i][j].isOut()){
                        graphics2D.setColor(Color.red);
                        graphics2D.fill(block[i][j]);
                        graphics2D.setColor(Color.white);
                        graphics2D.setFont(new Font("", 1, 10));
                        graphics2D.drawString("出口", points[i][j].getX(), points[i][j].getY() + 4 * height / 5);
                    }
                    if(points[i][j].isEnter()){
                        graphics2D.setColor(Color.red);
                        graphics2D.fill(block[i][j]);
                        graphics2D.setColor(Color.red);
                        graphics2D.setStroke(basicStroke);
                        graphics2D.draw(block[i][j]);
                    }
//                    if(points[i][j].isMountain()){
//                        graphics2D.setColor(Color.red);
//                        graphics2D.fill(block[i][j]);
//                        graphics2D.setColor(Color.red);
//                        graphics2D.setStroke(basicStroke);
//                        graphics2D.draw(block[i][j]);
//                    }
                }

            }
        }
        graphics2D.setColor(Color.red);
        int i = points[0][0].getX();
        int j = points[0][0].getY();
        i = i * width + leftX;
        j = j * height + leftY;
        Rectangle2D.Double localDouble = new Rectangle2D.Double(i, j, width * points[0].length, height * points.length);
        graphics2D.draw(localDouble);
        graphics2D.setColor(Color.black);
        String str1 = "绿色是路,红色是出口，蓝色是收费站（只要经过就收费，包括反复经过）务必记住整个路费，否则无法离开出口";
        String str2 = "用鼠标单击走迷宫者，然后按方向键行走";
        int n = handleMove.getBounds().width + leftX;
        graphics2D.setFont(new Font("", 0, 14));
        graphics2D.drawString(str1, n + 2, 2 * this.leftY / 3);
        graphics2D.drawString(str2, leftX, (points.length + 1) * height + leftY);
    }
}
