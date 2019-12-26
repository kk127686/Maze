package com.smx.model;

public class SetRoad {
    public static void setRoad(Point p1,Point p2,Point[][] point_array){
        int i=point_array.length;
        int j=point_array[0].length;
        int k=0;
        int m=0;
        int n=0;
        int i1=0;
        int i3;
        for(int i2=0;i2<i;i2++){
            for(i3=0;i3<j;i3++){
                if(p1==point_array[i2][i3]){
                    k=i2;
                    m=i3;
                }
            }
        }
        for(int i2=0;i2<i;i2++){
            for(i3=0;i3<j;i3++){
                if(p2==point_array[i2][i3]){
                    n=i2;
                    i1=i3;
                }
            }
        }
        if(k>n){
            for(int i2=k;i2>=n;i2--){
                point_array[i2][m].setRoad(true);
            }
            if(m<=i1){
                for(int i2=m;i2<=i1;i2++){
                    point_array[n][i2].setRoad(true);
                }
            }else{
                for(int i2=m;i2>=i1;i2--){
                    point_array[n][i2].setRoad(true);
                }
            }
        }else if(k<n){
            for(int i2=k;i2<=n;i2++){
                point_array[i2][m].setRoad(true);
            }
            if(m <= i1){
                for(int i2=m;i2<=i1;i2++){
                    point_array[n][i2].setRoad(true);
                }
            }else{
                for(int i2=m;i2>=i1;i2--){
                    point_array[n][i2].setRoad(true);
                }
            }
        }
    }
}
