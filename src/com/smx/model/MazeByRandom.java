package com.smx.model;

import com.sun.org.apache.bcel.internal.generic.FADD;

import java.util.*;

public class MazeByRandom implements MazeMaker{
    public Point[][] points;
    int row;
    int column;
    LinkedList<Point> isMountainPoint;
    public MazeByRandom(int row,int column){
        this.column=column;
        this.row=row;
        this.isMountainPoint=new LinkedList<>();
        this.points=new Point[row][column];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                this.points[i][j]=new Point();
                this.points[i][j].setX(j);
                this.points[i][j].setY(i);
            }
        }
    }
    @Override
    public Point[][] initMaze() {
        initRoad();
        initNumber();
        this.points[0][0].setEnter(true);
        this.points[0][0].setRoad(true);
        this.points[0][0].setHaveFlag(true);
        this.points[0][0].setNumber(-1);
        this.points[0][0].setMountain(true);
        this.points[row-1][column-1].setOut(true);
        this.points[row-1][column-1].setNumber(row*column);
        this.points[row-1][column-1].setMountain(true);
       isMountainPoint.add(this.points[0][0]);
       isMountainPoint.add(this.points[row-1][column-1]);
       randomSetMountain(row*column/5);
       Point point=points[0][0];
       while(point!=this.points[row-1][column-1]){
           point=findMaxHeightMountain(point);
       }
        return points;
    }
    private void initRoad(){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                this.points[i][j].setRoad(false);
                this.points[i][j].setHaveFlag(false);
                this.points[i][j].setMountain(false);
            }
        }
    }
    private  void initNumber(){
        ArrayList<Integer> arrayList=new ArrayList<>();
        for(int i=0;i<row*column;i++){
            arrayList.add(i);
        }
        Collections.shuffle(arrayList);
        Iterator iterator=arrayList.iterator();
        int j=0;
        for(int k=9;k<row;k++){
            for(int m=0;m<column;m++){
                this.points[k][m].setNumber((int)(iterator.next()));
            }
        }
    }
    private void randomSetMountain(int count){
        LinkedList linkedList=new LinkedList();
        Random random=new Random();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if((this.points[i][j]!=this.points[0][0])&&(this.points[i][j]!=this.points[row-1][column-1])){
                    linkedList.add(this.points[i][j]);
                }
            }
        }
        while(count>0){
            int i=random.nextInt(linkedList.size());
            Point point=(Point) linkedList.remove(i);
            point.setMountain(true);
            this.isMountainPoint.add(point);
            count--;
        }
    }
    Point findMaxHeightMountain(Point point){
        int i=Integer.MAX_VALUE;
        LinkedList<Point> linkedList=new LinkedList<>();
        Point point1;
        for(int j=0;j<isMountainPoint.size();i++){
            point1=isMountainPoint.get(i);
            if((point!=point1)&&(!point1.isHaveFlag())&&(point.distanceTo(point1)<i)){
                i=point.distanceTo(point1);
            }
        }
        for(int j=0;j<isMountainPoint.size();j++){
            point1=isMountainPoint.get(j);
            if((point!=point1)&&(!point1.isHaveFlag())&&(i==point.distanceTo(point1))){
                linkedList.add(point1);
            }
        }
        Point point2=null;
        int k=linkedList.get(0).getNumber();
        for(int m=0;m<linkedList.size();m++){
            int n=linkedList.get(m).getNumber();
            if(n>=k){
                k=n;
            }
        }
        for(int m=0;m<linkedList.size();m++){
            if(linkedList.get(m).getNumber()==k){
                point2=linkedList.get(m);
                break;
            }
        }
        point.setHaveFlag(true);
        SetRoad.setRoad(point,point2,points);
        return point2;
    }

}
