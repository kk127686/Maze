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
        isMountainPoint=new LinkedList<>();
        points=new Point[row][column];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                points[i][j]=new Point();
                points[i][j].setX(j);
                points[i][j].setY(i);
            }
        }
    }
    @Override
    public Point[][] initMaze() {
        initRoad();
        initNumber();
        //初始化入口参数
        points[0][0].setEnter(true);
        points[0][0].setRoad(true);
        points[0][0].setHaveFlag(true);
        points[0][0].setNumber(-1);
        points[0][0].setMountain(true);
        //初始化出口参数
        points[row-1][column-1].setOut(true);
        points[row-1][column-1].setNumber(row*column);
        points[row-1][column-1].setMountain(true);

        //设置出口和入口都是山
       isMountainPoint.add(points[0][0]);
       isMountainPoint.add(points[row-1][column-1]);
       //随机产生山
       randomSetMountain(row*column/5);
       Point point=points[0][0];
       while(point!=points[row-1][column-1]){
           point=findMaxHeightMountain(point);
       }
        return points;
    }
    private void initRoad(){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                points[i][j].setRoad(false);
                points[i][j].setHaveFlag(false);
                points[i][j].setMountain(false);
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
        for(int k=0;k<row;k++){
            for(int m=0;m<column;m++){
                points[k][m].setNumber((Integer) iterator.next());
            }
        }
    }
    private void randomSetMountain(int count){
        LinkedList linkedList=new LinkedList();
        Random random=new Random();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if((points[i][j]!=points[0][0])&&(points[i][j]!=points[row-1][column-1])){
                    linkedList.add(points[i][j]);
                }
            }

        }
        while(count>0){
            int i=random.nextInt(linkedList.size());
            Point point=(Point) linkedList.remove(i);
            point.setMountain(true);
            isMountainPoint.add(point);
            count--;
        }
    }

    Point findMaxHeightMountain(Point point){
        int i=Integer.MAX_VALUE;
        LinkedList<Point> linkedList=new LinkedList<>();
        Point point1;
        for(int j=0;j<isMountainPoint.size();j++){
            point1=isMountainPoint.get(j);
            if((point!=point1)&&(!point1.isHaveFlag())&&(point.distanceTo(point1)<i)){
                i=point.distanceTo(point1);
            }
        }
        for(int j=0;j<isMountainPoint.size();j++){
            point1=isMountainPoint.get(j);
            if((point!=point1)&& (!point1.isHaveFlag()) && (i==point.distanceTo(point1))){
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
        for(int m=0; m<linkedList.size(); m++){
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
