package com.smx.model;

public class Point {
    private int x;
    private  int y;
    private int number;
    private boolean isRoad;
    private boolean isEnter;
    private boolean isOut;
    private boolean haveFlag;
    private boolean isMountain;
    private boolean isCharge;
    private int chargeMoney;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public void setRoad(boolean road) {
        isRoad = road;
    }

    public boolean isEnter() {
        return isEnter;
    }

    public void setEnter(boolean enter) {
        isEnter = enter;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public boolean isHaveFlag() {
        return haveFlag;
    }

    public void setHaveFlag(boolean haveFlag) {
        this.haveFlag = haveFlag;
    }

    public boolean isMountain() {
        return isMountain;
    }

    public void setMountain(boolean mountain) {
        isMountain = mountain;
    }

    public boolean isCharge() {
        return isCharge;
    }

    public void setCharge(boolean charge) {
        isCharge = charge;
    }

    public int getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(int chargeMoney) {
        this.chargeMoney = chargeMoney;
    }
    public boolean equals(Point point){
        if((point.getX()==getX())&&(point.getY()==getY())){
            return true;
        }
        return false;
    }
    public int distanceTo(Point point){
        return Math.abs(getX()-point.getX())+ Math.abs(getY()-point.getY());
    }
}
