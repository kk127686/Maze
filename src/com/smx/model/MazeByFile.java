package com.smx.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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
        try {
            randomAccessFile=new RandomAccessFile(mazeFile,"r");
            long length=randomAccessFile.length();
            long position=0L;
            randomAccessFile.seek(position);
            while (position<length){
                String str1=randomAccessFile.readLine().trim();
                if(str1.length()>=column){
                    column=str1.length();
                }
                position=randomAccessFile.getFilePointer();
                row+=1;
            }
            points=new Point[row][column];
            for(int i=0;i<row;i++){
                for(int j=0;j<column;j++){
                    points[i][j]=new Point();
                    points[i][j].setX(j);
                    points[i][j].setY(i);
                }
            }
            position=0L;
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
