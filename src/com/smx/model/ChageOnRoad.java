package com.smx.model;

import com.smx.Maze;
import com.sun.org.apache.bcel.internal.generic.FADD;

import java.util.ArrayList;
import java.util.Random;

public class ChageOnRoad extends SetChargeOnRoad{
    @Override
    public Point[][] setChargeOnRoad(Point[][] points,int x) {
        ArrayList<Point> pointArrayList=new ArrayList<>();
        Random random=new Random();
        for(int i=0;i<points.length;i++){
            for(int j=0;j<points[i].length;j++){
                if((points[i][j].isRoad())&&(!points[i][j].isEnter())&&(!points[i][j].isOut())){
                    pointArrayList.add(points[i][j]);
                }
                points[i][j].setCharge(false);
            }
        }
        x= Math.min(pointArrayList.size(),x);
        while (x > 0)
        {
            int i = random.nextInt(pointArrayList.size());
            Point p1 = (Point)pointArrayList.remove(i);
            p1.setCharge(true);
            int k = random.nextInt(this.MAXMoney) + 1;
            p1.setChargeMoney(k);
            x--;
        }
        return points;
    }
}
