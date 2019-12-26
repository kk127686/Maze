package com.smx;

import com.smx.model.ChargeOnRoad;
import com.smx.model.MazeByFile;
import com.smx.model.MazeByRandom;
import com.smx.model.Point;
import com.smx.view.FixedMazeView;
import com.smx.view.IntegrationView;
import com.smx.view.RandomMazeView;

import java.io.File;

public class Maze {

    public static void main(String[] args) {
//        MazeByRandom mazeByRandom=new MazeByRandom(21,39);
//        Point[][] points=mazeByRandom.initMaze();
//        ChargeOnRoad chargeOnRoad=new ChargeOnRoad();
//        points=chargeOnRoad.setChargeOnRoad(points,20);
//        RandomMazeView randomMazeView=new RandomMazeView(points);
//        IntegrationView integrationView=new IntegrationView();
//        integrationView.addMazeVew("随机迷宫",randomMazeView);

        MazeByRandom mazeByRandom = new MazeByRandom(21, 39);
        Point[][] points = mazeByRandom.initMaze();
        ChargeOnRoad chargeOnRoad = new ChargeOnRoad();
        points = chargeOnRoad.setChargeOnRoad(points, 20);
        RandomMazeView randomMazeView = new RandomMazeView(points);
        IntegrationView localIntegrationView = new IntegrationView();
        localIntegrationView.addMazeVew("随机迷宫", randomMazeView);





//        MazeByFile mazeByFile = new MazeByFile(new File("迷宫文件\\蜀道迷宫.txt"));
//        points = mazeByFile.initMaze();
//        ChargeOnRoad localChargeOnRoad2 = new ChargeOnRoad();
//        localChargeOnRoad2.setMAXMoney(10);
//        points = localChargeOnRoad2.setChargeOnRoad(points, 6);
//        FixedMazeView fixedMazeView = new FixedMazeView(points);
//        localIntegrationView.addMazeVew("蜀道迷宫", fixedMazeView);

    }
}
