package com.smx.view;

import javax.swing.*;
import java.awt.*;

public class IntegrationView extends JFrame {
    JTabbedPane tabbedPane;
    public IntegrationView(){
        tabbedPane=new JTabbedPane(2);
        tabbedPane.validate();
        add(tabbedPane, BorderLayout.CENTER);
        setCenter(1500,720);
        setVisible(true);
    }
    public void  addMazeVew(String str1,MazeView mazeView){
        tabbedPane.add(str1,mazeView);
        validate();
        System.out.println("Hello");
    }
    private void setCenter(int width,int height){
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int screemWidth=(int)dimension.getWidth();
        int screenHeight=(int)dimension.getHeight();
        int xPos=(screemWidth-width)/2;
        int yPos=(screenHeight-height)/2;
        setBounds(xPos,yPos,width,height);
    }
}
