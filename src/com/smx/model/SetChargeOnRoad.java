package com.smx.model;

public abstract class SetChargeOnRoad {
    int MAXMoney=20;
    public abstract Point[][] setChargeOnRoad(Point[][] points,int count);
    public void setMAXMoney(int maxMoney){
        this.MAXMoney=maxMoney;
    }
}
