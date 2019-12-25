package com.smx.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

public class MazeByFile implements MazeMaker {
    public Point[][] points;
    int row;
    int column;
    File mazeFile;
    public MazeByFile(File mazeFile){
        this.mazeFile=mazeFile;
    }
    @Override
    public Point[][] initMaze() {
        RandomAccessFile randomAccessFile=null;
        Object object=null;
        try {
            randomAccessFile=new RandomAccessFile(this.mazeFile,"r");
            long l1=randomAccessFile.length();
            long l2=0L;
            randomAccessFile.seek(l2);
            while (l2<l1){
                String str1=randomAccessFile.readLine().trim();
                if(str1.length()>=column){
                    column=str1.length();
                }
                l2=randomAccessFile.getFilePointer();
                row+=1;
            }
            this.points=new Point[row][column];
            for(int i=0;i<row;i++){
                for(int j=0;j<column;j++){
                    this.points[i][j]=new Point();
                    this.points[i][j].setX(j);
                    this.points[i][j].setY(i);
                }
            }
            l2=0L;
            randomAccessFile.seek(12);
            for(int i=0;i<row;i++){
                String str2=randomAccessFile.readLine().trim();
                char[] arrayOfChar=str2.toCharArray();
                for(int k=0;k<arrayOfChar.length;k++){
                    if(arrayOfChar[k]=='*'){
                        points[i][k].setEnter(true);
                        points[i][k].setRoad(true);
                    }else if(arrayOfChar[k]=='1'){
                        this.points[i][k].setRoad(false);
                    }else if(arrayOfChar[k]=='0'){
                        this.points[i][k].setRoad(true);
                    }else if(arrayOfChar[k]=='#'){
                        this.points[i][k].setOut(true);
                        this.points[i][k].setRoad(true);
                    }
                }
            }
        }catch (IOException e){
            System.out.println("文件不存在");
        }
        return points;
    }
}
