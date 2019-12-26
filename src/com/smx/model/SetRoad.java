package com.smx.model;

public class SetRoad {
    public static void setRoad(Point p1,Point p2,Point[][] point_array){
        int row=point_array.length;
        int column=point_array[0].length;
        int m1=0;
        int n1=0;
        int m2=0;
        int n2=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(p1 == point_array[i][j]){
                    m1=i;
                    n1=j;
                }
            }
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(p2==point_array[i][j]){
                    m2=i;
                    n2=j;
                }
            }
        }
        if(m1>=m2){
            for(int i=m1; i>=m2; i--){
                point_array[i][n1].setRoad(true);
            }
            if(n1<=n2){
                for(int j=n1; j <= n2; j++){
                    point_array[m2][j].setRoad(true);
                }
            }else{
                for(int j=n1;j >= n2;j--){
                    point_array[m2][j].setRoad(true);
                }
            }
        }else if(m1<m2){
            for(int i=m1;i<=m2;i++){
                point_array[i][n1].setRoad(true);
            }
            if(n1 <= n2){
                for(int j=n1; j<=n2; j++){
                    point_array[m2][j].setRoad(true);
                }
            }else{
                for(int j=n1;j>=n2;j--){
                    point_array[m2][j].setRoad(true);
                }
            }
        }
    }
}
