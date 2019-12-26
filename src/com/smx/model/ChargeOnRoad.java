package com.smx.model;

import java.util.ArrayList;
import java.util.Random;

public class ChargeOnRoad extends SetChargeOnRoad{
    @Override
    public Point[][] setChargeOnRoad(Point[][] points,int count) {
        ArrayList<Point> pointArrayList=new ArrayList<>();
        Random random=new Random();
        for(int i=0; i<points.length; i++){
            for(int j=0; j<points[i].length; j++){
                if((points[i][j].isRoad())&&(!points[i][j].isEnter())&&(!points[i][j].isOut())){
                    pointArrayList.add(points[i][j]);
                }
                points[i][j].setCharge(false);
            }
        }
        count= Math.min(pointArrayList.size(),count);
        while (count > 0)
        {
            int i = random.nextInt(pointArrayList.size());
            Point p1 = pointArrayList.remove(i);
            p1.setCharge(true);
            int k = random.nextInt(MAXMoney) + 1;
            p1.setChargeMoney(k);
            count--;
        }
        return points;
    }
}
