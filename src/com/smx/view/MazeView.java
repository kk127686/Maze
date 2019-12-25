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
    PersonInMaze personWalker;
    HandleMove handleMove;
    public MazeView(Point[][] points){
        this.points=points;
        this.personWalker=new PersonInMaze();
        this.handleMove=new HandleMove();
        initPointXY();
        registerListener();
    }

    private void registerListener() {
        personWalker.addActionListener(handleMove);
        handleMove.setMazePoint(points);
    }

    public void initPointXY() {
        for(int i=0;i<points.length;i++){
            for(int j=0;j<points.length;j++){
                this.points[i][j].setX(j*width+leftX);
                this.points[i][j].setY(i*height+leftY);
            }
        }
        personWalker.setAtMazePoint(getEnterPoint(points));
        personWalker.setLocation(getEnterPoint(points).getX(),getEnterPoint(points).getY());
        handleMove.setMazePoint(points);
    }
    public void initView()
    {
        for (int i = 0; i < this.points.length; i++) {
            for (int j = 0; j < this.points[i].length; j++)
            {
                int k = this.points[i][j].getX();
                int m = this.points[i][j].getY();

                this.block[i][j] = new Rectangle2D.Double(k, m, this.width, this.height);
            }
        }
        repaint();
        this.handleMove.showTime.setText("0");
        this.personWalker.requestFocusInWindow();
        validate();
    }

    private Point getEnterPoint(Point[][] points) {
        Point point=null;
        for(int i=0;i<points.length;i++){
            for(int j=0;j<points[i].length;j++){
                if(points[i][j].isEnter()){
                    point=points[i][j];
                }
            }
        }
        return point;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D localGraphics2D = (Graphics2D)g;
        BasicStroke localBasicStroke = new BasicStroke(1.0F, 1, 0);
        for (int i = 0; i < this.points.length; i++) {
            for (int j = 0; j < this.points[i].length; j++) {
                if (!this.points[i][j].isRoad())
                {
                    Color localColor = new Color(233, 143, 22);
                    localGraphics2D.setColor(localColor);
                    localGraphics2D.setStroke(localBasicStroke);
                    localGraphics2D.draw(this.block[i][j]);
                }
                else
                {
                    localGraphics2D.setColor(Color.green);
                    localGraphics2D.fill(this.block[i][j]);
                    localGraphics2D.setColor(Color.blue);
                    localGraphics2D.setStroke(localBasicStroke);
                    localGraphics2D.draw(this.block[i][j]);
                    if (this.points[i][j].isCharge())
                    {
                        localGraphics2D.setColor(Color.blue);
                        localGraphics2D.fill(this.block[i][j]);
                        localGraphics2D.setColor(Color.white);
                        int k = this.points[i][j].getX();
                        int m = this.points[i][j].getY();
                        localGraphics2D.setFont(new Font("", 1, 15));
                        localGraphics2D.drawString("" + this.points[i][j].getChargeMoney(), k + this.width / 8, m + 4 * this.height / 5);
                    }
                    if (this.points[i][j].isOut())
                    {
                        localGraphics2D.setColor(Color.red);
                        localGraphics2D.fill(this.block[i][j]);
                        localGraphics2D.setColor(Color.white);
                        localGraphics2D.setFont(new Font("", 1, 10));
                        localGraphics2D.drawString("出口", this.points[i][j].getX(), this.points[i][j].getY() + 4 * this.height / 5);
                    }
                }
            }
        }
        localGraphics2D.setColor(Color.red);
        int i = this.points[0][0].getX();
        int j = this.points[0][0].getY();
        i = i * this.width + this.leftX;
        j = j * this.height + this.leftY;
        Rectangle2D.Double localDouble = new Rectangle2D.Double(i, j, this.width * this.points[0].length, this.height * this.points.length);

        localGraphics2D.draw(localDouble);
        localGraphics2D.setColor(Color.black);
        String str1 = "绿色是路,红色是出口，蓝色是收费站（只要经过就收费，包括反复经过）务必记住整个路费，否则无法离开出口";

        String str2 = "用鼠标单击走迷宫者，然后按方向键行走";
        int n = this.handleMove.getBounds().width + this.leftX;
        localGraphics2D.setFont(new Font("", 0, 14));
        localGraphics2D.drawString(str1, n + 2, 2 * this.leftY / 3);
        localGraphics2D.drawString(str2, this.leftX, (this.points.length + 1) * this.height + this.leftY);

    }
}
