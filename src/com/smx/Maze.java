package com.smx;

import com.smx.model.ChargeOnRoad;
import com.smx.model.MazeByRandom;
import com.smx.model.Point;
import com.smx.view.IntegrationView;
import com.smx.view.RandomMazeView;

public class Maze {

    public static void main(String[] args) {
        MazeByRandom mazeByRandom=new MazeByRandom(21,39);
        Point[][] points=mazeByRandom.initMaze();
        ChargeOnRoad chargeOnRoad=new ChargeOnRoad();
        points=chargeOnRoad.setChargeOnRoad(points,20);
        RandomMazeView randomMazeView=new RandomMazeView(points);
        IntegrationView integrationView=new IntegrationView();
        integrationView.addMazeVew("随机迷宫",randomMazeView);
    }
}
